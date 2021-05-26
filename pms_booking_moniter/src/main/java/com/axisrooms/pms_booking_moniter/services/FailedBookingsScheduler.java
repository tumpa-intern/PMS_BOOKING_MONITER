package com.axisrooms.pms_booking_moniter.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class FailedBookingsScheduler {

    @Autowired
    ServiceClass serviceRepository;

    private static final Logger log = LoggerFactory.getLogger(FailedBookingsScheduler.class);

    @Scheduled(cron = "0 16 16 * * *", zone = "IST")
    public void reportCurrentTime() {
	log.info("Check the email now.... Time of generation is over.");
	String date = null;
	serviceRepository.returnAllFailedResults(date);
    }

}
