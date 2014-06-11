package ua.ros.taxiapp.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
    private Integer carId;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "model_id", nullable = false)
    @NotNull
    private Model model;
    @Column(name = "year")
    @Max(2014)
    @Min(1930)
    private Integer year;
    @Column(name = "registrational_number")
    @NotNull
    private String registrationalNumber;
    @Column(name = "price_per_kilometer")
    @NotNull
    private Double pricePerKm;

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
