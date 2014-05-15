package ua.ros.taxiapp.domain;

import org.codehaus.jackson.annotate.JsonManagedReference;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "brands")
@NamedQueries({
        @NamedQuery(name = "brand.with.name", query = "from Brand b where b.brandsName = :brandName")
})
public class Brand implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "brand_id")
    Integer brandId;
    @Column(name = "name", unique = true, nullable = false)
    String brandsName;
    @OneToMany(mappedBy = "brand")
    @JsonManagedReference
    Set<Model> models = new HashSet<>();

    public Brand() {
    }

    public Brand(String name) {
        this.brandsName = name;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer id) {
        this.brandId = id;
    }

    public String getBrandsName() {
        return brandsName;
    }

    public void setBrandsName(String newName) {
        this.brandsName = newName;
    }

    public Set<Model> getModels() {
        return models;
    }

    public void setModels(Set<Model> models) {
        this.models = models;
    }
}
