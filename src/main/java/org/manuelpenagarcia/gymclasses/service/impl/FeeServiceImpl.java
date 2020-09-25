package org.manuelpenagarcia.gymclasses.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.manuelpenagarcia.gymclasses.exception.FeeAlreadyPaidException;
import org.manuelpenagarcia.gymclasses.exception.NoSuchActivityException;
import org.manuelpenagarcia.gymclasses.exception.NoSuchFeeException;
import org.manuelpenagarcia.gymclasses.exception.NoSuchMemberException;
import org.manuelpenagarcia.gymclasses.model.entity.Fee;
import org.manuelpenagarcia.gymclasses.model.repository.FeeRepository;
import org.manuelpenagarcia.gymclasses.service.FeeService;
import org.manuelpenagarcia.gymclasses.view.dto.FeeDTO;
import org.manuelpenagarcia.gymclasses.view.mapper.OrikaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.util.Pair;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("feeService")
@Transactional(readOnly=true)
@PropertySource(value = { "application.properties" })
public class FeeServiceImpl implements FeeService {
	
	@Autowired
	private FeeRepository feeRepository;
	
	@Autowired
	private OrikaMapper orikaMapper;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${notify.receiver}")
	private String receiver;
	
	@Value("${spring.mail.username}")
	private String sender;

	@Override
	public List<FeeDTO> findAllUnpaidFees() {
		return this.feeRepository.findByPaymentDateIsNull().stream().map(x -> this.mapWithSideFields(x)).collect(Collectors.toList());
	}

	@Override
	public List<FeeDTO> findAllUnpaidFeesByActivity(Long id) throws NoSuchActivityException {
		return this.feeRepository.findByActivityMemberActivityIdAndPaymentDateIsNull(id).stream().map(x -> this.mapWithSideFields(x)).collect(Collectors.toList());
	}

	@Override
	public List<FeeDTO> findAllUnpaidFeesByMember(Long id) throws NoSuchMemberException {
		return this.feeRepository.findByActivityMemberMemberIdAndPaymentDateIsNull(id).stream().map(x -> this.mapWithSideFields(x)).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly=false)
	public void payFee(Long id) throws NoSuchFeeException, FeeAlreadyPaidException {
		Fee fee = this.feeRepository.findById(id).orElseThrow(NoSuchFeeException::new);
		
		if(fee.getPaymentDate() != null) {
			throw new FeeAlreadyPaidException();
		}
		
		fee.setPaymentDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		
		this.feeRepository.save(fee);
		
		sendMailToConfirmPayment(fee);
	}
	
	private FeeDTO mapWithSideFields(Fee fee) {
		FeeDTO feeDTO = this.orikaMapper.map(fee, FeeDTO.class);
		
		feeDTO.setActivity(Pair.of(fee.getActivityMember().getActivity().getId(), fee.getActivityMember().getActivity().getName()));
		feeDTO.setMember(Pair.of(fee.getActivityMember().getMember().getId(), fee.getActivityMember().getMember().getName()));
		
		return feeDTO;
	}
	
	private void sendMailToConfirmPayment(Fee fee) {
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom(this.sender);
        message.setTo(this.receiver); 
        message.setSubject("Fee Payment - Gym App - Spring Boot Example"); 
        
        StringBuilder sb = new StringBuilder("The user ");
        sb.append(fee.getActivityMember().getMember().getName());
        sb.append(" has paid ");
        sb.append(fee.getMembershipFee());
        sb.append(" € as ");
        sb.append(fee.getMonth());
        sb.append("/");
        sb.append(fee.getYear());
        sb.append(" membership fee.");
        
        message.setText(sb.toString());
        
        mailSender.send(message);
	}

}
