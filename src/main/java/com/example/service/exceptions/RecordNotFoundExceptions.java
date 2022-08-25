package com.example.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Record Not Found")
public class RecordNotFoundExceptions extends RuntimeException {
	String msg;

	public RecordNotFoundExceptions() {
		super();
	}

	public RecordNotFoundExceptions(String msg) {
		super(msg);
		this.msg=msg;
	}

}
