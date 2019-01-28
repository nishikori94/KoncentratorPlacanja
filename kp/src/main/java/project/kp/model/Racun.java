package project.kp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Racun {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@Column(unique = true, nullable = false)
	public String brojRacuna;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "casopis_id", nullable = false)
	public Casopis casopis;

	@ManyToOne
	public Banka banka;

	public Racun() {
		super();
	}

	public Racun(String brojRacuna, Casopis casopis, Banka banka) {
		super();
		this.brojRacuna = brojRacuna;
		this.casopis = casopis;
		this.banka = banka;
	}

	public String getBrojRacuna() {
		return brojRacuna;
	}

	public void setBrojRacuna(String brojRacuna) {
		this.brojRacuna = brojRacuna;
	}

	public Casopis getCasopis() {
		return casopis;
	}

	public void setCasopis(Casopis casopis) {
		this.casopis = casopis;
	}

	public Banka getBanka() {
		return banka;
	}

	public void setBanka(Banka banka) {
		this.banka = banka;
	}

}
