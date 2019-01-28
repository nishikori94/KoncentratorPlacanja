package project.kp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import project.kp.model.Banka;
import project.kp.model.Casopis;
import project.kp.model.Porudzbina;
import project.kp.model.RezultatTransakcije;
import project.kp.model.StatusUplate;
import project.kp.model.Uplata;
import project.kp.repository.CasopisRepository;
import project.kp.repository.RezultatTransakcijeRepository;
import project.kp.repository.UplataRepository;
import project.kp.service.UplataService;

@Service
public class UplataServiceImpl implements UplataService {

	@Autowired
	private CasopisRepository casopisRep;

	@Autowired
	private UplataRepository uplataRep;

	@Autowired
	private RezultatTransakcijeRepository rezultatTransakcijeRep;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public String posaljiZahtevZaUplatu(Uplata u) {
		Casopis casopis = casopisRep.findByMerchantId(u.getMerchantId());
		Banka banka = casopis.getRacun().getBanka();
		u.setTipPlacanja("Kartica");
		final String putanja = "https://localhost:" + banka.getPort() + "/placanje/generisiUrl";
		return restTemplate.postForObject(putanja, u, String.class);
	}

	public void kreirajUplatu(Porudzbina porudzbina) {
		Casopis casopis = casopisRep.findByMerchantId(porudzbina.getMerchantId());
		// Banka banka = casopis.getRacun().getBanka();
		Uplata u = new Uplata(porudzbina.getAmount(), porudzbina.getMerchantOrderId(),
				porudzbina.getMerchantTimestamp(), porudzbina.getMerchantId(),
				casopisRep.findByMerchantId(porudzbina.getMerchantId()).getMerchantPassword(), casopis.getSuccessUrl(),
				casopis.getFailedUrl(), casopis.getErrorUrl(), StatusUplate.NA_OBRADI, porudzbina.getValuta(), "");
		uplataRep.save(u);
	}

	@Override
	public String zavrsiUplatu(Long uplataId, RezultatTransakcije rezultatTransakcije) {
		Uplata uplata = uplataRep.findById(uplataId).get();
		uplata.setStatusUplate(StatusUplate.UPLACENO);
		uplataRep.save(uplata);
		rezultatTransakcijeRep.save(rezultatTransakcije);
		return uplata.getSuccessUrl();
	}

	@Override
	public String otkaziUplatu(Long uplataId, RezultatTransakcije rezultatTransakcije) {
		Uplata uplata = uplataRep.findById(uplataId).get();
		uplata.setStatusUplate(StatusUplate.ODBIJENO);
		uplataRep.save(uplata);
		rezultatTransakcijeRep.save(rezultatTransakcije);
		return uplata.getFailedUrl();
	}

}
