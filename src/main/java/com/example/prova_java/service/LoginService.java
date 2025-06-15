package com.example.prova_java.service;

import com.example.prova_java.controller.dto.LoginDto;
import com.example.prova_java.model.Usuario;
import com.example.prova_java.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioDetailsService userDetailsService;

    public LoginDto logar(LoginDto loginDto) {
        Usuario usuario = usuarioService.consultarPorEmail(loginDto.getEmail());

        if(!usuario.getEmail().equals(loginDto.getEmail()) || !usuario.getSenha().equals(loginDto.getSenha())) {
            throw new RuntimeException("Credencias inválidas");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(usuario.getEmail());
        String jwtToken = jwtUtil.generateToken(userDetails);

        return new LoginDto(null, null, jwtToken);
    }
}
