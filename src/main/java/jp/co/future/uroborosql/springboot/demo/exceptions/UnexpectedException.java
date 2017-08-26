package jp.co.future.uroborosql.springboot.demo.exceptions;

/**
 * UnexpectedException
 *
 * @author Kenichi Hoshi
 */
public class UnexpectedException extends RuntimeException {

    public UnexpectedException(Exception e) {
        super("Unexpected.", e);
    }

}
