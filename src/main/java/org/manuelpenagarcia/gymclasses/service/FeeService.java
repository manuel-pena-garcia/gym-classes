package org.manuelpenagarcia.gymclasses.service;

import java.util.List;

import org.manuelpenagarcia.gymclasses.exception.FeeAlreadyPaidException;
import org.manuelpenagarcia.gymclasses.exception.NoSuchActivityException;
import org.manuelpenagarcia.gymclasses.exception.NoSuchFeeException;
import org.manuelpenagarcia.gymclasses.exception.NoSuchMemberException;
import org.manuelpenagarcia.gymclasses.view.dto.FeeDTO;

public interface FeeService {

	List<FeeDTO> findAllUnpaidFees();
	
	List<FeeDTO> findAllUnpaidFeesByActivity(Long id) throws NoSuchActivityException;
	
	List<FeeDTO> findAllUnpaidFeesByMember(Long id) throws NoSuchMemberException;
	 
	void payFee(Long id) throws NoSuchFeeException, FeeAlreadyPaidException;
}
