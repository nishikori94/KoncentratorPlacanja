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
				casopisRep.findByMerchantId(porudzbina.getMerchantId()).getMerchantPassword(), casopis.getSuccessUrl(), casopis.getFailedUrl(), casopis.getErrorUrl(), StatusUplate.NA_OBRADI, banka.port, porudzbina.getValuta());
		uplataRep.save(u);
	}

	@Override
	public String zavrsiUplatu(Long uplataId) {
		// TODO Auto-generated method stub
		Uplata uplata = uplataRep.findById(uplataId).get();
		uplata.setStatusUplate(StatusUplate.UPLACENO);
		return uplata.getSuccessUrl();
	}

	@Override
	public String otkaziUplatu(Long uplataId) {
		// TODO Auto-generated method stub
		Uplata uplata = uplataRep.findById(uplataId).get();
		uplata.setStatusUplate(StatusUplate.ODBIJENO);
		return uplata.getFailedUrl();
	}

}
