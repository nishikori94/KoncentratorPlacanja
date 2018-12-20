package project.kp.serviceImpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.kp.model.TipPlacanja;
import project.kp.repository.TipPlacanjaRepository;
import project.kp.service.TipPlacanjaService;

@Service
public class TipPlacanjaServiceImpl implements TipPlacanjaService {

	@Autowired
	private TipPlacanjaRepository tipPlacanjaRep;

	@Override
	public List<TipPlacanja> getAllTipoviPlacanja() {
		return tipPlacanjaRep.findAll();
	}

	@Override
	public Set<TipPlacanja> getTipoviPlacanja(String id) {
		//System.out.println(casopisRep.findByMerchantId(id).getTipoviPlacanja().size());
		//Set<TipPlacanja> tipoviPlacanja = casopisRep.findByMerchantId(id).getTipoviPlacanja();
		//return tipoviPlacanja;
		return null;
	}

}
