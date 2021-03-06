package project.kp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Casopis")
@Table(name = "casopis")
public class Casopis {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@Column
	private String merchantId;

	@Column
	private String merchantPassword;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "casopis")
	public Racun racun;

	@OneToMany(mappedBy = "casopis", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CasopisTipPlacanja> tipoviPlacanja = new ArrayList<>();

	@Column
	private String successUrl;

	@Column
	private String failedUrl;

	@Column
	private String errorUrl;

	public Casopis() {
		super();
	}

	public Casopis(String merchantId, String merchantPassword, Racun racun, String successUrl, String failedUrl,
			String errorUrl) {
		super();
		this.merchantId = merchantId;
		this.merchantPassword = merchantPassword;
		this.racun = racun;
		this.successUrl = successUrl;
		this.failedUrl = failedUrl;
		this.errorUrl = errorUrl;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantPassword() {
		return merchantPassword;
	}

	public void setMerchantPassword(String merchantPassword) {
		this.merchantPassword = merchantPassword;
	}

	public Racun getRacun() {
		return racun;
	}

	public void setRacun(Racun racun) {
		this.racun = racun;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<CasopisTipPlacanja> getTipoviPlacanja() {
		return tipoviPlacanja;
	}

	public void setTipoviPlacanja(List<CasopisTipPlacanja> tipoviPlacanja) {
		this.tipoviPlacanja = tipoviPlacanja;
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getFailedUrl() {
		return failedUrl;
	}

	public void setFailedUrl(String failedUrl) {
		this.failedUrl = failedUrl;
	}

	public String getErrorUrl() {
		return errorUrl;
	}

	public void setErrorUrl(String errorUrl) {
		this.errorUrl = errorUrl;
	}

}
