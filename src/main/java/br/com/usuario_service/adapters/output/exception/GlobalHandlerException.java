package br.com.usuario_service.adapters.output.exception;

import br.com.usuario_service.application.domain.exception.CpfAlreadyExistsException;
import br.com.usuario_service.application.domain.exception.ResourceNotFoundException;
import br.com.usuario_service.application.domain.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlerUserNotFoundException(UserNotFoundException exception, HttpServletRequest http){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createError(
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        ));
    }

    @ExceptionHandler(CpfAlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>> handlerCpfAlreadyExistsException(CpfAlreadyExistsException exception, HttpServletRequest http){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(createError(
                HttpStatus.CONFLICT,
                exception.getMessage(),
                HttpStatus.CONFLICT.value()
        ));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlerResourceNotFoundException(ResourceNotFoundException exception, HttpServletRequest http){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createError(
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value()
        ));
    }

    public Map<String, Object> createError(HttpStatus mserror, String message, int status){
        Map<String, Object> error = new HashMap<>();
        error.put("error", mserror);
        error.put("message", message);
        error.put("status", status);
        error.put("timestamp", LocalDateTime.now());
        return error;
    }
}
