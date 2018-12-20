package project.kp.service;

import project.kp.model.Porudzbina;
import project.kp.model.Uplata;

public interface UplataService {

	String posaljiZahtevZaUplatu(Uplata u);

	void kreirajUplatu(Porudzbina p);

	void zavrsiUplatu(Long uplataId);

	void otkaziUplatu(Long uplataId);

}
