package com.app.add.dto.resposnse;

import java.time.LocalDate;

import com.app.add.enums.LeaveStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LeaveApplicationResponse {
	private Long id;
	private Long employeeId;
	private Long leaveTypeId;
	private LocalDate startDate;
	private LocalDate endDate;
	private String reason;
	@Enumerated(EnumType.STRING)
	private LeaveStatus status;
}
