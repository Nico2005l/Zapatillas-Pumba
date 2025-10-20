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
    private String user_Name;
    private String user_LastName;
    private String user_Email;
    private String user_Username;
    private LocalDateTime user_Creation;

}
