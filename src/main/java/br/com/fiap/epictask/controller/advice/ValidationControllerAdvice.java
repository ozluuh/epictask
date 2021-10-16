package br.com.fiap.epictask.controller.advice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestControllerAdvice
public class ValidationControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public List<ValidationFieldError> handle(MethodArgumentNotValidException e) {
		log.error("Error Raised: {}", e.getMessage());

		List<ValidationFieldError> errors = new ArrayList<>();

		e.getBindingResult()
			.getFieldErrors()
			.forEach(error ->
				errors
				.add(new ValidationFieldError(error.getField(), error.getDefaultMessage(), error.getRejectedValue()))
			);

		return errors;
	}
}
