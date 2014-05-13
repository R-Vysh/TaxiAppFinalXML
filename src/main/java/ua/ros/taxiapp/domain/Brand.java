package ua.ros.taxiapp.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "brands")
public class Brand implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "brand_id")
    Integer brandId;
    @Column(name = "name", unique = true, nullable = false)
    String brandsName;
    @OneToMany(mappedBy="brand")
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
