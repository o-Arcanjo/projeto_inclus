package com.projeto_inclus.sistema_de_fotos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService{
    private final PasswordEncoder passwordEncoder;

    @Override
    public String criptografarSenha(String senha) {
        return passwordEncoder.encode(senha);
    }

    @Override
    public boolean verificarSenha(String senhaRecebida, String hashArmazenado) {
        return passwordEncoder.matches(senhaRecebida, hashArmazenado);
    }

    @Override
    public boolean autenticar(String token) {
        return false;
    }

    @Override
    public boolean autorizar(String papelEsperado, String papelRecebido) {
        return false;
    }
}
