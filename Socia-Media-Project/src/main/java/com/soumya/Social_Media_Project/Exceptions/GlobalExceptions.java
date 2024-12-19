package com.soumya.Social_Media_Project.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> otherExceptionHandler(Exception e, WebRequest req){
        ErrorDetails error = new ErrorDetails(e.getMessage(),req.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PostException.class)
    public ResponseEntity<ErrorDetails> postExceptionHandler(Exception e, WebRequest req){
        ErrorDetails error = new ErrorDetails(e.getMessage(),req.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ChatException.class)
    public ResponseEntity<ErrorDetails> chatExceptionHandler(Exception e, WebRequest req){
        ErrorDetails error = new ErrorDetails(e.getMessage(),req.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CommentException.class)
    public ResponseEntity<ErrorDetails> commentExceptionHandler(Exception e, WebRequest req){
        ErrorDetails error = new ErrorDetails(e.getMessage(),req.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MessageException.class)
    public ResponseEntity<ErrorDetails> messageExceptionHandler(Exception e, WebRequest req){
        ErrorDetails error = new ErrorDetails(e.getMessage(),req.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReelsException.class)
    public ResponseEntity<ErrorDetails> reelsExceptionHandler(Exception e, WebRequest req){
        ErrorDetails error = new ErrorDetails(e.getMessage(),req.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorDetails> userExceptionHandler(Exception e, WebRequest req){
        ErrorDetails error = new ErrorDetails(e.getMessage(),req.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
