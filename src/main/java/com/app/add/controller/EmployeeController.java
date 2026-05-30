package com.app.add.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.add.dto.request.EmployeeRequestDto;
import com.app.add.dto.resposnse.EmployeeResponse;
import com.app.add.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeSevice;

	@PostMapping("/employees")
	public ResponseEntity<EmployeeResponse> saveEmployee(@RequestBody EmployeeRequestDto dto) {

		EmployeeResponse response = employeeSevice.saveEmployee(dto);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
		EmployeeResponse response = employeeSevice.getEmployeeById(id);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
		employeeSevice.deleteEmployeeById(id);
		return ResponseEntity.ok("deleted employee");

	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<EmployeeResponse> updateEmployee(@RequestBody EmployeeRequestDto dto, @PathVariable Long id) {
		EmployeeResponse response =employeeSevice.updateEmployee(dto, id);
		return ResponseEntity.ok(response);

	}

	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeResponse>> getAllEmployee() {
		List<EmployeeResponse> response = employeeSevice.getAllEmployees();
		return ResponseEntity.ok(response);
	}

}
