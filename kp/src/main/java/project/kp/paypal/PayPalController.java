package project.kp.paypal;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/paypal")
@CrossOrigin(origins = "https://localhost:4200")
public class PayPalController {

	private final PayPalClient payPalClient;
	
	@Autowired
	PayPalController(PayPalClient payPalClient) {
		this.payPalClient = payPalClient;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/make/payment/{merchantOrderId}", produces = "application/json")
	public Map<String, Object> makePayment(@PathVariable("merchantOrderId") Long merchantOrderId) {
		Map<String, Object> sa = payPalClient.createPayment(merchantOrderId);
		return sa;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/complete/payment", produces = "application/json")
	public void completePayment(HttpServletRequest request) {
		System.out.println("usaooo");
		payPalClient.completePayment(request);
	}
	
	@Scheduled(cron = "0 * * * * *")
	public void sinhronizacijaPayPal() {
		payPalClient.sinhronizacijaPayPal();
	}

}
