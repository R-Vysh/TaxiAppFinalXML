package ua.ros.taxiapp.domain;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
public class Authority {

    public enum Rolename {
        ROLE_CUST("ROLE_CUST"), ROLE_TAXIST("ROLE_TAXIST"), ROLE_ADMIN("ROLE_ADMIN");

        private String value;

        Rolename(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    @Id
    @GeneratedValue
    @Column(name = "authority_id")
    Integer authorityId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username", referencedColumnName = "username")
    User user;
    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    Rolename rolename;

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public String getRolename() {
//        return rolename;
//    }
//
//    public void setRolename(String rolename) {
//        this.rolename = rolename;
//    }


    public Rolename getRolename() {
        return rolename;
    }

    public void setRolename(Rolename rolename) {
        this.rolename = rolename;
    }
}
