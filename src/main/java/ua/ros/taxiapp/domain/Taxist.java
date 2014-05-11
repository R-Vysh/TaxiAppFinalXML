package ua.ros.taxiapp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Taxist implements Serializable {

    private Integer taxistId;
    private Car car;
    private Order currentOrder;
    private Double rating;
    private Boolean free;
    private Boolean online;
    private List<Comment> comments = new ArrayList<>();
    private User user;
    
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

    public Boolean getFree() {
        return free;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }
}
