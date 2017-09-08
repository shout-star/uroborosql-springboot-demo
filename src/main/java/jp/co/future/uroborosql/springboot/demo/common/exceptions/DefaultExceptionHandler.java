package jp.co.future.uroborosql.springboot.demo.common.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import java.util.HashMap;

@ControllerAdvice
public class DefaultExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @ExceptionHandler({ValidationException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<?> unprocessabelEntity(Exception e, HttpServletRequest req) {
        // TODO: custom message
        return new ResponseEntity<>(
            ErrorResponse.of(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage()),
            HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // TODO: other exception
    //    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> unhandledException(Exception e, HttpServletRequest req) {
        LOG.error("unhandled exception", e);
        return new ResponseEntity<>(
            new HashMap<>(),
            HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
