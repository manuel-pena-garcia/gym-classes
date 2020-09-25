package org.manuelpenagarcia.gymclasses.view.mapper;

import org.manuelpenagarcia.gymclasses.model.entity.Activity;
import org.manuelpenagarcia.gymclasses.model.entity.Fee;
import org.manuelpenagarcia.gymclasses.model.entity.Member;
import org.manuelpenagarcia.gymclasses.view.dto.ActivityDTO;
import org.manuelpenagarcia.gymclasses.view.dto.FeeDTO;
import org.manuelpenagarcia.gymclasses.view.dto.MemberDTO;
import org.manuelpenagarcia.gymclasses.view.dto.ReducedActivityDTO;
import org.manuelpenagarcia.gymclasses.view.dto.ReducedMemberDTO;
import org.springframework.stereotype.Component;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class OrikaMapper extends ConfigurableMapper {

	protected void configure(MapperFactory mapperFactory) {
		
		mapperFactory.classMap(Activity.class, ReducedActivityDTO.class).byDefault().register();
		
		mapperFactory.classMap(Activity.class, ActivityDTO.class).use(Activity.class, ReducedActivityDTO.class).exclude("members").byDefault().register();
		
		mapperFactory.classMap(Member.class, ReducedMemberDTO.class).byDefault().register();
		
		mapperFactory.classMap(Member.class, MemberDTO.class).use(Member.class, ReducedMemberDTO.class).exclude("activities").byDefault().register();
		
		mapperFactory.classMap(Fee.class, FeeDTO.class).exclude("activity").exclude("member").byDefault().register();
	}
}
