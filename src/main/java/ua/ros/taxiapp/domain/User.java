package ua.ros.taxiapp.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_USER")
    Integer userId;
    @Column(name = "PASSWORD")
    String password;
    @Column(name = "MOBILE")
    String mobile;
    @Column(name = "TAXIST")
    Boolean taxist;
    @Column(name = "CREATED", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    Date createdTime;
    @Column(name = "USERNAME")
    String username;
    
    public User() {
    }
    
    public User(String mobile, String pass) {
        this.mobile = mobile;
        this.password = pass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getTaxist() {
        return taxist;
    }

    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer id) {
        this.userId = id;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public Date getCreatedTime() {
        return createdTime;
    }
    
    public void setCreatedTime(Date time) {
        this.createdTime = time;
    }
    
    public Boolean isTaxist() {
        return taxist;
    }
    
    public void setTaxist(Boolean taxist) {
        this.taxist = taxist;
    }
}
