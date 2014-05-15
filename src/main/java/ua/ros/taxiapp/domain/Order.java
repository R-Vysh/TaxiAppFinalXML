package ua.ros.taxiapp.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "orders")
@NamedQueries({
        @NamedQuery(name = "order.active", query = "from Order o where o.status = 'active'"),
        @NamedQuery(name = "order.with.customer", query = "from Order o where o.customer = :customer")
})

public class Order implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    Integer orderId;
    @ManyToOne
    @JoinColumn(name = "taxist_id", nullable = true)
    Taxist taxist;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    Customer customer;
    @Column(name = "address_from")
    String fromPlace;
    @Column(name = "address_to")
    String toPlace;
    @Column(name = "created", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    Date date;
    @Column(name = "total_price")
    Double price;
    @Column(name = "status")
    String status;
    
    public Order() {
    }
    
    public Order(Customer cust, String from, String to) {
        this.customer = cust;
        this.fromPlace = from;
        this.toPlace = to;
    }
    
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer id) {
        this.orderId = id;
    }

    public Taxist getTaxist() {
        return taxist;
    }

    public void setTaxist(Taxist taxist) {
        this.taxist = taxist;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getFromPlace() {
        return fromPlace;
    }

    public void setFromPlace(String fromPlace) {
        this.fromPlace = fromPlace;
    }

    public String getToPlace() {
        return toPlace;
    }

    public void setToPlace(String toPlace) {
        this.toPlace = toPlace;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
