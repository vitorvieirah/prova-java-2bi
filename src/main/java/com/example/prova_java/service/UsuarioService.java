package com.example.prova_java.service;

import com.example.prova_java.controller.dto.UsuarioDto;
import com.example.prova_java.model.Usuario;
import com.example.prova_java.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario cadastrar(Usuario usuario) {
        usuario.setRole(usuario.getRole());
        return repository.save(usuario);
    }

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    private Usuario consularPorId(Long id) {
        Optional<Usuario> usuario = repository.findById(id);

        if(usuario.isEmpty()) {
            throw new RuntimeException("Usuario não encontrado pelo seu id.");
        }

        return usuario.get();
    }

    public Usuario consultarPorEmail(String email) {
        Optional<Usuario> usuario = repository.findByEmail(email);

        if(usuario.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado pelo seu email.");
        }

        return usuario.get();
    }

    public Usuario consultarPorId(Long id) {
        Optional<Usuario> usuario = repository.findById(id);

        if(usuario.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado pelo seu id.");
        }

        return usuario.get();
    }

    public Usuario alterar(Usuario novosDados, Long id) {
        Usuario usuario = this.consularPorId(id);

        usuario.setDados(novosDados);

        return repository.save(usuario);
    }
}
