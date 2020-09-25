package org.manuelpenagarcia.gymclasses.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Activity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private Integer capacity;
	
	private Float price;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="activity")
	private List<ActivityMember> members;
}
