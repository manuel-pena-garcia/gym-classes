package org.manuelpenagarcia.gymclasses.model.repository;

import java.util.List;

import org.manuelpenagarcia.gymclasses.model.entity.ActivityMember;
import org.manuelpenagarcia.gymclasses.model.entity.Fee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeeRepository extends JpaRepository<Fee, Long> {
	
	List<Fee> findByPaymentDateIsNull();

	List<Fee> findByActivityMemberActivityIdAndPaymentDateIsNull(Long activityId);
	
	List<Fee> findByActivityMemberMemberIdAndPaymentDateIsNull(Long memberId);
	
	List<Fee> findByActivityMemberAndMonthAndYear(ActivityMember activityMember, Integer month, Integer year);
}
