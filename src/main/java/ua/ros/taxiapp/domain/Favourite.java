package ua.ros.taxiapp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Favourite implements Serializable {

    Integer favouriteId;
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
