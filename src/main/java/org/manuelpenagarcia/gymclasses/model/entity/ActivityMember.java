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
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="activity_member")
public class ActivityMember {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ACTIVITY_ID", nullable=false)
	private Activity activity;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MEMBER_ID", nullable=false)
	private Member member;
	
	@Column(name="START_DATE", nullable=false)
	private Date startDate;

}
