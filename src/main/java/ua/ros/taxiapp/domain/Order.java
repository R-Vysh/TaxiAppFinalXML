package ua.ros.taxiapp.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "orders")
@NamedQueries({
        @NamedQuery(name = "order.active", query = "from Order o where o.status = 'NOTTAKEN'"),
        @NamedQuery(name = "order.with.customer", query = "from Order o where o.customer = :customer")
})

public class Order implements Serializable {

    public enum OrderStatus {
        NOTTAKEN("NOTTAKEN"), DONE("DONE"), TAKEN("TAKEN"), CANCELED("CANCELED"), ONPLACE("ONPLACE");

        private String value;

        OrderStatus(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Integer orderId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "taxist_id", nullable = true)
    private Taxist taxist;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    @Column(name = "address_from")
    private String fromPlace;
    @Column(name = "address_to")
    private String toPlace;
    @Column(name = "created", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "total_price")
    private Double price;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "coordinates_from")
    private Coordinates fromCoordinates;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "coordinates_to")
    private Coordinates toCoordinates;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "order_car_maps",
            joinColumns = {
                    @JoinColumn(name = "order_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "car_id")})
    private Set<Car> appropriateCars;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Coordinates getFromCoordinates() {
        return fromCoordinates;
    }

    public void setFromCoordinates(Coordinates fromCoordinates) {
        this.fromCoordinates = fromCoordinates;
    }

    public Coordinates getToCoordinates() {
        return toCoordinates;
    }

    public void setToCoordinates(Coordinates toCoordinates) {
        this.toCoordinates = toCoordinates;
    }

    public Set<Car> getAppropriateCars() {
        return appropriateCars;
    }

    public void setAppropriateCars(Set<Car> appropriateCars) {
        this.appropriateCars = appropriateCars;
    }
}
