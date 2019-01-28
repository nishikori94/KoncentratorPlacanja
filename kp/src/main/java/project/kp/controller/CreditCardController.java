package project.kp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.kp.serviceImpl.CreditCardServiceImpl;

@RestController
@RequestMapping("/api/creditcard")
@CrossOrigin(origins = "https://localhost:4200")
public class CreditCardController {

	@Autowired
	private CreditCardServiceImpl creditCardServiceImpl; 
	
	// generisanje zahteva(Uplata) koji se salje banci prodavca, a vraca se link ka
	// frontu banke za unos podataka sa kartice
	// ovde treba u parametru da bude merchantOrderId i tipPlacanjaId
	@RequestMapping(method = RequestMethod.GET, path = "/create/{merchantOrderId}", produces = "application/json")
	public Map<String, Object> create(@PathVariable("merchantOrderId") Long merchantOrderId) {
		System.out.println("[PC] Kreiraj uplatu");
		return creditCardServiceImpl.create(merchantOrderId);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/complete", produces = "application/json")
	public String complete(HttpServletRequest request) {
		System.out.println("[PC] Zavrsi uplatu");
		String url = creditCardServiceImpl.complete(request);
		return "{\"url\":\"" + url + "\"}";
	}

}
