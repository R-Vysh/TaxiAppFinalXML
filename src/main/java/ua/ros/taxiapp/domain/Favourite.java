package ua.ros.taxiapp.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "favourites")
@NamedQueries({
        @NamedQuery(name = "favourite.with.address", query = "from Favourite fav where fav.address = :address"),
})
public class Favourite {

    @Id
    @GeneratedValue
    @Column(name = "favourite_id")
    private Integer favouriteId;
    @Column(name = "address")
    private String address;

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