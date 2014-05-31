package ua.ros.taxiapp.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "users")
@NamedQueries(value = {
        @NamedQuery(name = "user.with.mobile", query = "from User u where u.mobile = :mobile"),
        @NamedQuery(name = "user.with.password.and.username",
                query = "from User u where u.password = :password and u.username = :username"),
        @NamedQuery(name = "user.with.username", query = "from User u where u.username like :custName")
})
public class User implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "password")
    private String password;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "is_taxist")
    private Boolean taxist;
    @Column(name = "created", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;
    @Column(name = "username")
    private String username;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Authority> authorities;

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

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
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
