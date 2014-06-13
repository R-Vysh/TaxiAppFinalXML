package ua.ros.taxiapp.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "coordinates")
public class Coordinates implements Serializable {
    @Id
    @Column(name = "coordinates_id")
    @GeneratedValue
    private Integer coordinatesId;
    @Column(name = "longtitude", nullable = false)
    @NotNull
    private Double longtitude;
    @Column(name = "latitude", nullable = false)
    @NotNull
    private Double latitude;

    public Coordinates() {
        this.longtitude = 0.0;
        this.latitude = 0.0;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates)) return false;

        Coordinates that = (Coordinates) o;

        if (coordinatesId != null ? !coordinatesId.equals(that.coordinatesId) : that.coordinatesId != null)
            return false;
        if (!latitude.equals(that.latitude)) return false;
        if (!longtitude.equals(that.longtitude)) return false;

        return true;
    }
}
