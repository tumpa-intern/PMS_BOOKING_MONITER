package com.axisrooms.pms_booking_moniter.models;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name="PMS")
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class Pms {

	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
}
