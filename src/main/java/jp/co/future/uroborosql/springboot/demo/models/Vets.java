package jp.co.future.uroborosql.springboot.demo.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Vets
 *
 * @author Kenichi Hoshi
 */
@XmlRootElement
public class Vets {

    private List<Vet> vets;

    public Vets() {
    }

    public Vets(List<Vet> vets) {
        this.vets = vets;
    }

    @XmlElement
    public List<Vet> getVetList() {
        if (vets == null) {
            vets = new ArrayList<>();
        }
        return vets;
    }

}
