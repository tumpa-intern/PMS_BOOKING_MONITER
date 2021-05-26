package com.axisrooms.pms_booking_moniter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.axisrooms.pms_booking_moniter.models.Pms;

@Repository
public interface PmsRepository extends JpaRepository<Pms, Integer>{

	@Query(value="select name from PMS where id=?1", nativeQuery = true)
	String getPmsNameById(Integer pmsid);

}
