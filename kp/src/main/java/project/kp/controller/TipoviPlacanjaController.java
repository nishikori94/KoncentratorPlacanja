package project.kp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import project.kp.model.TipPlacanja;
import project.kp.repository.TipPlacanjaRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://localhost:4200")
public class TipoviPlacanjaController {

	@Autowired
	private TipPlacanjaRepository tipPlacanjaRep;

	@GetMapping(path = "/tipoviPlacanja/{id}")
	@ResponseBody
	public List<TipPlacanja> getTipoviPlacanja(@PathVariable("id") Long id) {
		return tipPlacanjaRep.findNestoNesto(id);
	}
}
