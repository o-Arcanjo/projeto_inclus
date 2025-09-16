    package com.projeto_inclus.sistema_de_fotos.enums;
    import lombok.Getter;

    @Getter
    public enum TipoMimeEnum {
        JPEG("image/jpeg"),
        PNG("image/png"),
        WEBP("image/webp");

        private final String mime;

        TipoMimeEnum(String mime){
            this.mime = mime;
        }
    }
