package ua.ros.taxiapp.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@NamedQueries({
        @NamedQuery(name = "order.active", query = "from Order o where o.status = 'NOTTAKEN'"),
        @NamedQuery(name = "order.with.customer", query = "from Order o where o.customer = :customer"),
        @NamedQuery(name = "count.orders.with.customer", query = "select count(*) from Order o where o.customer = :customer"),
        @NamedQuery(name = "order.with.taxist", query = "from Order o where o.taxist = :taxist"),
        @NamedQuery(name = "count.orders.with.taxist", query = "select count(*) from Order o where o.taxist = :taxist"),
        @NamedQuery(name = "order.for.car", query = "from Order o where o.status = 'NOTTAKEN' " +
                "and (:car in elements(o.appropriateCars) or o.appropriateCars is empty)"),
        @NamedQuery(name = "income.for.taxist", query = "select sum(o.price) from Order o where o.taxist = :taxist and o.status='DONE'"),
        @NamedQuery(name = "outcome.for.customer", query = "select sum(o.price) from Order o where o.customer = :customer and o.status='DONE'")
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
    @NotNull
    private Customer customer;
    @Column(name = "address_from")
    @NotNull
    private String fromPlace;
    @Column(name = "address_to")
    @NotNull
    private String toPlace;
    @Column(name = "created", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "total_price")
    private Double price;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @NotNull
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
    private List<Car> appropriateCars;
    @Column(name = "is_blamed")
    private Boolean blamed;

    public Order() {
        this.blamed = Boolean.FALSE;
    }

    public Order(Customer customer, String from, String to) {
        this.customer = customer;
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

    public List<Car> getAppropriateCars() {
        return appropriateCars;
    }

    public void setAppropriateCars(List<Car> appropriateCars) {
        this.appropriateCars = appropriateCars;
    }

    public Boolean getBlamed() {
        return blamed;
    }

    public void setBlamed(Boolean blamed) {
        this.blamed = blamed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (appropriateCars != null ? !appropriateCars.equals(order.appropriateCars) : order.appropriateCars != null)
            return false;
        if (!blamed.equals(order.blamed)) return false;
        if (!customer.equals(order.customer)) return false;
        if (dateCreated != null ? !dateCreated.equals(order.dateCreated) : order.dateCreated != null) return false;
        if (fromCoordinates != null ? !fromCoordinates.equals(order.fromCoordinates) : order.fromCoordinates != null)
            return false;
        if (!fromPlace.equals(order.fromPlace)) return false;
        if (orderId != null ? !orderId.equals(order.orderId) : order.orderId != null) return false;
        if (price != null ? !price.equals(order.price) : order.price != null) return false;
        if (status != order.status) return false;
        if (taxist != null ? !taxist.equals(order.taxist) : order.taxist != null) return false;
        if (toCoordinates != null ? !toCoordinates.equals(order.toCoordinates) : order.toCoordinates != null)
            return false;
        if (!toPlace.equals(order.toPlace)) return false;

        return true;
    }
}
