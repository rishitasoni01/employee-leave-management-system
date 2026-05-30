package com.app.add.service;

import java.util.List;

import com.app.add.dto.request.LeaveApplicationRequestDto;
import com.app.add.dto.resposnse.LeaveApplicationResponse;

public interface LeaveApplicationService {
	LeaveApplicationResponse applyLeave(LeaveApplicationRequestDto dto);
	
	List<LeaveApplicationResponse> viewAllApplications();
	LeaveApplicationResponse viewApplicationById(Long id);
	LeaveApplicationResponse approveLeave(Long id);
	LeaveApplicationResponse rejectLeave(Long id);
}
