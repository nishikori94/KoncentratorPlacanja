package project.kp.bitcoin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import project.kp.model.Uplata;
import project.kp.repository.UplataRepository;

@RestController
@RequestMapping("/bitcoin")
@CrossOrigin(origins = "http://localhost:4200")
public class BitcoinController {

	@Autowired
	private UplataRepository uplataRep;

	@RequestMapping(method = RequestMethod.GET, path = "/napraviPorudzbinuBTC/{merchantOrderId}", produces = "application/json")
	public String napraviPorudzbinuBTC(@PathVariable("merchantOrderId") Long merchantOrderId) {
		Uplata uplata = uplataRep.findByMerchantOrderId(merchantOrderId);
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
		headers.add("Authorization", "Token _8G5f_BCufzL2wc9y5qJTyhz1cZF7rfHwYq895ax");

		ResponseEntity<PorudzbinaBTC> responseEntity = new RestTemplate().exchange(url, HttpMethod.POST,
				new HttpEntity<PorudzbinaBTC>(pbtc, headers), PorudzbinaBTC.class);

		if (responseEntity.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY) {
			response.put("status", "error");
			return "{\"url\":\"" + response.get("redirect_url") + "\"}";
		}

		response.put("status", "success");
		response.put("redirect_url", responseEntity.getBody().getPayment_url());

		return "{\"url\":\"" + response.get("redirect_url") + "\"}";
	}
}
