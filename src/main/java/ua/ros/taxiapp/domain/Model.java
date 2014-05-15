package ua.ros.taxiapp.domain;

import org.codehaus.jackson.annotate.JsonBackReference;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "models")
@NamedQueries({
        @NamedQuery(name = "model.with.name", query = "from Model m where m.modelsName = :name"),
        @NamedQuery(name = "model.with.brand", query = "from Model m where m.brand = :brand")
})

public class Model implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "model_id")
    Integer modelId;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "brand_id", nullable = false)
    @JsonBackReference
    Brand brand;
    @Column(name = "name")
    String modelsName;
    
    public Model() {     
    }
    
    public Model(String name, Brand brand) {
        this.modelsName = name;
        this.brand = brand;
    }
    
    public Integer getModelId() {
        return modelId;
    }
    
    public void setModelId(Integer newId) {
        this.modelId = newId;
    }
    
    public Brand getBrand() {
        return brand;
    }
    
    public void setBrand(Brand newBrand) {
        brand = newBrand;
    }
    
    public String getModelsName() {
        return modelsName;
    }
    
    public void setModelsName(String newName) {
        this.modelsName = newName;
    }
}
