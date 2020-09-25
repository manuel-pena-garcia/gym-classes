package org.manuelpenagarcia.gymclasses.service;

import java.util.List;

import org.manuelpenagarcia.gymclasses.exception.NoSuchActivityException;
import org.manuelpenagarcia.gymclasses.view.dto.ActivityDTO;
import org.manuelpenagarcia.gymclasses.view.dto.ReducedActivityDTO;

public interface ActivityService {
	
	List<ReducedActivityDTO> findAllActivities();
	
	ActivityDTO getActivityById(Long id) throws NoSuchActivityException;

	ActivityDTO saveActivity(ActivityDTO activityDTO) throws NoSuchActivityException;
	
	void deleteActivity(Long id);
	
	List<ReducedActivityDTO> findAvailableActivities();
}
