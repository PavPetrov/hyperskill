package account.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public class UserSignupDTO {
    @NotEmpty
    private String name;
    @NotEmpty
    private String lastname;

    @Pattern(regexp = "\\w+(@acme.com)$")
    @NotEmpty
    @NotNull
    private String email;

    //  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 12, message = "Password length must be 12 chars minimum!")
    @NotNull
    private String password;

    public UserSignupDTO() {
    }

    public String getName() {
        return name;
    }

    public UserSignupDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public UserSignupDTO setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserSignupDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserSignupDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}
