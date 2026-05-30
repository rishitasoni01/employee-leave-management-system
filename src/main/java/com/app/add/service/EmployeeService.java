package com.app.add.service;

import java.util.List;

import com.app.add.dto.request.EmployeeRequestDto;
import com.app.add.dto.resposnse.EmployeeResponse;

public interface EmployeeService {

	EmployeeResponse saveEmployee(EmployeeRequestDto dto);

	EmployeeResponse getEmployeeById(Long id);
	void  deleteEmployeeById(Long id);
	EmployeeResponse updateEmployee(EmployeeRequestDto dto,Long id);
	List<EmployeeResponse> getAllEmployees();

}
