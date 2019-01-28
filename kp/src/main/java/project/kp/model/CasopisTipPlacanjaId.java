package project.kp.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CasopisTipPlacanjaId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4971701947434658672L;

	@Column(name = "casopis_id")
	private Long casopisId;

	@Column(name = "tip_placanja_id")
	private Long tipPlacanjaId;

	
	
	public CasopisTipPlacanjaId() {
		super();
	}

	public CasopisTipPlacanjaId(Long casopisId, Long tipPlacanjaId) {
		super();
		this.casopisId = casopisId;
		this.tipPlacanjaId = tipPlacanjaId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		CasopisTipPlacanjaId that = (CasopisTipPlacanjaId) o;
		return Objects.equals(casopisId, that.casopisId) && Objects.equals(tipPlacanjaId, that.tipPlacanjaId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(casopisId, tipPlacanjaId);
	}
}
