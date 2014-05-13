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
    @Column(name = "car_id")
    @GeneratedValue
    Integer carId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false)
    Model model;
    @Column(name = "year")
    Integer year;
    @Column(name = "registrational_number")
    String regNumber;
    @Column(name = "price_per_kilometer")
    Double pricePerKm;
    
    public Car() {    
    }
    
    public Car(Model model, Integer year, String regNumber, Double price) {
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
