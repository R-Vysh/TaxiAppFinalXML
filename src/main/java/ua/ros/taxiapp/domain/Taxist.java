package ua.ros.taxiapp.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "taxists")
public class Taxist {

    @Id
    @Column(name = "ID_TAXIST")
    Integer taxistId;
    @OneToOne
    @JoinColumn(name = "ID_CAR")
    Car car;
    @OneToOne
    @JoinColumn(name = "ID_ORDER")
    Order currentOrder;
    @Column(name = "RATING")
    Double rating;
    @Column(name = "FREE")
    Boolean free;
    @OneToMany(mappedBy = "taxist")
    Set<Comment> comments = new HashSet<>();
    @OneToOne
    @PrimaryKeyJoinColumn
    User user;
    
    public Taxist() {
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

    public Boolean isFree() {
        return free;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
