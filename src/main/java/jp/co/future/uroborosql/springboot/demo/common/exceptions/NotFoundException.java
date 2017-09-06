package jp.co.future.uroborosql.springboot.demo.common.exceptions;

/**
 * NotFoundException
 *
 * @author Kenichi Hoshi
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("Data not found.");
    }

}
