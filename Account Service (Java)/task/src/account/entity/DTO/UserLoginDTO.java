package account.entity.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public class UserLoginDTO {

    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String lastname;

    //  @Pattern(regexp = "\\w+(@acme.com)$")
    @NotEmpty
    @NotNull
    private String email;

    //  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)

    public UserLoginDTO() {
    }

    public String getName() {
        return name;
    }

    public UserLoginDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public UserLoginDTO setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserLoginDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserLoginDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
