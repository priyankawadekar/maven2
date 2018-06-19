package taskmanagerpkg.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ObjectConversionException extends RuntimeException {

    public ObjectConversionException() {
        super();
    }
    public ObjectConversionException(String s) {
        super(s);
    }
    public ObjectConversionException(String s, Throwable throwable) {
        super(s, throwable);
    }
    public ObjectConversionException(Throwable throwable) {
        super(throwable);
    }
}
