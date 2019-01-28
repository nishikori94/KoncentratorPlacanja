package project.kp.serviceImpl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import project.kp.model.PorudzbinaBTC;
import project.kp.model.StatusUplate;
import project.kp.model.Uplata;
import project.kp.paymentInterface.PaymentInterface;
import project.kp.repository.UplataRepository;

@Service
public class BitcoinServiceImpl implements PaymentInterface {

	@Autowired
	private UplataRepository uplataRep;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public Map<String, Object> create(Long merchantOrderId) {
		Uplata uplata = uplataRep.findByMerchantOrderId(merchantOrderId);
		uplata.setStatusUplate(StatusUplate.NA_OBRADI);
		uplata.setTipPlacanja("Bitcoin");
		uplataRep.save(uplata);
		Map<String, Object> response = new HashMap<String, Object>();

		PorudzbinaBTC pbtc = new PorudzbinaBTC();

		pbtc.setOrder_id(uplata.getMerchantOrderId().toString());

		String exchangeUrl = "https://api.coingate.com/v2/rates/merchant/" + uplata.getValuta() + "/BTC";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Double> d = restTemplate.getForEntity(exchangeUrl, double.class);
		double btcPrice = Double.parseDouble(uplata.getAmount()) * d.getBody().doubleValue();

		pbtc.setPrice_amount(btcPrice);
		pbtc.setCancel_url(uplata.getFailedUrl());
		pbtc.setSuccess_url(uplata.getSuccessUrl());
		pbtc.setToken("ssssssVw4VbDTLRfQBb9C7bixEtQeXzQPJENVy5r");

		String url = "https://api-sandbox.coingate.com/v2/orders";

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Token 8yL45frWNgvEtbpVWQFsYyoTsJsEz2TRXVtiC6nd");

		ResponseEntity<PorudzbinaBTC> responseEntity = new RestTemplate().exchange(url, HttpMethod.POST,
				new HttpEntity<PorudzbinaBTC>(pbtc, headers), PorudzbinaBTC.class);

		uplata.setBtcId(responseEntity.getBody().getId());
		if (responseEntity.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY) {
			response.put("status", "error");
			uplata.setStatusUplate(StatusUplate.ODBIJENO);
			uplataRep.save(uplata);
			return response;
		}
		uplata.setStatusUplate(StatusUplate.UPLACENO);
		uplataRep.save(uplata);
		response.put("status", "success");
		response.put("redirect_url", responseEntity.getBody().getPayment_url());

		return response;
	}

	@Override
	public String complete(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public void sinhronizacija() {
		final long ONE_MINUTE_IN_MILLIS = 60000;// millisecs
		Date trenutno = new Date();
		Calendar date = Calendar.getInstance();
		long t = date.getTimeInMillis();
		Date trenutnoMinus = new Date(t - (1 * ONE_MINUTE_IN_MILLIS));

		List<Uplata> uplate = uplataRep
				.findAllByMerchantTimestampLessThanEqualAndMerchantTimestampGreaterThanEqualAndTipPlacanjaAndStatusUplate(
						trenutno, trenutnoMinus, "Bitcoin", StatusUplate.NA_OBRADI);
		for (int i = 0; i < uplate.size(); i++) {
			String url = "https://api-sandbox.coingate.com/v2/orders/" + uplate.get(i).getBtcId();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Token 8yL45frWNgvEtbpVWQFsYyoTsJsEz2TRXVtiC6nd");
			ParameterizedTypeReference<Map<String, Object>> typeRef = new ParameterizedTypeReference<Map<String, Object>>() {
			};
			ResponseEntity<Map<String, Object>> response = restTemplate.exchange(url, HttpMethod.GET,
					new HttpEntity<Object>(headers), typeRef);
			JSONObject responseJSON = new JSONObject(response.getBody());
			if (responseJSON.get("status") == "Paid") {
				uplate.get(i).setStatusUplate(StatusUplate.UPLACENO);
				uplataRep.save(uplate.get(i));
			}
		}
	}

}
