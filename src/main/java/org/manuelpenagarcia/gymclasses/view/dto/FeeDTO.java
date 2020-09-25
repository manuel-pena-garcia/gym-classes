package org.manuelpenagarcia.gymclasses.view.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.util.Pair;

import lombok.Data;

@Data
public class FeeDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Integer month;
	
	private Integer year;
	
	private Float membershipFee;
	
	private Date startDate;
	
	private Date paymentDate;
	
	private Pair<Long, String> activity;
	
	private Pair<Long, String> member;

}
