package project.kp.model;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity(name = "CasopisTipPlacanja")
@Table(name = "casopis_tip_placanja")
public class CasopisTipPlacanja {

	@EmbeddedId
	private CasopisTipPlacanjaId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("casopisId")
	private Casopis casopis;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("tipPlacanjaId")
	private TipPlacanja tipPlacanja;

	public CasopisTipPlacanja() {
	}

	public CasopisTipPlacanja(CasopisTipPlacanjaId id, Casopis casopis, TipPlacanja tipPlacanja) {
		this.casopis = casopis;
		this.tipPlacanja = tipPlacanja;
		this.id = new CasopisTipPlacanjaId(casopis.getId(), tipPlacanja.getId());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		CasopisTipPlacanja that = (CasopisTipPlacanja) o;
		return Objects.equals(casopis, that.casopis) && Objects.equals(tipPlacanja, that.tipPlacanja);
	}

	@Override
	public int hashCode() {
		return Objects.hash(casopis, tipPlacanja);
	}

}
