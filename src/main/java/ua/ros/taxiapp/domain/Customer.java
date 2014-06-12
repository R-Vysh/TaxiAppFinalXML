package ua.ros.taxiapp.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "customers")
@NamedQueries({
        @NamedQuery(name = "customer.with.mobile", query = "from Customer c where c.user.mobile = :mobile"),
        @NamedQuery(name = "customer.with.order", query = "from Customer c where c.currentOrder = :ord"),
        @NamedQuery(name = "customer.with.user", query = "from Customer c where c.user = :user"),
        @NamedQuery(name = "customer.with.username.and.password",
                query = "from Customer c where c.user.username = :username and c.user.password = :password")})
public class Customer implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "customer_id", unique = true, nullable = false)
    private Integer customerId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", nullable = true)
    private Order currentOrder;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    public Customer() {
        this.currentOrder = null;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer id) {
        this.customerId = id;
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

}
