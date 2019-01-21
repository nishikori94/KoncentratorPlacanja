package project.kp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import project.kp.model.Uplata;

public interface UplataRepository extends JpaRepository<Uplata, Long> {

	Uplata findByMerchantOrderId(Long merchantOrderId);
	
	List<Uplata> findByTipPlacanja(String tipPlacanja);
	
	List<Uplata> findAllByMerchantTimestampLessThanEqualAndMerchantTimestampGreaterThanEqualAndTipPlacanja(Date max, Date min, String tipPlacanja);

	Uplata findByBtcId(String BtcId);
}
