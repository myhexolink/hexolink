package com.auth.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

import static com.auth.model.Color.randomColor;
@Getter
@NoArgsConstructor
@Entity
@Setter
@ToString
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
public class User extends AbstractUserSequenceEntity {

    @Column(name = "first_name", nullable = false, length = 64)
    @JsonProperty("first_name")
    @Size(min = 1, max = 64, message = "First name must be 1 - 64 characters")
    @NotBlank
    private String firstName;

    @Column(name = "last_name")
    @JsonProperty("last_name")
    @Size(max = 64, message = "Last name must not be more than 64 characters")
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @Column(name = "registered", columnDefinition = "timestamp(0) default now()")
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime registered;

    @Column(name = "updated", columnDefinition = "timestamp(0) default now()")
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updated;

    @Column(name = "color")
    @NotBlank
    private String color;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Basic
    @Column(name = "avatar", nullable = true)
    private byte[] avatar;

    public User(
            Integer id,
            String firstName,
            String lastName,
            String email,

            LocalDateTime registered,
            LocalDateTime updated,

            Collection<Role> roles,
            byte[] avatar) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.registered = registered;
        this.updated = updated;
        this.color = randomColor();
        this.avatar = avatar;
        setRoles(roles);
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }
}
