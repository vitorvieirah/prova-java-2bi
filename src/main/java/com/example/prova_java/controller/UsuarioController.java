package com.example.prova_java.controller;

import com.example.prova_java.controller.dto.UsuarioDto;
import com.example.prova_java.mapper.UsuarioMapper;
import com.example.prova_java.security.UsuarioDetails;
import com.example.prova_java.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    //todos
    @PostMapping("cadastrar")
    public ResponseEntity<UsuarioDto> cadastar(@RequestBody UsuarioDto novoUsuario) {
        UsuarioDto response = UsuarioMapper.paraDto(service.cadastrar(UsuarioMapper.paraDomainDeDto(novoUsuario)));
        return ResponseEntity.created(
                UriComponentsBuilder
                        .newInstance()
                        .path("/usuarios/{id}")
                        .buildAndExpand(response.getId())
                        .toUri())
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listar() {
        List<UsuarioDto> usuarios = service.listar().stream().map(UsuarioMapper::paraDto).toList();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> consultarPorId(@PathVariable("id") Long id, Authentication authentication) {
        UsuarioDetails usuarioLogado = (UsuarioDetails) authentication.getPrincipal();

        if (!usuarioLogado.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))
                && !usuarioLogado.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(UsuarioMapper.paraDto(service.consultarPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> alterar(@PathVariable("id") Long id, @RequestBody UsuarioDto novosDados, Authentication authentication) {

        UsuarioDetails usuarioLogado = (UsuarioDetails) authentication.getPrincipal();

        if (!usuarioLogado.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))
                && !usuarioLogado.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(UsuarioMapper.paraDto(service.alterar(UsuarioMapper.paraDomainDeDto(novosDados), id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
