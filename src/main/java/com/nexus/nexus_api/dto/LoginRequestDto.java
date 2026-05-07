package com.nexus.nexus_api.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginRequestDto {

    @NotEmpty(message = "Login é obrigatório: ")
    private String login;

    @NotEmpty(message = "Senha e obrigatória: ")
    private String password;

}
