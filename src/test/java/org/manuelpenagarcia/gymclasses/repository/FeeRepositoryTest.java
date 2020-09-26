package org.manuelpenagarcia.gymclasses.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.manuelpenagarcia.gymclasses.model.repository.FeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FeeRepositoryTest {

	@Autowired
	private FeeRepository feeRepository;
	
	@Test
	public void whenFindByPaymentDateIsNull_shouldReturnFeesWithPaymentDateNull() {
		assertThat(this.feeRepository.findByPaymentDateIsNull().stream().filter(x -> x.getPaymentDate() != null).count()).isEqualTo(0L);
	}
	
	@Test
	public void whenFindByActivityMemberActivityIdAndPaymentDateIsNull_firstActivity_shouldReturnOneValue() {
		Long activityId = 2L;
		
		assertThat(this.feeRepository.findByActivityMemberActivityIdAndPaymentDateIsNull(activityId).size()).isEqualTo(2);
	}
}
