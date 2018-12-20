package project.kp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.kp.model.TipPlacanja;

@Repository
public interface TipPlacanjaRepository extends JpaRepository<TipPlacanja, Long>{

	public List<TipPlacanja> findAll();
	
	@Query("select tp from TipPlacanja tp join fetch tp.casopisi tpc join fetch tpc.casopis c where c.id = ?1")
	public List<TipPlacanja> findNestoNesto(Long id);
	
	//@Query("select p from Post p join fetch p.tags pt join fetch pt.tag where p.id = ?1")
//	public List<TipPlacanja> findTipoviPlacanja(Long id);
	
//	@Query("SELECT tp FROM TipPlacanja tp JOIN tp.casopisi c WHERE c.merchantId = ?1")
//	public Set<TipPlacanja> findBlaBla(String id);
//	
//	@Query("select TipPlacanja from casopis_tipovi_placanja ct where ct.merchant_id = ?1")
//	public Set<TipPlacanja> findByCasopisi_MerchantId(String id);
}
