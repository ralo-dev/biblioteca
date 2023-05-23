package com.cbtis.biblioteca.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    private String nombre;
    private String apellidos;

    private String role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

}