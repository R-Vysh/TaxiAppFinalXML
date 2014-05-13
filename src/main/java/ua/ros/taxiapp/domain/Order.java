package ua.ros.taxiapp.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_ORDER")
    Integer orderId;
    @ManyToOne
    @JoinColumn(name = "ID_TAXIST", nullable = true)
    Taxist taxist;
    @ManyToOne
    @JoinColumn(name = "ID_CUSTOMER", nullable = false)
    Customer customer;
    @Column(name = "FROM_ADDRESS")
    String fromPlace;
    @Column(name = "TO_ADDRESS")
    String toPlace;
    @Column(name = "CREATED", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    Date date;
    @Column(name = "TOTAL_PRICE")
    Double price;
    @Column(name = "STATUS")
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
