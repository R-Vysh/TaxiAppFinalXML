package ua.ros.taxiapp.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "customers")
@NamedQueries({
        @NamedQuery(name = "customer.with.mobile", query = "from Customer c where c.user.mobile = :mobile"),
        @NamedQuery(name = "customer.with.order", query = "from Customer c where c.currentOrder = :ord"),
        @NamedQuery(name = "customer.with.user", query = "from Customer c where c.user = :user")})
public class Customer implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "customer_id", unique = true, nullable = false)
    private Integer customerId;
    @Column(name = "blames")
    private Integer blames;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", nullable = true)
    private Order currentOrder;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "favourite_customer_maps",
            joinColumns = {
                    @JoinColumn(name = "customer_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "favourite_id")})
    private Set<Favourite> favourites;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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
