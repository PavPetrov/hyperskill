package account.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class AppUser {

    @Id
    @GeneratedValue
    private Long Id;

    private String name;

    private String lastname;

    private String email;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public AppUser() {
    }

    public Long getId() {
        return Id;
    }

    public AppUser setId(Long id) {
        Id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AppUser setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public AppUser setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getEmail() {
        return email.toLowerCase();
    }

    public AppUser setEmail(String email) {
        this.email = email.toLowerCase();
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AppUser setPassword(String password) {
        this.password = password;
        return this;
    }
}
