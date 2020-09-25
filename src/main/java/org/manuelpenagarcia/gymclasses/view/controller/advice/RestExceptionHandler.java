package org.manuelpenagarcia.gymclasses.view.controller.advice;

import org.manuelpenagarcia.gymclasses.exception.FeeAlreadyPaidException;
import org.manuelpenagarcia.gymclasses.exception.FullActivityException;
import org.manuelpenagarcia.gymclasses.exception.NoSuchActivityException;
import org.manuelpenagarcia.gymclasses.exception.NoSuchFeeException;
import org.manuelpenagarcia.gymclasses.exception.NoSuchMemberException;
import org.manuelpenagarcia.gymclasses.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private ActivityService activityService;
	
	@ExceptionHandler({FeeAlreadyPaidException.class, 
		NoSuchActivityException.class, 
		NoSuchFeeException.class, 
		NoSuchMemberException.class})
	public ResponseEntity<Object> handleExceptionInternal(Exception e, WebRequest wr) {
		return handleExceptionInternal(e, e.getLocalizedMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, wr);
	}

	@ExceptionHandler({FullActivityException.class})
	public ResponseEntity<Object> handleExceptionFullActivity(Exception e, WebRequest wr) {
		StringBuilder body = new StringBuilder();
		
		body.append(e.getLocalizedMessage());
		body.append("\n\n");
		body.append("We suggest this alternative activities, which are available now: ");
		
		this.activityService.findAvailableActivities().stream().map(x -> x.getName()).forEach(x -> {
			body.append(x);
			body.append(", ");
		});
		
		body.delete(body.length() - 2, body.length());
		
		
		return handleExceptionInternal(e, body.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, wr);
	}
}
