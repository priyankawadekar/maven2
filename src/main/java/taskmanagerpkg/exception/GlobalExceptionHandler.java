package taskmanagerpkg.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/*ref - http://www.baeldung.com/exception-handling-for-rest-with-spring*/
/*ref - http://www.springboottutorial.com/spring-boot-exception-handling-for-rest-services*/

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ObjectConversionException.class)
    public ResponseEntity<Object> handleObjectConversionException(ObjectConversionException oex) {
        return ResponseEntity.badRequest().body(oex);
    }

    @ExceptionHandler(ObjectConflictException.class)
    public ResponseEntity<Object> handleAnyException(String msg, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).body(msg);
    }
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        String bodyOfResponse = " This is application specific taskmanagerpkg.exception.";
        return handleExceptionInternal(ex,
                new Date() + bodyOfResponse,
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request);
    }
}