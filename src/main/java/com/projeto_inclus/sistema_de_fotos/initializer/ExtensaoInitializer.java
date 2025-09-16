package com.projeto_inclus.sistema_de_fotos.initializer;
import com.projeto_inclus.sistema_de_fotos.entity.Extensao;
import com.projeto_inclus.sistema_de_fotos.enums.TipoMimeEnum;
import com.projeto_inclus.sistema_de_fotos.repository.IExtensaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExtensaoInitializer implements CommandLineRunner {

    @Autowired
    private final IExtensaoRepository extensaoRepository;

    @Override
    public void run(String... args){
        for(TipoMimeEnum tipoMime : TipoMimeEnum.values()){
            extensaoRepository.findExtensaoByTipoMime(tipoMime.getMime())
                    .orElseGet(() -> {
                                Extensao novaExtensao = new Extensao();
                                novaExtensao.setTipoMime(tipoMime.getMime());
                                return extensaoRepository.save(novaExtensao);
                            });
        }
    }
}
