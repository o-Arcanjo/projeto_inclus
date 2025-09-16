package com.projeto_inclus.sistema_de_fotos.rest.dto;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class DTOFactory {
    private final Map<Class<? extends IDTO>, Function<Object, ? extends IDTO>> builders = new HashMap<>();


    public <T extends IDTO, E> void register(Class<T> dtoClass, Function<E,T> builder){
        builders.put(dtoClass, (Function<Object, T>) builder);
    }

    public <T extends IDTO, E> T build(Class<T> dtoClass, E entity) {
        Function<Object, ? extends IDTO> builder = builders.get(dtoClass);
        if (builder == null) {
            throw new IllegalArgumentException("Nenhum builder registrado para o DTO: " + dtoClass);
        }
        return dtoClass.cast(builder.apply(entity));
    }
}
