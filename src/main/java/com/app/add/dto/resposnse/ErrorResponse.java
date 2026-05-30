package com.app.add.dto.resposnse;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
	private LocalDateTime timeStamp;
	private int status;
	private String message;
	private String path;

}
