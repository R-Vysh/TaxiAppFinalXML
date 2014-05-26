package ua.ros.taxiapp.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "coordinates")
public class Coordinates implements Serializable {
    @Id
    @Column(name = "coordinates_id")
    @GeneratedValue
    Integer coordinatesId;
    @Column(name = "longtitude", nullable = false)
    Double longtitude;
    @Column(name = "latitude", nullable = false)
    Double latitude;

    public Integer getCoordinatesId() {
        return coordinatesId;
    }

    public void setCoordinatesId(Integer coordinatesId) {
        this.coordinatesId = coordinatesId;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
