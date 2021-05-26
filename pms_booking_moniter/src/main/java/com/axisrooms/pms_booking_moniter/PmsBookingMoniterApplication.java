package com.axisrooms.pms_booking_moniter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PmsBookingMoniterApplication {

    public static void main(String[] args) {
	SpringApplication.run(PmsBookingMoniterApplication.class, args);
    }

}
