package com.app.add.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.add.enums.LeaveStatus;
import com.app.add.model.LeaveApplication;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Long> {
 List<LeaveApplication>findByEmployeeIdAndLeaveTypeIdAndStatusNot(Long  employeeId ,Long leaveTypeId,LeaveStatus status);
}
