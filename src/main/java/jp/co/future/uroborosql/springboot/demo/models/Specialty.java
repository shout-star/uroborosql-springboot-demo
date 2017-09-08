package jp.co.future.uroborosql.springboot.demo.models;

import jp.co.future.uroborosql.mapping.annotations.Table;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Specialty
 *
 * @author Kenichi Hoshi
 * @see jp.co.future.uroborosql.springboot.demo.models.BaseModel
 */
@Table(name = "specialties")
public class Specialty extends BaseModel {

    @NotEmpty
    @Size(max = 30)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
