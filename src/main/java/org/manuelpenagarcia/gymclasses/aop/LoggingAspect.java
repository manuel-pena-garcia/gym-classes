package org.manuelpenagarcia.gymclasses.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import lombok.extern.log4j.Log4j2;

@Aspect
@Log4j2
@Configuration
public class LoggingAspect {

	@Before("execution(* org.manuelpenagarcia.gymclasses..*(..))")
	public void logBeforeMethod(JoinPoint joinPoint) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Entering the method ");
		sb.append(joinPoint.getSignature().getName());
		sb.append(" with following parameters: ");
		sb.append(joinPoint.getArgs());
		
		log.info(sb.toString());
	}
	
	@After("execution(* org.manuelpenagarcia.gymclasses..*(..))")
	public void logAfterMethod(JoinPoint joinPoint) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Leaving the method ");
		sb.append(joinPoint.getSignature().getName());
		sb.append(" with following parameters: ");
		sb.append(joinPoint.getArgs());
		
		log.info(sb.toString());
	}
	
	@AfterThrowing(pointcut = "execution(* org.manuelpenagarcia.gymclasses..*(..))", throwing="e")
	public void logAfterExceptionThrowing(JoinPoint joinPoint, Exception e) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Exception thrown in method ");
		sb.append(joinPoint.getSignature().getName());
		sb.append("; due to: ");
		sb.append(e.getLocalizedMessage());
		
		log.info(sb.toString());
	}
}
