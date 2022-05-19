package in.surya.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.surya.entity.EligibilityDetailsEntity;

@Repository
public interface EligDtlsRepository extends JpaRepository<EligibilityDetailsEntity, Integer> {

	@Query("select distinct(planName) from EligibilityDetailsEntity")
	public List<String> getUniquePlanNames();

	@Query("select distinct(planStatus) from EligibilityDetailsEntity")
	public List<String> getUniqueStatus();

}
