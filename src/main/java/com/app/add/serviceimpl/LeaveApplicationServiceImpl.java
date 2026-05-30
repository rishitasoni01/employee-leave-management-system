package com.app.add.serviceimpl;

import java.time.temporal.ChronoUnit;
import java.util.List;

import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.add.dto.request.LeaveApplicationRequestDto;
import com.app.add.dto.resposnse.LeaveApplicationResponse;
import com.app.add.enums.LeaveStatus;
import com.app.add.exception.EmployeeNotFoundException;
import com.app.add.model.Employee;
import com.app.add.model.LeaveApplication;
import com.app.add.model.LeaveType;
import com.app.add.repository.EmployeeRepository;
import com.app.add.repository.LeaveApplicationRepository;
import com.app.add.repository.LeaveTypeRepository;
import com.app.add.service.LeaveApplicationService;

@Service
public class LeaveApplicationServiceImpl implements LeaveApplicationService {

	@Autowired
	private LeaveApplicationRepository leaveApplicationRepo;
	@Autowired
	private LeaveTypeRepository leaveTypeRepo;
	@Autowired
	private EmployeeRepository empRepo;

	@Override
	public LeaveApplicationResponse applyLeave(LeaveApplicationRequestDto dto) {
		// TODO Auto-generated method stub
		if (dto.getEmployeeId() == null) {
			throw new EmployeeNotFoundException("employee doesn't exist");
		}

		if (dto.getLeaveTypeId() == null) {
			throw new RuntimeException("leave type doesn't exist");
		}

		Employee emp = empRepo.findById(dto.getEmployeeId())
				.orElseThrow(() -> new EmployeeNotFoundException("employee is not existed"));

		LeaveType leaveType = leaveTypeRepo.findById(dto.getLeaveTypeId())
				.orElseThrow(() -> new EmployeeNotFoundException("leave  is not existed"));

		Integer leaveDays = leaveType.getMaxDays();

		long countLeaveDays = ChronoUnit.DAYS.between(dto.getStartDate(), dto.getEndDate()) + 1;

		if (dto.getStartDate().isAfter(dto.getEndDate())) {
			throw new RuntimeException("start date can not be after");
		}

		List<LeaveApplication> notRejectedleaveApplication = leaveApplicationRepo
				.findByEmployeeIdAndLeaveTypeIdAndStatusNot(dto.getEmployeeId(), dto.getLeaveTypeId(),
						LeaveStatus.REJECTED);
		boolean anyMatch = notRejectedleaveApplication.stream()
				.anyMatch(obj -> !(dto.getEndDate().isBefore(obj.getStartDate())
						|| dto.getStartDate().isAfter(obj.getEndDate())));
		// date is E-5th may and database S-6th may date,now i peovide S-2th may and
		// database E-1 st may
		if (anyMatch) {
			throw new RuntimeException("leave already exist betwwn alreay applied");
		}

		if (leaveDays < countLeaveDays) {
			throw new RuntimeException("this is not valid dates");
		}

		LeaveApplication leaveApplicaton = LeaveApplication.builder().employee(emp).leaveType(leaveType)
				.startDate(dto.getStartDate()).endDate(dto.getEndDate()).reason(dto.getReason())
				.status(LeaveStatus.PENDING).build();

		LeaveApplication saveLeaveApplicaton = leaveApplicationRepo.save(leaveApplicaton);

		LeaveApplicationResponse response = LeaveApplicationResponse.builder().id(saveLeaveApplicaton.getId())

				.employeeId(saveLeaveApplicaton.getEmployee().getId())
				.leaveTypeId(saveLeaveApplicaton.getLeaveType().getId()).startDate(saveLeaveApplicaton.getStartDate())
				.endDate(saveLeaveApplicaton.getEndDate()).reason(saveLeaveApplicaton.getReason())
				.status(saveLeaveApplicaton.getStatus()).build();

		return response;
	}

	@Override
	public List<LeaveApplicationResponse> viewAllApplications() {
		List<LeaveApplication> leaveApplication = leaveApplicationRepo.findAll();

		List<LeaveApplicationResponse> reaponse = leaveApplication.stream()
				.map(obj -> LeaveApplicationResponse.builder().id(obj.getId()).leaveTypeId(obj.getLeaveType().getId())
						.employeeId(obj.getEmployee().getId()).startDate(obj.getStartDate()).endDate(obj.getEndDate())
						.reason(obj.getReason()).status(obj.getStatus()).build())
				.toList();
		return reaponse;

	}

	@Override
	public LeaveApplicationResponse viewApplicationById(Long id) {

		LeaveApplication leaveApplication = leaveApplicationRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("leave id Doesn't exist"));

		LeaveApplicationResponse response = LeaveApplicationResponse.builder().id(leaveApplication.getId())
				.employeeId(leaveApplication.getEmployee().getId()).leaveTypeId(leaveApplication.getLeaveType().getId())
				.startDate(leaveApplication.getStartDate()).endDate(leaveApplication.getEndDate())
				.reason(leaveApplication.getReason()).status(leaveApplication.getStatus()).build();

		return response;
	}

	public LeaveApplicationResponse approveLeave(Long id) {
		LeaveApplication leaveApplication = leaveApplicationRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("leave id Doesn't exist"));

		if (leaveApplication.getStatus().equals(LeaveStatus.REJECTED)) {
			throw new RuntimeException("not applicable for approve");
		}

		leaveApplication.setStatus(LeaveStatus.APPROVED);
		LeaveApplication saveLeaveApplicaton = leaveApplicationRepo.save(leaveApplication);

		LeaveApplicationResponse response = LeaveApplicationResponse.builder().id(saveLeaveApplicaton.getId())

				.employeeId(saveLeaveApplicaton.getEmployee().getId())
				.leaveTypeId(saveLeaveApplicaton.getLeaveType().getId()).startDate(saveLeaveApplicaton.getStartDate())
				.endDate(saveLeaveApplicaton.getEndDate()).reason(saveLeaveApplicaton.getReason())
				.status(saveLeaveApplicaton.getStatus()).build();

		return response;

	}

	public LeaveApplicationResponse rejectLeave(Long id) {
		LeaveApplication leaveApplication = leaveApplicationRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("leave id Doesn't exist"));

		leaveApplication.setStatus(LeaveStatus.REJECTED);
		LeaveApplication saveLeaveApplicaton = leaveApplicationRepo.save(leaveApplication);

		LeaveApplicationResponse response = LeaveApplicationResponse.builder().id(saveLeaveApplicaton.getId())

				.employeeId(saveLeaveApplicaton.getEmployee().getId())
				.leaveTypeId(saveLeaveApplicaton.getLeaveType().getId()).startDate(saveLeaveApplicaton.getStartDate())
				.endDate(saveLeaveApplicaton.getEndDate()).reason(saveLeaveApplicaton.getReason())
				.status(saveLeaveApplicaton.getStatus()).build();

		return response;

	}

}
