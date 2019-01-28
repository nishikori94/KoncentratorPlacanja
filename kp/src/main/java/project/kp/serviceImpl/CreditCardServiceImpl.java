package project.kp.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import project.kp.model.Banka;
import project.kp.model.Casopis;
import project.kp.model.StatusUplate;
import project.kp.model.Uplata;
import project.kp.paymentInterface.PaymentInterface;
import project.kp.repository.CasopisRepository;
import project.kp.repository.UplataRepository;

@Service
public class CreditCardServiceImpl implements PaymentInterface {

	@Autowired
	private CasopisRepository casopisRep;

	@Autowired
	private UplataRepository uplataRep;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Map<String, Object> create(Long merchantOrderId) {
		Uplata uplata = uplataRep.findByMerchantOrderId(merchantOrderId);
		Casopis casopis = casopisRep.findByMerchantId(uplata.getMerchantId());
		Banka banka = casopis.getRacun().getBanka();
		uplata.setTipPlacanja("Kartica");
		uplataRep.save(uplata);
		final String putanja = "https://localhost:" + banka.getPort() + "/placanje/generisiUrl";
		String url = restTemplate.postForObject(putanja, uplata, String.class);
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("status", "success");
		response.put("url", url);
		return response;
	}

	@Override
	public String complete(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Uplata uplata = uplataRep.findById(Long.parseLong(request.getParameter("paymentId"))).get();
		String url;
		if (request.getParameter("result").equals("true")) {
			uplata.setStatusUplate(StatusUplate.UPLACENO);
			url = uplata.getSuccessUrl();
			System.out.println("[PC] Transakcija obradjena USPESNO");
		} else {
			uplata.setStatusUplate(StatusUplate.ODBIJENO);
			url = uplata.getFailedUrl();
			System.out.println("[PC] Transakcija obradjena NEUSPESNO");
		}
		uplataRep.save(uplata);
		return url;
	}

	@Override
	public void sinhronizacija() {
		// TODO Auto-generated method stub
	}

}
