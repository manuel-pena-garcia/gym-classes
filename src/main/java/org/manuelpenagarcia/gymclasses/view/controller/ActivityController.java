package org.manuelpenagarcia.gymclasses.view.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.manuelpenagarcia.gymclasses.service.ActivityService;
import org.manuelpenagarcia.gymclasses.view.dto.ActivityDTO;
import org.manuelpenagarcia.gymclasses.view.dto.ReducedActivityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activity")
public class ActivityController {
	
	@Autowired
	private ActivityService activityService;
	
	@GetMapping("/findAll")
	@RolesAllowed({"Gym-admin", "Gym-user"})
	public List<ReducedActivityDTO> findAll(){
		return this.activityService.findAllActivities();
	}
	
	@GetMapping("/get/{activityId}")
	@RolesAllowed({"Gym-admin", "Gym-user"})
	public ActivityDTO getActivityById(@PathVariable("activityId") Long activityId) {
		return this.activityService.getActivityById(activityId);
	}
	
	@PostMapping("/save")
	@RolesAllowed("Gym-admin")
	public ActivityDTO save(@RequestBody ActivityDTO activity) {
		return this.activityService.saveActivity(activity);
	}
	
	@DeleteMapping("/delete/{activityId}")
	@RolesAllowed("Gym-admin")
	public void delete(@PathVariable("activityId") Long activityId) {
		this.activityService.deleteActivity(activityId);
	}
}
