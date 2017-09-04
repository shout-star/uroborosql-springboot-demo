package jp.co.future.uroborosql.springboot.demo.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Login model
 *
 * @author Kenichi Hoshi
 */

public class Login {

    @NotEmpty
    @Size(max = 30)
    private String user;

    @NotEmpty
    @Size(min = 6, max = 30)
    private String password;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
