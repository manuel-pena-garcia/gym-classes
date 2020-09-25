package org.manuelpenagarcia.gymclasses.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.manuelpenagarcia.gymclasses.exception.FullActivityException;
import org.manuelpenagarcia.gymclasses.exception.NoSuchActivityException;
import org.manuelpenagarcia.gymclasses.exception.NoSuchMemberException;
import org.manuelpenagarcia.gymclasses.model.entity.Activity;
import org.manuelpenagarcia.gymclasses.model.entity.ActivityMember;
import org.manuelpenagarcia.gymclasses.model.entity.Member;
import org.manuelpenagarcia.gymclasses.model.repository.ActivityMemberRepository;
import org.manuelpenagarcia.gymclasses.model.repository.ActivityRepository;
import org.manuelpenagarcia.gymclasses.model.repository.MemberRepository;
import org.manuelpenagarcia.gymclasses.service.MemberService;
import org.manuelpenagarcia.gymclasses.view.dto.MemberDTO;
import org.manuelpenagarcia.gymclasses.view.dto.ReducedMemberDTO;
import org.manuelpenagarcia.gymclasses.view.mapper.OrikaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("memberService")
@Transactional(readOnly=true)
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private ActivityMemberRepository activityMemberRepository;
	
	@Autowired
	private ActivityRepository activityRepository;
	
	@Autowired
	private OrikaMapper orikaMapper;
	
	@Override
	public List<ReducedMemberDTO> findAllMembers() {
		return this.orikaMapper.mapAsList(this.memberRepository.findAll(), ReducedMemberDTO.class);
	}

	@Override
	public MemberDTO getMemberById(Long id) throws NoSuchMemberException {
		Member member = this.memberRepository.findById(id).orElseThrow(NoSuchMemberException::new);
		
		MemberDTO memberDTO = this.orikaMapper.map(member, MemberDTO.class);
		
		memberDTO.getActivities().putAll(member.getActivities().stream().collect(Collectors.toMap(ActivityMember::getId, x -> x.getActivity().getName())));
		
		return memberDTO;
	}

	@Override
	@Transactional(readOnly=false)
	public MemberDTO saveMember(MemberDTO memberDTO) throws NoSuchMemberException {
		Member member;
		
		if(memberDTO.getId() == null) {
			member = this.orikaMapper.map(memberDTO, Member.class);
		} else {
			member = this.memberRepository.findById(memberDTO.getId()).orElseThrow(NoSuchMemberException::new);
		
			this.orikaMapper.map(memberDTO, member);
		}
		
		member = this.memberRepository.save(member);
		
		return this.orikaMapper.map(member, MemberDTO.class);
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteMember(Long id) {
		this.memberRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly=false)
	public void addMemberToActivity(Long memberId, Long activityId) throws NoSuchMemberException, NoSuchActivityException, FullActivityException {
		Activity activity = this.activityRepository.findById(activityId).orElseThrow(NoSuchActivityException::new);
		
		if(activity.getCapacity().equals(activity.getMembers().size())) {
			throw new FullActivityException();
		}
		
		ActivityMember activityMember = new ActivityMember();
		
		activityMember.setActivity(activity);
		
		activityMember.setMember(this.memberRepository.findById(memberId).orElseThrow(NoSuchMemberException::new));
		
		activityMember.setStartDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		
		this.activityMemberRepository.save(activityMember);
	}

}
