package project.kp.serviceImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import project.kp.model.StatusUplate;
import project.kp.model.Uplata;
import project.kp.paymentInterface.PaymentInterface;
import project.kp.repository.UplataRepository;

@Service
public class PayPalServiceImpl implements PaymentInterface {

	@Autowired
	private UplataRepository uplataRep;

	@Autowired
	RestTemplate restTemplate;

	String clientId = "AY_XfMErvTj4gwOLOeyPrsYBuwG4NnG05TBdHFleCkQqOcAhO1hEoW2D26IFQyQocxyB61AP0h76x-9Y";
	String clientSecret = "EPHFPePb_v1zLg0zd8lSy0_pRgJGC4y05HkrqT5nDhFPflCQOKHXjmdu0GCCaD5hyrdx81DyjnRVxlhr";

	@Override
	public Map<String, Object> create(Long merchantOrderId) {
		Map<String, Object> response = new HashMap<String, Object>();
		Uplata uplata = uplataRep.findByMerchantOrderId(merchantOrderId);
		uplata.setTipPlacanja("PayPal");
		uplataRep.save(uplata);
		Amount amount = new Amount();

		String exchangeUrl = "https://bankersalgo.com/apicalc2/5c2f51b4a1f7f/87d380fe40f3d0a7bf9fd780da534427/"
				+ uplata.getValuta() + "/USD/" + uplata.getAmount();

		RestTemplate restTemplate = new RestTemplate();
		String exchangeRate = restTemplate.getForObject(exchangeUrl, String.class);
		JSONObject jsonObj = new JSONObject(exchangeRate);
		String exchangeRateString = jsonObj.get("result").toString();

		BigDecimal bd = new BigDecimal(Double.parseDouble(exchangeRateString));
		bd = bd.setScale(2, RoundingMode.HALF_UP);

		amount.setCurrency("USD");
		amount.setTotal(bd.toString());

		Transaction transaction = new Transaction();
		transaction.setAmount(amount);

		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);

		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");

		Payment payment = new Payment();
		payment.setIntent("sale");
		payment.setPayer(payer);
		payment.setTransactions(transactions);

		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl(uplata.getFailedUrl());
		redirectUrls.setReturnUrl(uplata.getSuccessUrl());
		payment.setRedirectUrls(redirectUrls);
		Payment createdPayment;
		try {
			String redirectUrl = "";
			APIContext context = new APIContext(clientId, clientSecret, "sandbox");
			createdPayment = payment.create(context);
			if (createdPayment != null) {
				List<Links> links = createdPayment.getLinks();
				for (Links link : links) {
					if (link.getRel().equals("approval_url")) {
						redirectUrl = link.getHref();
						break;
					}
				}
				response.put("status", "success");
				response.put("url", redirectUrl);
				uplata.setStatusUplate(StatusUplate.NA_OBRADI);
				uplata.setBtcId(createdPayment.getId());
				uplataRep.save(uplata);
			}
		} catch (PayPalRESTException e) {
			System.out.println("Error happened during payment creation!");
			uplata.setStatusUplate(StatusUplate.ODBIJENO);
			uplataRep.save(uplata);
			response.put("status", "failed");
			response.put("url", uplata.getFailedUrl());
		}
		return response;
	}

	@Override
	public void complete(HttpServletRequest request) {
		Map<String, Object> response = new HashMap<String, Object>();
		Payment payment = new Payment();
		payment.setId(request.getParameter("paymentId"));
		PaymentExecution paymentExecution = new PaymentExecution();
		paymentExecution.setPayerId(request.getParameter("payerId"));
		try {
			APIContext context = new APIContext(clientId, clientSecret, "sandbox");
			Payment createdPayment = payment.execute(context, paymentExecution);
			if (createdPayment != null) {
				response.put("status", "success");
				response.put("payment", createdPayment);
				Uplata uplata = uplataRep.findByBtcId(createdPayment.getId());
				uplata.setStatusUplate(StatusUplate.UPLACENO);
				uplataRep.save(uplata);
			}
		} catch (PayPalRESTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(response.toString());
	}

	@Override
	public void sinhronizacija() {
		final long ONE_MINUTE_IN_MILLIS = 60000;// millisecs
		Date trenutno = new Date();
		Calendar date = Calendar.getInstance();
		long t = date.getTimeInMillis();
		Date trenutnoMinus = new Date(t - (10 * ONE_MINUTE_IN_MILLIS));
		List<Uplata> uplate = uplataRep
				.findAllByMerchantTimestampLessThanEqualAndMerchantTimestampGreaterThanEqualAndTipPlacanjaAndStatusUplate(
						trenutno, trenutnoMinus, "PayPal", StatusUplate.NA_OBRADI);
		for (int i = 0; i < uplate.size(); i++) {
			try {
				APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");
				Payment payment;
				payment = Payment.get(apiContext, uplate.get(i).getBtcId());
				if (payment.getState().equals("approved")) {
					uplate.get(i).setStatusUplate(StatusUplate.UPLACENO);
					uplataRep.save(uplate.get(i));
				}
			} catch (PayPalRESTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}