package org.manuelpenagarcia.gymclasses.scheduling.jobs;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.manuelpenagarcia.gymclasses.model.entity.Fee;
import org.manuelpenagarcia.gymclasses.model.repository.ActivityMemberRepository;
import org.manuelpenagarcia.gymclasses.model.repository.FeeRepository;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=false)
public class CreateFeesJob implements Job {

	@Autowired
	private FeeRepository feeRepository;
	
	@Autowired
	private ActivityMemberRepository activityMemberRepository;
	
	/*
	 * Will create a fee for the current month, if not exists, for every member that joins an activity
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Integer currentMonth = LocalDate.now().getMonthValue();
		
		Integer currentYear = LocalDate.now().getYear();
		
		this.activityMemberRepository.findAll().stream().forEach(x -> {
			if(this.feeRepository.findByActivityMemberAndMonthAndYear(x, currentMonth, currentYear).isEmpty()) {
				Fee fee = new Fee();
				
				fee.setActivityMember(x);
				fee.setMembershipFee(x.getActivity().getPrice());
				fee.setMonth(currentMonth);
				fee.setYear(currentYear);
				fee.setStartDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				
				this.feeRepository.save(fee);
			}
		});
	}

}
