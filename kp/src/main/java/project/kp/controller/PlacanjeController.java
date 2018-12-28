package project.kp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import project.kp.model.Casopis;
import project.kp.model.Porudzbina;
import project.kp.model.RezultatTransakcije;
import project.kp.model.TipPlacanja;
import project.kp.model.Uplata;
import project.kp.repository.CasopisRepository;
import project.kp.repository.TipPlacanjaRepository;
import project.kp.repository.UplataRepository;
import project.kp.service.TipPlacanjaService;
import project.kp.service.UplataService;

@RestController
@RequestMapping("/placanje")
@CrossOrigin(origins = "http://localhost:4200")
public class PlacanjeController {

	@Autowired
	private UplataRepository uplataRep;
	@Autowired
	private TipPlacanjaRepository tipPlacanjaRep;


	@Autowired
	private TipPlacanjaService tipPlacanjaService;

	@Autowired
	private UplataService uplataService;
	
	@Autowired
	private CasopisRepository casopisRep;

	// vrati sve tipove placanja
	@GetMapping(path = "/tipovi")
	@ResponseBody
	public List<TipPlacanja> getAllTipoviPlacanja() {
		return tipPlacanjaService.getAllTipoviPlacanja();
	}

	// vrati tipove placanja koje podrzava odredjeni casopis na osnovu njegovog id-a
	@GetMapping(path = "/tipoviPlacanja/{id}")
	@ResponseBody
	public List<TipPlacanja> getTipoviPlacanja(@PathVariable("id") Long id) {
		return tipPlacanjaRep.findNestoNesto(id);
	}

	// generisanje zahteva(Uplata) koji se salje banci prodavca, a vraca se link ka
	// frontu banke za unos podataka sa kartice
	// ovde treba u parametru da bude merchantOrderId i tipPlacanjaId
	@RequestMapping(method = RequestMethod.GET, path = "/generisiZahtevZaUplatu/{merchantOrderId}", produces = "application/json")
	public String generisiZahetZaUplatu(@PathVariable("merchantOrderId") Long merchantOrderId) {
		Uplata uplata = uplataRep.findByMerchantOrderId(merchantOrderId);
		String url = uplataService.posaljiZahtevZaUplatu(uplata);
		return "{\"url\":\""+ url + "\"}";
		//return uplataService.posaljiZahtevZaUplatu(uplata);
	}

	// cuvanje porudzbine za evidenciju i vracanje linka ka frontu KP gde bira nacin
	// placanja
	@PostMapping(path = "/sacuvajPorudzbinu")
	public String sacuvajZahtevZaUplatu(@RequestBody Porudzbina p) {
		uplataService.kreirajUplatu(p);
		Casopis c = casopisRep.findByMerchantId(p.getMerchantId());
		// na osnovu merchantId-a ce naci tipove placanja za taj casopis, a
		// merchantOrderId sluzi za kasnije pronalazenje porudzbine
		String putanja = "http://localhost:4200/redirect/" + c.getId() + "/" + p.getMerchantOrderId();
		return putanja;
	}

	@PostMapping(path = "/zavrsiUplatu/{uplataId}")
	public String zavrsiUplatu(@RequestBody RezultatTransakcije rezultatTransakcije,
			@PathVariable("uplataId") Long uplataId) {
		String url = uplataService.zavrsiUplatu(uplataId, rezultatTransakcije);
		System.out.println(rezultatTransakcije);
		return "{\"url\":\""+ url + "\"}";
	}

	@PostMapping(path = "/otkaziUplatu/{uplataId}")
	public String otkaziUplatu(@RequestBody RezultatTransakcije rezultatTransakcije,
			@PathVariable("uplataId") Long uplataId) {
		String url = uplataService.otkaziUplatu(uplataId, rezultatTransakcije);
		System.out.println(rezultatTransakcije);
		return "{\"url\":\""+ url + "\"}";
	}

}
