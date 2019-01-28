package project.kp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.kp.model.Casopis;
import project.kp.model.Porudzbina;
import project.kp.repository.CasopisRepository;
import project.kp.service.PorudzbinaService;

@RestController
@RequestMapping("/porudzbina")
@CrossOrigin(origins = "https://localhost:4200")
public class PorudzbinaController {

	@Autowired
	private PorudzbinaService porudzbinaService;

	@Autowired
	private CasopisRepository casopisRep;

	// cuvanje porudzbine za evidenciju i vracanje linka ka frontu KP gde bira nacin
	// placanja
	@PostMapping(path = "/sacuvajPorudzbinu")
	public String sacuvajZahtevZaUplatu(@RequestBody Porudzbina p) {
		porudzbinaService.kreirajPorudzbinu(p);
		Casopis c = casopisRep.findByMerchantId(p.getMerchantId());
		// na osnovu merchantId-a ce naci tipove placanja za taj casopis, a
		// merchantOrderId sluzi za kasnije pronalazenje porudzbine
		String putanja = "https://localhost:4200/redirect/" + c.getId() + "/" + p.getMerchantOrderId();
		return putanja;
	}
}
