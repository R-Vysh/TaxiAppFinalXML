package ua.ros.taxiapp.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "user.with.mobile", query = "from User u where u.mobile = :mobile")
})
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    Integer userId;
    @Column(name = "password")
    String password;
    @Column(name = "mobile")
    String mobile;
    @Column(name = "is_taxist")
    Boolean taxist;
    @Column(name = "created", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    Date createdTime;
    @Column(name = "username")
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
    
    public void setTaxist(Boolean taxist) {
        this.taxist = taxist;
    }


}
