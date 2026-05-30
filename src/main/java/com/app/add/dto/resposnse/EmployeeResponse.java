package com.app.add.dto.resposnse;

import java.util.Date;

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
public class EmployeeResponse {

	private Long id;
	@NotNull
	private String name;
	@NotNull
	@Email
	private String email;
	@NotNull
	private String department;
	private Date joiningDate;
}
