package com.example.controller.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PARTIAL_CONTENT,reason ="some of the record in not aval")
public class ParsialRecordExceptions extends RuntimeException {

}
