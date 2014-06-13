package ua.ros.taxiapp.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
public class Authority implements Serializable {

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
    private Integer authorityId;
    @ManyToOne()
    @JoinColumn(name = "username", referencedColumnName = "username")
    @NotNull
    private User user;
    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    @NotNull
    private Rolename rolename;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    public Rolename getRolename() {
        return rolename;
    }

    public void setRolename(Rolename rolename) {
        this.rolename = rolename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Authority)) return false;

        Authority authority = (Authority) o;

        if (authorityId != null ? !authorityId.equals(authority.authorityId) : authority.authorityId != null)
            return false;
        if (rolename != authority.rolename) return false;
        if (!user.equals(authority.user)) return false;

        return true;
    }
}
