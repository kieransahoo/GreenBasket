package com.masai.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> MycommonexcHandler(Exception ie,WebRequest req){
		MyErrorDetails me=new MyErrorDetails();
		me.setTimeStamp(LocalDateTime.now());
		me.setDetails(req.getDescription(false));
		me.setMessage(ie.getMessage());
		return new ResponseEntity<>(me,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> NoHandlerFoundException(NoHandlerFoundException ie,WebRequest req){
		MyErrorDetails me=new MyErrorDetails();
		me.setTimeStamp(LocalDateTime.now());
		me.setDetails(req.getDescription(false));
		me.setMessage(ie.getMessage());
		return new ResponseEntity<>(me,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> NoHandlerFoundException(MethodArgumentNotValidException ie,WebRequest req){
		MyErrorDetails me=new MyErrorDetails();
		me.setTimeStamp(LocalDateTime.now());
		me.setDetails(req.getDescription(false));
		me.setMessage(ie.getMessage());
		return new ResponseEntity<>(me,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(OrderException.class)
	public ResponseEntity<MyErrorDetails> NoHandlerFoundException(OrderException ie,WebRequest req){
		MyErrorDetails me=new MyErrorDetails();
		me.setTimeStamp(LocalDateTime.now());
		me.setDetails(req.getDescription(false));
		me.setMessage(ie.getMessage());
		return new ResponseEntity<>(me,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(CartException.class)
	public ResponseEntity<MyErrorDetails> cartException(CartException ce,WebRequest req){
		MyErrorDetails me=new MyErrorDetails();
		me.setTimeStamp(LocalDateTime.now());
		me.setDetails(req.getDescription(false));
		me.setMessage(ce.getMessage());
		return new ResponseEntity<>(me,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(FeedbackException.class)
	public ResponseEntity<MyErrorDetails> cartException(FeedbackException fe,WebRequest req){
		MyErrorDetails me=new MyErrorDetails();
		me.setTimeStamp(LocalDateTime.now());
		me.setDetails(req.getDescription(false));
		me.setMessage(fe.getMessage());
		return new ResponseEntity<>(me,HttpStatus.BAD_REQUEST);
	}
}
