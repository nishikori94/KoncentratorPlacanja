package project.kp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.kp.model.Uplata;

public interface UplataRepository extends JpaRepository<Uplata, Long> {

	Uplata findByMerchantOrderId(Long merchantOrderId);

}
