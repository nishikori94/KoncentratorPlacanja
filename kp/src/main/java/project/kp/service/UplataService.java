package project.kp.service;

import project.kp.model.Porudzbina;
import project.kp.model.RezultatTransakcije;
import project.kp.model.Uplata;

public interface UplataService {

	String posaljiZahtevZaUplatu(Uplata u);

	void kreirajUplatu(Porudzbina p);

	String zavrsiUplatu(Long uplataId, RezultatTransakcije rezultatTransakcije);

	String otkaziUplatu(Long uplataId, RezultatTransakcije rezultatTransakcije);

}
