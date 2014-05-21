package ua.ros.taxiapp.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "coordinates")
public class Coordinates implements Serializable {
    @Id
    @Column(name = "taxist_id")
    Integer taxistId;
    @Column(name = "longtitude", nullable = false)
    Float longtitude;
    @Column(name = "latitude", nullable = false)
    Float latitude;

    public Integer getTaxistId() {
        return taxistId;
    }

    public void setTaxistId(Integer taxistId) {
        this.taxistId = taxistId;
    }

    public Float getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Float longtitude) {
        this.longtitude = longtitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }
}
