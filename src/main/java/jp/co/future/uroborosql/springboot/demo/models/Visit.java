package jp.co.future.uroborosql.springboot.demo.models;

import jp.co.future.uroborosql.mapping.annotations.Table;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Visit model
 *
 * @author Kenichi Hoshi
 * @see jp.co.future.uroborosql.springboot.demo.models.BaseModel
 */
@Table(name = "VISITS")
public class Visit extends BaseModel {

    @NotNull
    private Date visitDate;

    @NotEmpty
    @Size(max = 255)
    private String description;

    @Digits(integer = 5, fraction = 0)
    private int petId;

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

}
