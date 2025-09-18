package com.projeto_inclus.sistema_de_fotos.enums;

public enum ErrorTypeEnum {
    ERRO_INESPERADO("errors/erro-inesperado"),        // Erro interno inesperado do servidor (status 500)
    ERRO_DE_VALIDACAO("errors/erro-de-validacao"),      // Dados de entrada inválidos ou malformados (status 400)
    REQUISICAO_INVALIDA("errors/requisicao-invalida"),    // Solicitação malformada ou sintaxe incorreta (status 400)
    ESTADO_INVALIDO("errors/estado-invalido"),        // Entidade processável mas com estado semântico inválido (status 422)
    RECURSO_NAO_ENCONTRADO("errors/recurso-nao-encontrado"), // Recurso solicitado não existe ou não foi encontrado (status 404)
    NAO_AUTENTICADO("errors/nao-autenticado"),        // Credenciais de autenticação ausentes ou inválidas (status 401)
    NAO_AUTORIZADO("errors/nao-autorizado"),         // Usuário autenticado mas sem permissão para a operação (status 403)
    CONFLITO("errors/conflito"),               // Solicitação conflita com estado atual do recurso (status 409)
    SERVICO_INDISPONIVEL("errors/servico-indisponivel");// Serviço temporariamente indisponível ou em manutenção (status 503)

    private final String uri;

    ErrorTypeEnum(String uri){
        this.uri = uri;
    }

    public String getUri(){
        return uri;
    }
}
