package com.app.add.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.add.dto.request.LeaveTypeRequestDto;
import com.app.add.dto.resposnse.LeaveTypeResponse;
import com.app.add.service.LeaveTypeService;

@RestController
@RequestMapping("/api/v1")
public class LeaveTypeController {
	@Autowired
	private LeaveTypeService leaveTypeService;

	@PostMapping("/leave")
	public ResponseEntity<LeaveTypeResponse> saveLeaveType(@RequestBody LeaveTypeRequestDto dto) {
		LeaveTypeResponse response = leaveTypeService.saveLeaveType(dto);
		return ResponseEntity.ok(response);
	}
	@GetMapping("/leave")
	public ResponseEntity<List<LeaveTypeResponse>> getAllLeaveType(){
		List<LeaveTypeResponse> response = leaveTypeService.getAllLeaveType();
		return ResponseEntity.ok(response);
	}
	
	
}
