package com.axisrooms.pms_booking_moniter.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Document(collection="LOGS_PMS_BOOKINGS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogsPmsBookings {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private String id;
	
	@Column(name="path")
	private String path;
	
	@Column(name="request")
	private String request;
	
	@Column(name="response")
	private String response;
	
	@Column(name="uuid")
	private String uuid;
	
	@Column(name="pmsId")
	private Integer pmsId;
	
	@Column(name="accessKey")
	private String accessKey;
	
	@Column(name="contentType")
	private String contentType;
	
	@Column(name="respondedTime")
	private LocalDateTime respondedTime;
	
}
