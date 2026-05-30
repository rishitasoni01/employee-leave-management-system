package com.app.add.dto.request;

import java.time.LocalDate;

import com.app.add.enums.LeaveStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeaveApplicationRequestDto {

	private  Long  employeeId;
	private Long leaveTypeId;
	private LocalDate startDate;
	private LocalDate endDate;
	private String reason;
	@Enumerated(EnumType.STRING)
	private LeaveStatus status;

}
