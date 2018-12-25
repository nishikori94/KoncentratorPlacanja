package project.kp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

@Entity(name = "TipPlacanja")
@Table(name = "tip_placanja")
@NaturalIdCache
@Cache(
    usage = CacheConcurrencyStrategy.READ_WRITE
)
public class TipPlacanja {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NaturalId
	private String naziv;

	@OneToMany(
	        mappedBy = "tipPlacanja",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<CasopisTipPlacanja> casopisi = new ArrayList<>();

	@Column
	private String url;

	@Column
	private String imgPath;


	public TipPlacanja() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipPlacanja(Long id, String naziv, String url, String imgPath) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.url = url;
		this.imgPath = imgPath;
	}
	
	

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipPlacanja tipPlacanja = (TipPlacanja) o;
        return Objects.equals(naziv, tipPlacanja.naziv);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(naziv);
    }

}
