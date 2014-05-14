package ua.ros.taxiapp.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "cars")
@NamedQueries({
        @NamedQuery(name = "car.with.number", query = "from Car c where c.registrationalNumber = :regNumber"),
        @NamedQuery(name = "car.with.price", query = "from Car c where c.pricePerKm = :price"),
        @NamedQuery(name = "car.with.price.between", query = "from Car c where c.pricePerKm between :lowerPrice and :higherPrice")
})
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
    String registrationalNumber;
    @Column(name = "price_per_kilometer")
    Double pricePerKm;

    public Car() {
    }

    public Car(Model model, Integer year, String regNumber, Double price) {
        this.model = model;
        this.year = year;
        this.registrationalNumber = regNumber;
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

    public void setRegistrationalNumber(String newNumber) {
        this.registrationalNumber = newNumber;
    }

    public String getRegistrationalNumber() {
        return registrationalNumber;
    }

    public Double getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(Double newPrice) {
        this.pricePerKm = newPrice;
    }
}
