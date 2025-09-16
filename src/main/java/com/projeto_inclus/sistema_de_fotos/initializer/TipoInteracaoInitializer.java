package com.projeto_inclus.sistema_de_fotos.initializer;

import com.projeto_inclus.sistema_de_fotos.entity.TipoInteracao;
import com.projeto_inclus.sistema_de_fotos.enums.TipoInteracaoEnum;
import com.projeto_inclus.sistema_de_fotos.repository.ITipoInteracaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TipoInteracaoInitializer implements CommandLineRunner {

    @Autowired
    private final ITipoInteracaoRepository tipoInteracaoRepository;

    public void run(String... args){
        for(TipoInteracaoEnum tipoInteracaoEnum : TipoInteracaoEnum.values()){
            tipoInteracaoRepository.findTipoInteracaoByTipoInteracao(tipoInteracaoEnum.getTipoInteracao())
                    .orElseGet(() -> {
                        TipoInteracao tipoInteracao = new TipoInteracao();
                        tipoInteracao.setTipoInteracao(tipoInteracaoEnum.getTipoInteracao());
                        return tipoInteracaoRepository.save(tipoInteracao);
                    });
        }
    }
}
