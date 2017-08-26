package jp.co.future.uroborosql.springboot.demo.models;

import jp.co.future.uroborosql.mapping.annotations.Table;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * Vet
 *
 * @author Kenichi Hoshi
 * @see jp.co.future.uroborosql.springboot.demo.models.BaseModel
 */
@Table(name = "VETS")
public class Vet extends BaseModel {
    @NotEmpty
    @Size(max = 30)
    private String firstName;

    @NotEmpty
    @Size(max = 30)
    private String lastName;

    private List<Specialty> specialties;

    public Vet() {
    }

    public Vet(int id, String firstName, String lastName) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<Specialty> specialties) {
        this.specialties = specialties;
    }
}
