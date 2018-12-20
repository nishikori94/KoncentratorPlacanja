package project.kp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import project.kp.model.Banka;
import project.kp.model.Casopis;
import project.kp.model.Porudzbina;
import project.kp.model.StatusUplate;
import project.kp.model.Uplata;
import project.kp.repository.CasopisRepository;
import project.kp.repository.UplataRepository;
import project.kp.service.UplataService;

@Service
public class UplataServiceImpl implements UplataService {

	@Autowired
	CasopisRepository casopisRep;

	@Autowired
	UplataRepository uplataRep;

	@Override
	public String posaljiZahtevZaUplatu(Uplata u) {
		
		final String putanja = "http://localhost:" + u.getBankaPort() + "/placanje/generisiUrl";
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForObject(putanja, u, String.class);
	}

	public void kreirajUplatu(Porudzbina porudzbina) {
		Casopis casopis = casopisRep.findByMerchantId(porudzbina.getMerchantId());
		Banka banka = casopis.getRacun().getBanka();
		Uplata u = new Uplata(porudzbina.getAmount(), porudzbina.getMerchantOrderId(),
				porudzbina.getMerchantTimestamp(), porudzbina.getMerchantId(),
				casopisRep.findByMerchantId(porudzbina.getMerchantId()).getMerchantPassword(), "success url",
				"failedUrl", "errorUrl", StatusUplate.NA_OBRADI, banka.port, porudzbina.getValuta());
		uplataRep.save(u);
	}

	@Override
	public void zavrsiUplatu(Long uplataId) {
		// TODO Auto-generated method stub
		uplataRep.findById(uplataId).get().setStatusUplate(StatusUplate.UPLACENO);
	}

	@Override
	public void otkaziUplatu(Long uplataId) {
		// TODO Auto-generated method stub
		uplataRep.findById(uplataId).get().setStatusUplate(StatusUplate.ODBIJENO);
	}

}
