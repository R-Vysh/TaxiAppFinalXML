package ua.ros.taxiapp.domain;

import org.codehaus.jackson.annotate.JsonManagedReference;

import java.io.Serializable;
import java.util.ArrayList;
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
    private Integer brandId;
    @Column(name = "name", unique = true, nullable = false)
    private String brandsName;
    @OneToMany(mappedBy = "brand")
    @JsonManagedReference
    private List<Model> models = new ArrayList<>();

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

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    public void addModel(Model model) {
        this.models.add(model);
    }
}
