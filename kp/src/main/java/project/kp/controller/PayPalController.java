package project.kp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.kp.serviceImpl.PayPalServiceImpl;

@RestController
@RequestMapping(value = "/api/paypal")
@CrossOrigin(origins = "https://localhost:4200")
public class PayPalController {

	private final PayPalServiceImpl payPalServiceImp;
	
	@Autowired
	PayPalController(PayPalServiceImpl payPalServiceImp) {
		this.payPalServiceImp = payPalServiceImp;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/create/{merchantOrderId}", produces = "application/json")
	public Map<String, Object> create(@PathVariable("merchantOrderId") Long merchantOrderId) {
		return payPalServiceImp.create(merchantOrderId);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/complete", produces = "application/json")
	public void completePayment(HttpServletRequest request) {
		payPalServiceImp.complete(request);
	}
	
	@Scheduled(cron = "0 * * * * *")
	public void sinhronizacijaPayPal() {
		payPalServiceImp.sinhronizacija();
	}

}
