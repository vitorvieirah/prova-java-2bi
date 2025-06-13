package com.example.prova_java.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioDto {
    private Long id;
    private String nome;
    private String email;
    private String senha;
}
