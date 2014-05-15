package ua.ros.taxiapp.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "taxists")
@NamedQueries({
        @NamedQuery(name = "taxist.free", query = "from Taxist t where t.free = true"),
        @NamedQuery(name = "taxist.with.mobile", query = "from Taxist t where t.user.mobile = :mobile")
})
public class Taxist {

    @Id
    @GeneratedValue
    @Column(name = "taxist_id")
    Integer taxistId;
    @OneToOne
    @JoinColumn(name = "car_id")
    Car car;
    @OneToOne
    @JoinColumn(name = "order_id")
    Order currentOrder;
    @Column(name = "rating")
    Double rating;
    @Column(name = "is_free")
    Boolean free;
    @Column(name = "is_online")
    Boolean online;
    @OneToMany(mappedBy = "taxist")
    Set<Comment> comments = new HashSet<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
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

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Boolean getFree() {
        return free;
    }
}
