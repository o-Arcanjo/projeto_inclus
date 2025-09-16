package com.projeto_inclus.sistema_de_fotos.service;
import com.projeto_inclus.sistema_de_fotos.util.EstruturaToken;
import com.projeto_inclus.sistema_de_fotos.util.Payload;


public interface ITokenService {
    String gerarToken(EstruturaToken estruturaToken);
}
