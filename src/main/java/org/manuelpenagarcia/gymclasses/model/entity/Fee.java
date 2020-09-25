package org.manuelpenagarcia.gymclasses.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Fee {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Integer month;
	
	private Integer year;
	
	@Column(name="MEMBERSHIP_FEE")
	private Float membershipFee;
	
	@Column(name="START_DATE", nullable=true)
	private Date startDate;
	
	@Column(name="PAYMENT_DATE", nullable=true)
	private Date paymentDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ACTIVITY_MEMBER_ID", nullable=false)
	private ActivityMember activityMember;
}
