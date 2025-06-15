package com.example.prova_java.controller;

import com.example.prova_java.controller.dto.LoginDto;
import com.example.prova_java.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginService service;

    @PostMapping
    public ResponseEntity<LoginDto> logar(@RequestBody LoginDto loginDto) {
        LoginDto response = service.logar(loginDto);
        return ResponseEntity.ok(response);
    }
}
