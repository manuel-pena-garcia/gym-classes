package org.manuelpenagarcia.gymclasses.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.manuelpenagarcia.gymclasses.exception.NoSuchActivityException;
import org.manuelpenagarcia.gymclasses.model.entity.Activity;
import org.manuelpenagarcia.gymclasses.model.entity.ActivityMember;
import org.manuelpenagarcia.gymclasses.model.repository.ActivityRepository;
import org.manuelpenagarcia.gymclasses.service.ActivityService;
import org.manuelpenagarcia.gymclasses.view.dto.ActivityDTO;
import org.manuelpenagarcia.gymclasses.view.dto.ReducedActivityDTO;
import org.manuelpenagarcia.gymclasses.view.mapper.OrikaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("activityService")
@Transactional(readOnly=true)
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityRepository activityRepository;
	
	@Autowired
	private OrikaMapper orikaMapper;
	
	@Override
	public List<ReducedActivityDTO> findAllActivities() {
		return this.orikaMapper.mapAsList(this.activityRepository.findAll(), ReducedActivityDTO.class);
	}

	@Override
	public ActivityDTO getActivityById(Long id) throws NoSuchActivityException {
		Activity activity = this.activityRepository.findById(id).orElseThrow(NoSuchActivityException::new);
		
		ActivityDTO activityDTO = this.orikaMapper.map(activity, ActivityDTO.class);

		activityDTO.getMembers().putAll(activity.getMembers().stream().collect(Collectors.toMap(ActivityMember::getId, x -> x.getActivity().getName())));
		
		return activityDTO;
	}

	@Override
	@Transactional(readOnly=false)
	public ActivityDTO saveActivity(ActivityDTO activityDTO) throws NoSuchActivityException {
		Activity activity;
		
		if(activityDTO.getId() == null) {
			activity = this.orikaMapper.map(activityDTO, Activity.class);
		} else {
			activity = this.activityRepository.findById(activityDTO.getId()).orElseThrow(NoSuchActivityException::new);
			
			this.orikaMapper.map(activityDTO, activity);
		}
		
		activity = this.activityRepository.save(activity);
		
		return this.orikaMapper.map(activity, ActivityDTO.class);
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteActivity(Long id) {
		this.activityRepository.deleteById(id);
	}

	@Override
	public List<ReducedActivityDTO> findAvailableActivities() {
		return this.activityRepository.findAll().stream().filter(x -> x.getCapacity() > x.getMembers().size()).map(x -> this.orikaMapper.map(x, ReducedActivityDTO.class)).collect(Collectors.toList());
	}

	
}
