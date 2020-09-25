package org.manuelpenagarcia.gymclasses.view.controller;

import java.util.List;

import org.manuelpenagarcia.gymclasses.service.MemberService;
import org.manuelpenagarcia.gymclasses.view.dto.MemberDTO;
import org.manuelpenagarcia.gymclasses.view.dto.ReducedMemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/findAll")
	public List<ReducedMemberDTO> findAll(){
		return this.memberService.findAllMembers();
	}
	
	@GetMapping("/get/{memberId}")
	public MemberDTO getMemberById(@PathVariable("memberId") Long memberId) {
		return this.memberService.getMemberById(memberId);
	}
	
	@PostMapping("/save")
	public MemberDTO save(@RequestBody MemberDTO member) {
		return this.memberService.saveMember(member);
	}
	
	@DeleteMapping("/delete/{memberId}")
	public void delete(@PathVariable("memberId") Long memberId) {
		this.memberService.deleteMember(memberId);
	}
	
	@PutMapping("/addToActivity/{memberId}/{activityId}")
	public void addMemberToActivity(@PathVariable("memberId") Long memberId, @PathVariable("activityId") Long activityId) {
		this.memberService.addMemberToActivity(memberId, activityId);
	}
	
}
