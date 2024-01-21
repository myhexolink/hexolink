package com.messenger.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;
import java.time.LocalDate;


@Getter
@NoArgsConstructor
@Setter
@ToString
public class OAuthUser implements HasId {

    private Integer id;

    @JsonProperty("first_name")
    @Size(min = 1, max = 64, message = "First name must be 1 - 64 characters")
    @NotBlank
    private String firstName;

    @JsonProperty("last_name")
    @Size(max = 64, message = "Last name must not be more than 64 characters")
    private String lastName;

    @Email
    @NotBlank
    private String email;

    private String color;

    @Size(max = 70, message = "Last name must not be more than 64 characters")
    private String bio;

    @JsonProperty("birth_date")
    private LocalDate birthDate;

    @Pattern(flags = Pattern.Flag.CASE_INSENSITIVE,
            regexp = "male|female",
            message = "gender must be male or female")
    private String gender;

    @JsonManagedReference
    @JsonProperty("avatar_file")
    @ToString.Exclude
    private byte[] avatarFile;

    public OAuthUser(
            Integer id,
            String email,
            String color,
            String firstName,
            String lastName,
            String bio,
            LocalDate birthDate,
            String gender,
            byte[] avatarFile) {
        this.id = id;
        this.email = email;
        this.color = color;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.birthDate = birthDate;
        this.gender = gender;
        this.avatarFile = avatarFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OAuthUser)) return false;
        OAuthUser user = (OAuthUser) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }
}
