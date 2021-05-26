package com.axisrooms.pms_booking_moniter.models;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="channel_ota")
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class ChannelOTA {

	@Id
	@Column(name = "channel_id")
	private Integer channelId;
	
	@Column(name = "ota_name")
	private String otaName;
	
}
