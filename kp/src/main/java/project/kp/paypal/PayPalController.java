package project.kp.paypal;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/paypal")
@CrossOrigin(origins = "http://localhost:4200")
public class PayPalController {

	private final PayPalClient payPalClient;

	@Autowired
	PayPalController(PayPalClient payPalClient) {
		this.payPalClient = payPalClient;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/make/payment/{merchantOrderId}", produces = "application/json")
	public Map<String, Object> makePayment(@PathVariable("merchantOrderId") Long merchantOrderId) {
		return payPalClient.createPayment(merchantOrderId);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/complete/payment", produces = "application/json")
	public Map<String, Object> completePayment(HttpServletRequest request) {
		return payPalClient.completePayment(request);
	}
}
