package com.axisrooms.pms_booking_moniter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.axisrooms.pms_booking_moniter.models.ChannelOTA;

@Repository
public interface ChannelOtaRepository extends JpaRepository<ChannelOTA, Integer> {

	@Query(value="select ota_name from channel_ota where channel_id=?1", nativeQuery = true)
	String getNameById(Integer channelid);

}
