package ua.ros.taxiapp.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "favourites")
public class Favourite {

    @Id
    @GeneratedValue
    @Column(name = "ID_FAVOURITE")
    Integer favouriteId;
    @Column(name = "ADDRESS")
    String address;

    public Favourite() {
    }
    
    public Favourite(String address) {
        this.address = address;
    }

    public Integer getFavouriteId() {
        return favouriteId;
    }

    public void setFavouriteId(Integer newId) {
        this.favouriteId = newId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
