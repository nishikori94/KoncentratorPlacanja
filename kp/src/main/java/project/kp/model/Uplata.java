package project.kp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Uplata {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@Column
	private String amount;

	@Column
	private String valuta;

	@Column
	private Long merchantOrderId;

	@Column
	private Date merchantTimestamp;

	@Column
	private String merchantId;

	@Column
	private String merchantPassword;

	@Column
	private String successUrl;

	@Column
	private String failedUrl;

	@Column
	private String errorUrl;

	@Column
	private StatusUplate statusUplate;

	@Column
	private String bankaPort;

	@Column
	private String tipPlacanja;
	
	@Column
	private String btcId;

	public Uplata() {
	}

	public Uplata(String amount, Long merchantOrderId, Date merchantTimestamp, String merchantId,
			String merchantPassword, String successUrl, String failedUrl, String errorUrl, StatusUplate statusUplate,
			String valuta, String tipPlacanja) {
		super();
		this.amount = amount;
		this.merchantOrderId = merchantOrderId;
		this.merchantTimestamp = merchantTimestamp;
		this.merchantId = merchantId;
		this.merchantPassword = merchantPassword;
		this.successUrl = successUrl;
		this.failedUrl = failedUrl;
		this.errorUrl = errorUrl;
		this.statusUplate = statusUplate;
		this.valuta = valuta;
		this.tipPlacanja = tipPlacanja;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getValuta() {
		return valuta;
	}

	public void setValuta(String valuta) {
		this.valuta = valuta;
	}

	public Long getMerchantOrderId() {
		return merchantOrderId;
	}

	public void setMerchantOrderId(Long merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}

	public Date getMerchantTimestamp() {
		return merchantTimestamp;
	}

	public void setMerchantTimestamp(Date merchantTimestamp) {
		this.merchantTimestamp = merchantTimestamp;
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

	public StatusUplate getStatusUplate() {
		return statusUplate;
	}

	public void setStatusUplate(StatusUplate statusUplate) {
		this.statusUplate = statusUplate;
	}

	public String getTipPlacanja() {
		return tipPlacanja;
	}

	public void setTipPlacanja(String tipPlacanja) {
		this.tipPlacanja = tipPlacanja;
	}

	public String getBtcId() {
		return btcId;
	}

	public void setBtcId(String btcId) {
		this.btcId = btcId;
	}

}
