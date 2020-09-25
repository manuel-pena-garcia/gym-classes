package org.manuelpenagarcia.gymclasses.view.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ReducedMemberDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
}
