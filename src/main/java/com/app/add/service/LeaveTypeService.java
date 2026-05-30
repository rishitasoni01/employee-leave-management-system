package com.app.add.service;

import java.util.List;

import com.app.add.dto.request.LeaveTypeRequestDto;
import com.app.add.dto.resposnse.LeaveTypeResponse;

public interface LeaveTypeService {

	LeaveTypeResponse saveLeaveType(LeaveTypeRequestDto dto);

	List<LeaveTypeResponse> getAllLeaveType();
}
