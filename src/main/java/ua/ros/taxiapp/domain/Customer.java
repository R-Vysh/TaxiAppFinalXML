package ua.ros.taxiapp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Customer implements Serializable {
    
    Integer customerId;
    Integer blames;
    Order currentOrder;
    private List<Favourite> favourites;
    User user;
    
    public Customer() {
        this.favourites = new ArrayList<>();
        this.blames = 0;
        this.currentOrder = null;
    }
    
    public Integer getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(Integer id) {
        this.customerId = id;
    }
    
    public Integer getBlames() {
        return blames;
    }
    
    public void setBlames(Integer blames) {
        this.blames = blames;
    }
    
    public Order getCurrentOrder() {
        return currentOrder;
    }
    
    public void setCurrentOrder(Order order) {
        this.currentOrder = order;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public List<Favourite> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<Favourite> favourites) {
        this.favourites = favourites;
    }
    
    
}
