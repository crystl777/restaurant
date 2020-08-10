package model;

import java.util.Date;
import java.util.Set;

public class User extends AbstractNamedEntity {

    private String email;

    private String password;

    private Set<Role> roles;

    private Date registered = new Date();

    public User() {
    }

    public User(Integer id, String name, String email, String password, Set<Role> roles, Date registered) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.registered = registered;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }
}
