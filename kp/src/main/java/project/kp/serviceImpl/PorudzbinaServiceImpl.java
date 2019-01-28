package project.kp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.kp.model.Casopis;
import project.kp.model.Porudzbina;
import project.kp.model.StatusUplate;
import project.kp.model.Uplata;
import project.kp.repository.CasopisRepository;
import project.kp.repository.UplataRepository;
import project.kp.service.PorudzbinaService;

@Service
public class PorudzbinaServiceImpl implements PorudzbinaService {

	@Autowired
	private UplataRepository uplataRep;

	@Autowired
	private CasopisRepository casopisRep;

	@Override
	public void kreirajPorudzbinu(Porudzbina porudzbina) {
		Casopis casopis = casopisRep.findByMerchantId(porudzbina.getMerchantId());
		// Banka banka = casopis.getRacun().getBanka();
		Uplata u = new Uplata(porudzbina.getAmount(), porudzbina.getMerchantOrderId(),
				porudzbina.getMerchantTimestamp(), porudzbina.getMerchantId(),
				casopisRep.findByMerchantId(porudzbina.getMerchantId()).getMerchantPassword(), casopis.getSuccessUrl(),
				casopis.getFailedUrl(), casopis.getErrorUrl(), StatusUplate.NA_OBRADI, porudzbina.getValuta(), "");
		uplataRep.save(u);
	}

}
