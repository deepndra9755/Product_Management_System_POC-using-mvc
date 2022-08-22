package com.example.controller.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not such kind of item is there")
public class RecordNotFoundExceptions extends RuntimeException {

	public RecordNotFoundExceptions(String msg) {
		super(msg);
	}

	public RecordNotFoundExceptions() {
		super();
	}
}
