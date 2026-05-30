package com.app.add.dto.request;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//rishita
public class EmployeeRequestDto {
	@NotNull(message="")
	private String name;
	@NotNull
	@Email
	private String email;
	@NotNull
	private String department;
	
	private Date joiningDate;

}
