package jp.co.future.uroborosql.springboot.demo.common.exceptions;

import org.springframework.http.HttpStatus;

/**
 * ErrorResponse
 *
 * @author Kenichi Hoshi
 */
public class ErrorResponse {
    /**
     * error type
     */
    private String error;
    /**
     * error message
     */
    private String message;
    /**
     * http status
     */
    private int status;
    /**
     * issue time
     */
    private long timestamp;

    private ErrorResponse(String error, String message, int status) {
        this.setError(error);
        this.setMessage(message);
        this.setStatus(status);
        this.setTimestamp(System.currentTimeMillis() / 1000L);
    }

    public static ErrorResponse of(HttpStatus httpStatus, String message) {
        return new ErrorResponse(httpStatus.getReasonPhrase(), message, httpStatus.value());
    }

    public static ErrorResponse of(String error, String message, int status) {
        return new ErrorResponse(error, message, status);
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
