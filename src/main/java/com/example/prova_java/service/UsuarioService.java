package com.example.prova_java.service;

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
        return repository.save(usuario);
    }

    public Usuario alterarRole(String novaRole, Long id) {
        Usuario usuario = this.consularPorId(id);
        // ver como fazer
        return null;
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
            throw new RuntimeException("Usuario não encontrado.");
        }

        return usuario.get();
    }
}
