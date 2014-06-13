package ua.ros.taxiapp.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
    @Column(name = "model")
    @NotNull
    private String model;
    @Column(name = "brand")
    @NotNull
    private String brand;
    @Column(name = "year")
    @NotNull
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

    public Car(Integer carId, String model, String brand, Integer year, String regNumber, Double price) {
        this.carId = carId;
        this.model = model;
        this.brand = brand;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void updateFromCar(Car car) {
        this.model = car.model;
        this.registrationalNumber = car.registrationalNumber;
        this.brand = car.brand;
        this.year = car.year;
        this.pricePerKm = car.pricePerKm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;

        Car car = (Car) o;

        if (!brand.equals(car.brand)) return false;
        if (carId != null ? !carId.equals(car.carId) : car.carId != null) return false;
        if (!model.equals(car.model)) return false;
        if (!pricePerKm.equals(car.pricePerKm)) return false;
        if (!registrationalNumber.equals(car.registrationalNumber)) return false;
        if (!year.equals(car.year)) return false;

        return true;
    }
}
