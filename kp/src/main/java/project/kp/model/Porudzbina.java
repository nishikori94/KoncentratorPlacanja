package project.kp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Porudzbina {

	@Id
	@GeneratedValue
	public Long merchantOrderId;

	@Column
	private String merchantId;

	@Column
	private String merchantPassword;

	@Column
	private String amount;
	
	@Column
	private String valuta;

	@Column
	private String merchantTimestamp;

	@Column
	private String tipPlacanja;

	public Porudzbina() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Porudzbina(Long merchantOrderId, String merchantId, String merchantPassword, String amount,
			String merchantTimestamp, String tipPlacanja, String valuta) {
		super();
		this.merchantOrderId = merchantOrderId;
		this.merchantId = merchantId;
		this.merchantPassword = merchantPassword;
		this.amount = amount;
		this.merchantTimestamp = merchantTimestamp;
		this.tipPlacanja = tipPlacanja;
		this.valuta = valuta;
	}

	public Long getMerchantOrderId() {
		return merchantOrderId;
	}

	public void setMerchantOrderId(Long merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getMerchantTimestamp() {
		return merchantTimestamp;
	}

	public void setMerchantTimestamp(String merchantTimestamp) {
		this.merchantTimestamp = merchantTimestamp;
	}

	public String getTipPlacanja() {
		return tipPlacanja;
	}

	public void setTipPlacanja(String tipPlacanja) {
		this.tipPlacanja = tipPlacanja;
	}

	public String getValuta() {
		return valuta;
	}

	public void setValuta(String valuta) {
		this.valuta = valuta;
	}

	
}
