package com.projeto_inclus.sistema_de_fotos.service;

public interface IAuthService{
    String criptografarSenha(String senha);
    boolean verificarSenha(String senhaRecebida, String hashArmazenado);
    boolean autenticar(String token);
    boolean autorizar(String papelEsperado, String papelRecebido);
}
