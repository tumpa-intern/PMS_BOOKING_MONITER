package com.axisrooms.pms_booking_moniter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axisrooms.pms_booking_moniter.services.FailedBookingsScheduler;
import com.axisrooms.pms_booking_moniter.services.ServiceClass;

@RestController
@RequestMapping(value = "/axisrooms/pms")
public class Controller {

    @Autowired
    FailedBookingsScheduler scheduler;

    @Autowired
    ServiceClass serviceRepository;

    @GetMapping(value = "/fetchFailedBookings")
    public String getFailedBookings(@RequestParam(required = false) String date) throws Exception {

	serviceRepository.returnAllFailedResults(date);
	return "PLEASE CHECK THE FILE, SENT TO MAIL";

    }

}