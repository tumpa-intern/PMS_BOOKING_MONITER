package com.axisrooms.pms_booking_moniter.repositories;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.axisrooms.pms_booking_moniter.models.PmsPushBooking;

@Repository
public interface PmsPushBookingRepository extends JpaRepository<PmsPushBooking, BigInteger> {

	@Query(value = "SELECT * FROM pms_push_booking WHERE push_status='Failed'and booking_no IS NOT NULL and to_char(generation_time,'yyyy-MM-dd')=?1 ", nativeQuery = true)
	List<PmsPushBooking> getAllTheFailedBookingsUsingDate(String date);

}
