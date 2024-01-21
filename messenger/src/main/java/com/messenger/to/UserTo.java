package com.messenger.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserTo {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("first_name")
    @Size(min = 1, max = 64, message = "First name must be 1 - 64 characters")
    @NotBlank
    private String firstName;

    @JsonProperty("last_name")
    @Size(max = 64, message = "Last name must not be more than 64 characters")
    private String lastName;

    @JsonProperty("email")
    @NotBlank
    @Size(max = 100)
    private String email;

    @JsonProperty("color")
    private String color;

    @Size(max = 70, message = "Last name must not be more than 64 characters")
    private String bio;

    @JsonProperty("birth_date")
    private LocalDate birthDate;

    @JsonProperty("gender")
    @Pattern(flags = Pattern.Flag.CASE_INSENSITIVE,
            regexp = "male|female",
            message = "gender must be male or female")
    private String gender;

    @JsonProperty("avatar_file")
    private byte[] avatarFile;
}
