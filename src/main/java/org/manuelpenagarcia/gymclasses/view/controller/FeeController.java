package org.manuelpenagarcia.gymclasses.view.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.manuelpenagarcia.gymclasses.service.FeeService;
import org.manuelpenagarcia.gymclasses.view.dto.FeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fee")
public class FeeController {
	
	@Autowired
	private FeeService feeService;

	@GetMapping("/findAllUnpaid")
	@RolesAllowed("Gym-admin")
	public List<FeeDTO> findAllUnpaid() {
		return this.feeService.findAllUnpaidFees();
	}
	
	@GetMapping("/findAllUnpaidByActivity/{activityId}")
	@RolesAllowed("Gym-admin")
	public List<FeeDTO> findAllUnpaidByActivity(@PathVariable("activityId") Long activityId) {
		return this.feeService.findAllUnpaidFeesByActivity(activityId);
	}
	
	@GetMapping("/findAllUnpaidByMember/{memberId}")
	@RolesAllowed("Gym-admin")
	public List<FeeDTO> findAllUnpaidByMember(@PathVariable("memberId") Long memberId) {
		return this.feeService.findAllUnpaidFeesByMember(memberId);
	}
	
	@PutMapping("/payFee/{feeId}")
	@RolesAllowed({"Gym-admin", "Gym-user"})
	public void payFee(@PathVariable("feeId") Long feeId) {
		this.feeService.payFee(feeId);
	}
}
