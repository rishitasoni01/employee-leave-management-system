package com.app.add.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.add.dto.request.LeaveApplicationRequestDto;
import com.app.add.dto.resposnse.LeaveApplicationResponse;
import com.app.add.service.LeaveApplicationService;

@RestController
@RequestMapping("/api/v2")
public class LeaveApplicationController {
	@Autowired
	private LeaveApplicationService leaveApplicationService;

	@PostMapping("/leaves")
	public ResponseEntity<LeaveApplicationResponse> applyLeave(@RequestBody LeaveApplicationRequestDto dto) {

		LeaveApplicationResponse response = leaveApplicationService.applyLeave(dto);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/leaves")
	public ResponseEntity<List<LeaveApplicationResponse>> viewAllApplications() {
		List<LeaveApplicationResponse> response = leaveApplicationService.viewAllApplications();
		return ResponseEntity.ok(response);
	}

	@GetMapping("leaves/{id}")
	public ResponseEntity<LeaveApplicationResponse> viewApplicationById(@PathVariable Long id) {
		LeaveApplicationResponse response = leaveApplicationService.viewApplicationById(id);

		return ResponseEntity.ok(response);
	}

	@GetMapping("leaves/approve/{id}")
	public ResponseEntity <LeaveApplicationResponse> approveLeave(@PathVariable Long id) {
		LeaveApplicationResponse response = leaveApplicationService.approveLeave(id);
		
		return ResponseEntity.ok(response);
}
	
	@GetMapping("leaves/reject/{id}")
	public ResponseEntity <LeaveApplicationResponse> rejectLeave(@PathVariable Long id) {
		LeaveApplicationResponse response = leaveApplicationService.rejectLeave(id);
		
		return ResponseEntity.ok(response);
}
	
	
	
	
	
}