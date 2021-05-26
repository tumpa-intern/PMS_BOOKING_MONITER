package com.axisrooms.pms_booking_moniter.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.axisrooms.pms_booking_moniter.models.LogsPmsBookings;

@Repository
public interface LogsPmsBookingsRepository extends MongoRepository<LogsPmsBookings, String> {

	@Query("{'accessKey' : ?0}")
	List<LogsPmsBookings> findByAccessKey(String accesskey);

}
