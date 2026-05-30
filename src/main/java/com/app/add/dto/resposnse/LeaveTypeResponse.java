package com.app.add.dto.resposnse;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
//@AllArgsConstructor why we dont use
//@NoArgsConstructor
@Builder
public class LeaveTypeResponse {
	private Long id;
	@NotBlank
	private String name;
	@NotNull
	private Integer maxDays;
	
}
