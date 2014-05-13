package ua.ros.taxiapp.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "models")
public class Model implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_MODEL")
    Integer modelId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_BRAND", nullable = false)
    Brand brand;
    @Column(name = "NAME")
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
