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
@Table(name = "cars")
public class Car implements Serializable {

    @Id
    @Column(name = "ID_CAR")
    @GeneratedValue
    Integer carId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_BRAND", nullable = false)
    Brand brand;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MODEL", nullable = false)
    Model model;
    @Column(name = "YEAR")
    Integer year;
    @Column(name = "REG_NUMBER")
    String regNumber;
    @Column(name = "PRICE_PER_KM")
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
