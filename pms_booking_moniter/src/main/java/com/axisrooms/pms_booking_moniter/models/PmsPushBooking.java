package com.axisrooms.pms_booking_moniter.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pms_push_booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PmsPushBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pms_id")
	private Integer pmsId;

	@Column(name = "booking_no")
	private String bookingNo;

	@Column(name = "channel_id")
	private Integer channelId;

	@Column(name = "generation_time")
	private LocalDateTime generationTime;

	@Column(name = "cancellation_time")
	private LocalDateTime cancellationTime;

	@Column(name = "last_modified_time")
	private LocalDateTime lastModifiedTime;

	@Column(name = "status")
	private String status;

	@Column(name = "push_status")
	private String pushStatus;

}
