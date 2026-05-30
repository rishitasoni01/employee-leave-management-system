package com.app.add.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.add.dto.request.EmployeeRequestDto;
import com.app.add.dto.resposnse.EmployeeResponse;
import com.app.add.exception.EmployeeAlreadyExistException;
import com.app.add.exception.EmployeeNotFoundException;
import com.app.add.model.Employee;
import com.app.add.repository.EmployeeRepository;
import com.app.add.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository empRepo;

	public EmployeeResponse saveEmployee(EmployeeRequestDto dto) {

		if (empRepo.existsByEmail(dto.getEmail())) {
			throw new EmployeeAlreadyExistException("employee already exist");
		}
		Employee emp = Employee.builder().name(dto.getName()).email(dto.getEmail()).department(dto.getDepartment())
				.joiningDate(dto.getJoiningDate()).build();
		Employee saveEmployee = empRepo.save(emp);
		EmployeeResponse response = EmployeeResponse.builder().id(saveEmployee.getId()).name(saveEmployee.getName())
				.email(saveEmployee.getEmail()).department(saveEmployee.getDepartment())
				.joiningDate(saveEmployee.getJoiningDate()).build();

		return response;
	}

	public EmployeeResponse getEmployeeById(Long id) {

		Employee employee = empRepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException("employee not found"));
		EmployeeResponse response = EmployeeResponse.builder().id(employee.getId()).name(employee.getName())
				.department(employee.getDepartment()).email(employee.getEmail()).joiningDate(employee.getJoiningDate())
				.build();
		return response;
	}

	public void deleteEmployeeById(Long id) {
		empRepo.deleteById(id);
	}

	public List<EmployeeResponse> getAllEmployees() {
		List<Employee> employee = empRepo.findAll();
		List<EmployeeResponse> respone = employee.stream()
				.map(obj -> EmployeeResponse.builder().id(obj.getId()).name(obj.getName()).email(obj.getEmail())
						.department(obj.getDepartment()).joiningDate(obj.getJoiningDate()).build())
				.toList();
		return respone;
	}

	@Override
	public EmployeeResponse updateEmployee(EmployeeRequestDto dto, Long id) {
		Employee emp = empRepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException("employee doen't exist"));
		// EmployeeResponse response=
	Employee emp1=updateApply(emp, dto);

		Employee emps = empRepo.save(emp1);

		EmployeeResponse response = EmployeeResponse.builder().id(emps.getId()).name(emps.getName())
				.email(emps.getEmail()).department(emps.getDepartment()).joiningDate(dto.getJoiningDate()).build();

		return response;

	}

	public Employee updateApply(Employee emp, EmployeeRequestDto dto) {

	return	Employee.builder().name(dto.getName()).id(emp.getId()).email(dto.getEmail()).department(dto.getDepartment())
				.joiningDate(dto.getJoiningDate()).build();

	}
}
