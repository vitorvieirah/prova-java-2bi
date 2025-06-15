package com.example.prova_java.mapper;

import com.example.prova_java.controller.dto.UsuarioDto;
import com.example.prova_java.model.Usuario;

public class UsuarioMapper {
    public static UsuarioDto paraDto(Usuario domain) {
        return new UsuarioDto(
                domain.getId(),
                domain.getNome(),
                domain.getEmail(),
                domain.getSenha(),
                domain.getRole()
        );
    }

    public static Usuario paraDomainDeDto(UsuarioDto dto) {
        return new Usuario(
                dto.getId(),
                dto.getNome(),
                dto.getEmail(),
                dto.getSenha(),
                dto.getRole()
        );
    }
}
