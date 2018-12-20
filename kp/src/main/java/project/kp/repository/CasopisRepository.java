package project.kp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.kp.model.Casopis;

public interface CasopisRepository extends JpaRepository<Casopis, Long>{

	public Casopis findByMerchantId(String merchantId);
	
	public Casopis findCasopisByMerchantId(String merchantId);
}
