package com.app.add.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.add.dto.request.LeaveTypeRequestDto;
import com.app.add.dto.resposnse.LeaveTypeResponse;
import com.app.add.model.LeaveType;
import com.app.add.repository.LeaveTypeRepository;
import com.app.add.service.LeaveTypeService;

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {

	@Autowired
	private LeaveTypeRepository leaveTypeRepo;

	@Override
	public LeaveTypeResponse saveLeaveType(LeaveTypeRequestDto dto) {

		LeaveType leaveType = LeaveType.builder().name(dto.getName()).maxDays(dto.getMaxDays()).build();
		LeaveType saveLeaveType = leaveTypeRepo.save(leaveType);
		LeaveTypeResponse response = LeaveTypeResponse.builder().id(saveLeaveType.getId()).name(saveLeaveType.getName())
				.maxDays(saveLeaveType.getMaxDays()).build();

		return response;

	}

	@Override
	public List<LeaveTypeResponse> getAllLeaveType() {
		List<LeaveType> leaveType = leaveTypeRepo.findAll();
		List<LeaveTypeResponse> response = leaveType.stream()
				.map(obj -> LeaveTypeResponse.builder().id(obj.getId()).name(obj.getName()).maxDays(obj.getMaxDays()).build()).toList();

		return response;
	}

	

}
