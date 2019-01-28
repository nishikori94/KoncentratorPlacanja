package project.kp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.kp.serviceImpl.BitcoinServiceImpl;

@RestController
@RequestMapping("/api/bitcoin")
@CrossOrigin(origins = "https://localhost:4200")
public class BitcoinController {

	@Autowired
	private BitcoinServiceImpl bitcoinServiceImpl;

	@RequestMapping(method = RequestMethod.GET, path = "/create/{merchantOrderId}", produces = "application/json")
	public String create(@PathVariable("merchantOrderId") Long merchantOrderId) {
		Map<String, Object> response = bitcoinServiceImpl.create(merchantOrderId);
		return "{\"url\":\"" + response.get("redirect_url") + "\"}";
	}

	@Scheduled(cron = "0 * * * * *")
	public void sinhronizacijaBTC() {
		bitcoinServiceImpl.sinhronizacija();
	}

}
