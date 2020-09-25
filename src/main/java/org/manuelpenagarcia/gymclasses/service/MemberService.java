package org.manuelpenagarcia.gymclasses.service;

import java.util.List;

import org.manuelpenagarcia.gymclasses.exception.FullActivityException;
import org.manuelpenagarcia.gymclasses.exception.NoSuchActivityException;
import org.manuelpenagarcia.gymclasses.exception.NoSuchMemberException;
import org.manuelpenagarcia.gymclasses.view.dto.MemberDTO;
import org.manuelpenagarcia.gymclasses.view.dto.ReducedMemberDTO;

public interface MemberService {

	List<ReducedMemberDTO> findAllMembers();
	
	MemberDTO getMemberById(Long id) throws NoSuchMemberException;
	
	MemberDTO saveMember(MemberDTO memberDTO) throws NoSuchMemberException;
	
	void deleteMember(Long id);
	
	void addMemberToActivity(Long memberId, Long activityId) throws NoSuchMemberException, NoSuchActivityException, FullActivityException;
}
