package com.auth.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Device implements Serializable {

//    @NotBlank
    @JsonProperty("device_factory_identifier")
    private String deviceFactoryIdentifier;
//    @NotBlank
    @JsonProperty("device_brand")
    private String deviceBrand;
//    @NotBlank
    @JsonProperty("device_model")
    private String deviceModel;
//    @NotBlank
    @JsonProperty("system")
    private String system;
//    @NotBlank
    @JsonProperty("system_version")
    private String systemVersion;
//    @NotBlank
    @JsonProperty("fCMToken")
    private String fCMToken;
}
