package ua.ros.taxiapp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Brand implements Serializable {

    private Integer brandId;
    private String brandsName;
    List<Model> models = new ArrayList<>();
    
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
        models.add(model);
    }
}
