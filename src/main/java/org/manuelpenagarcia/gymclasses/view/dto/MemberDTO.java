package org.manuelpenagarcia.gymclasses.view.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public class MemberDTO extends ReducedMemberDTO {
	
	private static final long serialVersionUID = 1L;
	
	private Map<Long, String> activities = new HashMap<>();
}
