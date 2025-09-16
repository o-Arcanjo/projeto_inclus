package com.projeto_inclus.sistema_de_fotos.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto_inclus.sistema_de_fotos.util.EstruturaToken;
import com.projeto_inclus.sistema_de_fotos.util.Payload;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class TokenServiceImpl implements ITokenService{
    private final ObjectMapper objectMapper =  new ObjectMapper();
    private String serializePayload(Payload payload){
        try{
            return objectMapper.writeValueAsString(payload);
        }catch (JsonProcessingException e){
            throw new RuntimeException("Erro ao serializar o payload do token", e);
        }
    }

    @Override
    public String gerarToken(EstruturaToken estruturaToken) {
        SecretKey chaveSecreta = Keys.hmacShaKeyFor(estruturaToken.chaveSecreta().getBytes(StandardCharsets.UTF_8));
        Payload payload = estruturaToken.payload();
        return Jwts.builder()
                .signWith(chaveSecreta)
                .issuer("projeto_inclus")
                .subject(payload.id().toString())
                .expiration(estruturaToken.expiracaoToken())
                .notBefore(estruturaToken.criacaoToken())
                .issuedAt(estruturaToken.criacaoToken())
                .id(UUID.randomUUID().toString())
                .claim("dadosUsuario", serializePayload(payload))
                .compact();
    }
}
