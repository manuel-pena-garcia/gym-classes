package org.manuelpenagarcia.gymclasses.scheduling;

import org.manuelpenagarcia.gymclasses.scheduling.jobs.CreateFeesJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"application.properties"})
public class QuartzJobConfiguration {
	
	@Value("${cron.createFees}")
	private String createFeesCronExpression;

	@Bean
	public JobDetail createFeesJobDetail() {
		return JobBuilder.newJob(CreateFeesJob.class).withIdentity("createFeesJobDetail").storeDurably().build();
	}
	
	@Bean
	public Trigger createFeesTrigger(JobDetail createFeesJobDetail) {
		return TriggerBuilder.newTrigger().forJob(createFeesJobDetail)
				.withIdentity("createFeesTrigger")
				.withSchedule(CronScheduleBuilder.cronSchedule(createFeesCronExpression))
				.build();
	}
}
