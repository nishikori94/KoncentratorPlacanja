package project.kp.service;

import java.util.List;
import java.util.Set;

import project.kp.model.TipPlacanja;

public interface TipPlacanjaService {

	public List<TipPlacanja> getAllTipoviPlacanja();

	public Set<TipPlacanja> getTipoviPlacanja(String id);
}
