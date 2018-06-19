package taskmanagerpkg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ObjectConflictException extends RuntimeException{
    public ObjectConflictException() {
        super();
    }
    public ObjectConflictException(String s, HttpStatus httpStatus) {
        ResponseEntity.status(httpStatus).body(s);
    }
    public ObjectConflictException(String s, Throwable throwable) {
        super(s, throwable);
    }
    public ObjectConflictException(Throwable throwable) {
        super(throwable);
    }
}
