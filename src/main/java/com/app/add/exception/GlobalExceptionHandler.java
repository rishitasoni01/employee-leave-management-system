package com.app.add.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.add.dto.resposnse.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(EmployeeNotFoundException.class)

	public ResponseEntity<ErrorResponse> handleNotFound(EmployeeNotFoundException ex, HttpServletRequest request) {
		ErrorResponse error = ErrorResponse.builder().timeStamp(LocalDateTime.now()).status(404)
				.message(ex.getMessage()).path(request.getRequestURI()).build();

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmployeeAlreadyExistException.class)
	public ResponseEntity<ErrorResponse> handleAlreadyExists(EmployeeAlreadyExistException ex,
			HttpServletRequest request) {
		
		ErrorResponse error = ErrorResponse.builder().timeStamp(LocalDateTime.now()).status(400)
				.message(ex.getMessage()).path(request.getRequestURI()).build();
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> handleAlreadyExists(RuntimeException ex,
			HttpServletRequest request) {
		
		ErrorResponse error = ErrorResponse.builder().timeStamp(LocalDateTime.now()).status(400)
				.message(ex.getMessage()).path(request.getRequestURI()).build();
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	

}
