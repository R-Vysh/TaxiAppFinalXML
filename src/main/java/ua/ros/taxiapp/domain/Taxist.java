package ua.ros.taxiapp.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "taxists")
@NamedQueries({
        @NamedQuery(name = "taxist.free", query = "from Taxist t where t.free = true and t.online = true"),
        @NamedQuery(name = "taxist.with.mobile", query = "from Taxist t where t.user.mobile = :mobile"),
        @NamedQuery(name = "taxist.with.user", query = "from Taxist t where t.user = :user")
})
public class Taxist {

    @Id
    @GeneratedValue
    @Column(name = "taxist_id")
    private Integer taxistId;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    private Car car;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order currentOrder;
    @Column(name = "rating")
    private Double rating;
    @Column(name = "is_free")
    private Boolean free;
    @Column(name = "is_online")
    private Boolean online;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinates_id")
    private Coordinates coordinates;

    public Taxist() {
        this.free = true;
        this.online = false;
        this.rating = 0.0;
    }

    public Taxist(Car car, User user) {
        this.car = car;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getTaxistId() {
        return taxistId;
    }

    public void setTaxistId(Integer id) {
        this.taxistId = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order order) {
        this.currentOrder = order;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Boolean getFree() {
        return free;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
