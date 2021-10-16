package br.com.fiap.epictask.exception;

public class TaskNotFoundException extends RuntimeException {

	public TaskNotFoundException(String message) {
		super(message);
	}

}
