package ua.ros.taxiapp.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
@NamedQueries({
        @NamedQuery(name = "customer.with.mobile", query = "from Customer c where c.user.mobile = :mobile")
})
public class Customer implements Serializable{
    
    @Id
    @GeneratedValue
    @Column(name = "customer_id", unique = true, nullable = false)
    Integer customerId;
    @Column(name = "blames")
    Integer blames;
    @OneToOne
    @JoinColumn(name = "order_id", nullable = true)
    Order currentOrder;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "favourite_customer_maps",
            joinColumns = {
                @JoinColumn(name = "customer_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "favourite_id")})
    private Set<Favourite> favourites;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    User user;
    
    public Customer() {
        this.favourites = new HashSet<>();
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
    
    public Set<Favourite> getFavourites() {
        return favourites;
    }
    
    public void setFavourites(Set<Favourite> favs) {
        this.favourites = favs;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
}
