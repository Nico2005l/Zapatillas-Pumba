package com.uade.tpo.zapatillasPumba.controllers.auth;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("user_name")
    private String user_Name;
    @JsonProperty("user_lastName")
    private String user_LastName;
    @JsonProperty("user_email")
    private String user_Email;
    @JsonProperty("user_username")
    private String user_Username;
    @JsonProperty("user_creation")
    private LocalDateTime user_Creation;

}
