package ua.ros.taxiapp.domain;

import java.io.Serializable;

public class Car implements Serializable {

    Integer carId;
    Brand brand;
    Model model;
    Integer year;
    String regNumber;
    Double pricePerKm;
    
    public Car() {    
    }
    
    public Car(Brand brand, Model model, Integer year, String regNumber, Double price) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.regNumber = regNumber;
        this.pricePerKm = price;
    }
            
    public Integer getCarId() {
        return carId;
    }
    
    public void setCarId(Integer newId) {
        carId = newId;
    }
    
    public Brand getBrand() {
        return brand;
    }
    
    public void setBrand(Brand brand) {
        this.brand = brand;
    }
    
    public Model getModel() {
        return model;
    }
    
    public void setModel(Model model) {
        this.model = model;
    }
    
    public Integer getYear() {
        return year;
    }
    
    public void setYear(Integer year) {
        this.year = year;
    }
    
    public void setRegNumber(String newNumber) {
        this.regNumber = newNumber;
    }
    
    public String getRegNumber() {
        return regNumber;
    }
    
    public Double getPricePerKm() {
        return pricePerKm;
    }
    
    public void setPricePerKm(Double newPrice) {
        this.pricePerKm = newPrice;
    }
}
