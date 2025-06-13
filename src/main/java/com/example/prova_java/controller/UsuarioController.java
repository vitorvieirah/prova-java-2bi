package com.example.prova_java.controller;

import com.example.prova_java.controller.dto.UsuarioDto;
import com.example.prova_java.mapper.UsuarioMapper;
import com.example.prova_java.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

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

    @PutMapping("/role/{id}")
    public ResponseEntity<UsuarioDto> alterarRole(@RequestBody String novaRole, @PathVariable("id") Long id) {
        UsuarioDto response = UsuarioMapper.paraDto(service.alterarRole(novaRole, id));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listar() {
        List<UsuarioDto> usuarios = service.listar().stream().map(UsuarioMapper::paraDto).toList();
        return ResponseEntity.ok(usuarios);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
