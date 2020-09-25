package org.manuelpenagarcia.gymclasses.view.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ReducedActivityDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private Integer capacity;
	
	private Float price;
}
