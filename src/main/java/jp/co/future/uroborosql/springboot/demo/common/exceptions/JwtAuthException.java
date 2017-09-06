package jp.co.future.uroborosql.springboot.demo.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * JwtAuthException
 *
 * @author Kenichi Hoshi
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class JwtAuthException extends RuntimeException {

    public JwtAuthException() {
        super("Authentication failed.");
    }

    public JwtAuthException(Throwable e) {
        super("Authentication failed.", e);
    }

}
