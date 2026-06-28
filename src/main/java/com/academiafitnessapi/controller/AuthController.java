package com.academiafitnessapi.controller;

import com.academiafitnessapi.model.Usuario;
import com.academiafitnessapi.repository.UsuarioRepository;
import com.academiafitnessapi.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/registrar")
    public Map<String, String> registrar(@RequestBody Usuario usuario) {
        if (repository.findByUsername(usuario.getUsername()).isPresent()) {
            return Map.of("erro", "Usuário já existe");
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        if (usuario.getRole() == null || usuario.getRole().isBlank()) {
            usuario.setRole("USER");
        }
        repository.save(usuario);
        return Map.of("mensagem", "Usuário registrado com sucesso");
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Usuario login) {
        Optional<Usuario> existente = repository.findByUsername(login.getUsername());
        if (existente.isEmpty() || !passwordEncoder.matches(login.getPassword(), existente.get().getPassword())) {
            return Map.of("erro", "Usuário ou senha inválidos");
        }
        String role = existente.get().getRole();
        String token = jwtUtil.gerarToken(login.getUsername(), role == null ? "USER" : role);
        return Map.of("token", token, "role", role == null ? "USER" : role);
    }
}
