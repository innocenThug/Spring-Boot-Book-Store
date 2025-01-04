package com.onlinestore.Book.exception;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(BookNotFoundException.class)
	public String handleBookNotFoundException(BookNotFoundException e) {
		return e.getMsg();
	}
	@ExceptionHandler(BookNotSavedException.class)
	public String handleBookSavedException(BookNotFoundException e) {
		return e.getMsg();
	}
}