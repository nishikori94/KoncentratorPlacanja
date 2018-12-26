package project.kp.paypal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
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

import project.kp.model.Uplata;
import project.kp.repository.UplataRepository;

@Service
public class PayPalClient {

	@Autowired
	private UplataRepository uplataRep;

	String clientId = "AY_XfMErvTj4gwOLOeyPrsYBuwG4NnG05TBdHFleCkQqOcAhO1hEoW2D26IFQyQocxyB61AP0h76x-9Y";
	String clientSecret = "EPHFPePb_v1zLg0zd8lSy0_pRgJGC4y05HkrqT5nDhFPflCQOKHXjmdu0GCCaD5hyrdx81DyjnRVxlhr";

	public Map<String, Object> createPayment(Long merchantOrderId) {
		Map<String, Object> response = new HashMap<String, Object>();
		Uplata uplata = uplataRep.findByMerchantOrderId(merchantOrderId);
		Amount amount = new Amount();

		String exchangeUrl = "http://free.currencyconverterapi.com/api/v5/convert?q=" + uplata.getValuta()
				+ "_EUR&compact=ultra";
		RestTemplate restTemplate = new RestTemplate();
		String exchangeRate = restTemplate.getForObject(exchangeUrl, String.class);
		JSONObject jsonObj = new JSONObject(exchangeRate);
		String exchangeRateString = jsonObj.get(uplata.getValuta() + "_EUR").toString();
		double eurAmount = Double.parseDouble(exchangeRateString) * Double.parseDouble(uplata.getAmount());

		BigDecimal bd = new BigDecimal(eurAmount);
		bd = bd.setScale(2, RoundingMode.HALF_UP);

		amount.setCurrency("EUR");
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
			}
		} catch (PayPalRESTException e) {
			System.out.println("Error happened during payment creation!");
		}
		return response;
	}

	public Map<String, Object> completePayment(HttpServletRequest req) {
		Map<String, Object> response = new HashMap<String, Object>();
		Payment payment = new Payment();
		payment.setId(req.getParameter("paymentId"));
		PaymentExecution paymentExecution = new PaymentExecution();
		paymentExecution.setPayerId(req.getParameter("payerId"));
		try {
			APIContext context = new APIContext(clientId, clientSecret, "sandbox");
			Payment createdPayment = payment.execute(context, paymentExecution);
			if (createdPayment != null) {
				response.put("status", "success");
				response.put("payment", createdPayment);
			}
		} catch (PayPalRESTException e) {
			// System.err.println(e.getDetails());
		}
		System.out.println(response.toString());
		return response;
	}
}