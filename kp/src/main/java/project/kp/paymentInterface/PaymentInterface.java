package project.kp.paymentInterface;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface PaymentInterface {
	
	public Map<String, Object> create(Long merchantOrderId);

	public String complete(HttpServletRequest request);
	
	public void sinhronizacija();

}
