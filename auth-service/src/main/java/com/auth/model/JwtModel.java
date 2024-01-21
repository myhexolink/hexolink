package com.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
@Setter
@ToString
@Table(name = "tokens")
public class JwtModel implements Serializable {

    @Id
    @SequenceGenerator(
            name = "token_seq",
            sequenceName = "token_seq",
            allocationSize = 1,
            initialValue = 100000
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_seq")
    protected Integer jwtModelId;

    private String token;

    private Integer userId;

    private String deviceId;

    @JsonProperty(value = "secretKey")
    private byte[] secretKey;

    public JwtModel(String token, Integer userId, String email, byte[] secretKey) {
        this(null, token, userId, email, secretKey);
    }

    public JwtModel(Integer jwtModelId, String token, Integer userId, String deviceId, byte[] secretKey) {
        this.jwtModelId = jwtModelId;
        this.token = token;
        this.userId = userId;
        this.deviceId = deviceId;
        this.secretKey = secretKey;
    }
}
