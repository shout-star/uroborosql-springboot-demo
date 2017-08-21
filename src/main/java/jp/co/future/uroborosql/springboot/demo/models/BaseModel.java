package jp.co.future.uroborosql.springboot.demo.models;

/**
 * BaseModel
 *
 * @author Kenichi Hoshi
 */
public abstract class BaseModel {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
