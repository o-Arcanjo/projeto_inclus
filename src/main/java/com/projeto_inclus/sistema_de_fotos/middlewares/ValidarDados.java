package com.projeto_inclus.sistema_de_fotos.middlewares;



import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;


/**
 * Classe genérica para validação de dados de uma entidade.
 *
 * O objetivo é permitir comparar valores de campos entre:
 *   - uma entidade base de validação (que define os padrões/valores esperados);
 *   - uma entidade a ser validada (que contém os valores reais recebidos).
 *
 * A validação pode ser feita usando expressões regulares ou regras personalizadas
 * associadas a cada campo.
 *
 * @param <EntidadeBaseValidacao> tipo da entidade com valores de referência
 * @param <EntidadeValidada> tipo da entidade cujos valores serão validados
 */
public class ValidarDados <EntidadeBaseValidacao, EntidadeValidada>{

    /** Entidade que contém os valores de referência para validação */
    private EntidadeBaseValidacao entidadeValidacao;

    /** Entidade que será validada */
    private EntidadeValidada entidadeValidada;

    /**
     * Estrutura que armazena as propriedades de validação.
     *
     * - Chave externa: nome da classe (entidade);
     * - Valor: um mapa com chave = nome do campo e valor = expressão/valor esperado.
     */
    private final Map<String, Map<String, String>> propriedades = new HashMap<>();


    /**
     * Construtor da classe de validação.
     *
     * @param entidadeValidacao entidade com valores de referência para validação
     * @param entidadeValidada entidade que será validada
     */
    public ValidarDados (EntidadeBaseValidacao entidadeValidacao, EntidadeValidada entidadeValidada){
        this.entidadeValidacao = entidadeValidacao;
        this.entidadeValidada = entidadeValidada;
    }


    /**
     * Retorna todos os campos da classe fornecida (via reflexão).
     *
     * @param clazz classe que se deseja inspecionar
     * @return array de {@link Field} da classe
     */
    private Field[] retornarCampoClasse(Class<?> clazz){
        return clazz.getDeclaredFields();
    }

    /**
     * Valida se um campo específico de uma entidade atende às regras definidas em {@code propriedades}.
     *
     * O método busca a expressão regular ou valor esperado configurado para o campo
     * e verifica se o valor atual satisfaz essa condição.
     *
     * @param classeBase objeto que encapsula: nome da classe, campo e valor atual
     * @return {@code true} se o valor for válido, {@code false} caso contrário
     */
    private boolean validarCadaPropriedade (MiddlewareContext classeBase){
        String nomeClasse = classeBase.getClasseOrigem();
        String variavelClasse = classeBase.getVariavelAtual();
        String valorVariavelClasse = classeBase.getValorAtual();

        // Se a propriedade não existe para este valor, a validação falha
        if(!propriedades.get(nomeClasse).containsKey(valorVariavelClasse)){
            return false;
        }

        // Obtém a regra de validação (padrão regex ou valor fixo esperado)
        String propriedade = propriedades.get(nomeClasse).get(variavelClasse);
        return propriedade == null || Pattern.matches(propriedade, valorVariavelClasse);
    }

    /**
     * Retorna o tipo da classe de uma instância fornecida.
     *
     * @param classe instância de qualquer tipo
     * @return objeto {@link Class} correspondente ao tipo
     */
    private <Classe> Class<?> retornarTipoClasse(Classe classe){
        return classe.getClass();
    }

    /**
     * Retorna o nome simples (sem pacote) da classe de uma instância.
     *
     * @param classe instância de qualquer tipo
     * @return nome simples da classe
     */
    private <Classe> String retornarNomeClasse(Classe classe){
        return retornarTipoClasse(classe).getSimpleName();
    }

    /**
     * Verifica se um campo não existe no conjunto de propriedades
     * configuradas para validação.
     *
     * @param nomeClasse nome da classe que contém o campo
     * @param variavel nome do campo
     * @return {@code true} se o campo não existir, {@code false} caso contrário
     */
    private boolean ehCampoInexistente(String nomeClasse, String variavel){
        return !propriedades.get(nomeClasse).containsKey(variavel);
    }

    /**
     * Adiciona um novo campo e valor (chave-valor) de referência
     * para validação dentro de uma classe específica.
     *
     * Caso o campo já exista, não substitui.
     *
     * @param classeBase objeto com nome da classe, nome do campo e valor esperado
     * @return sempre {@code true}, para ser compatível com Function
     */
    private Boolean adicionarCampoValor(MiddlewareContext classeBase){
        String nomeClasse = classeBase.getClasseOrigem();
        String variavelClasse = classeBase.getVariavelAtual();
        String valorVariavelClasse = classeBase.getValorAtual();

        if(ehCampoInexistente(nomeClasse, variavelClasse)){
            propriedades.get(nomeClasse).put(variavelClasse,valorVariavelClasse);
        }
        return true;
    }

    /**
     * Itera sobre todos os campos de uma entidade e aplica um processador
     * (função customizada) a cada campo.
     *
     * O processador é um {@code Function<ClasseBase, Boolean>}, permitindo
     * tanto adicionar campos quanto validar valores.
     *
     * @param <Entidade> tipo da entidade sendo iterada
     * @param entidade instância da entidade
     * @param processador função que processa cada campo e retorna um boolean
     * @return {@code true} se todos os campos processados retornarem true, {@code false} caso contrário
     */
    private <Entidade> boolean iterarCampos(Entidade entidade, Function<MiddlewareContext,Boolean> processador){
        Field[] campos = retornarCampoClasse(retornarTipoClasse(entidade));
        for(Field campo : campos){
            campo.setAccessible(true);
            try{
                Object valorObj = campo.get(entidade);

                // Apenas Strings são permitidas como valores
                if(!(valorObj instanceof String)){
                    throw new IllegalArgumentException("O valor do campo '"
                            + campo.getName() + "' deve ser uma String");
                }

                String variavel = campo.getName();
                String nomeClasse = retornarNomeClasse(entidadeValidacao);
                String valor = (String) valorObj;

                // Cria objeto que encapsula os dados do campo atual
                MiddlewareContext classeBase = MiddlewareContext.builder()
                        .classeOrigem(nomeClasse)
                        .variavelAtual(variavel)
                        .valorAtual(valor)
                        .build();

                // Se algum processador retornar false, interrompe
                if (!processador.apply(classeBase)) {
                    return false;
                }

            }catch(IllegalAccessException | IllegalArgumentException e){
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * Valida todos os campos da entidade validada, verificando se os valores
     * batem com as regras configuradas na entidade de referência.
     *
     * @return {@code true} se todos os campos atenderem às regras, {@code false} caso contrário
     */
    public boolean validarCampos(){
        String nomeClasse = retornarNomeClasse(entidadeValidada);

        // Se não houver propriedades definidas para essa classe, não há como validar
        if(!propriedades.containsKey(nomeClasse)){
            return false;
        }

        return iterarCampos(entidadeValidada, this::validarCadaPropriedade);
    }

    /**
     * Adiciona os campos da entidade base de validação ao mapa de propriedades.
     *
     * Isso permite registrar quais valores/expressões devem ser usados como
     * referência durante o processo de validação.
     */
    public void adicionarCamposParaValidacao (){
        String nomeClasse = retornarNomeClasse(entidadeValidacao);
        propriedades.putIfAbsent(nomeClasse, new HashMap<>());

        iterarCampos(entidadeValidacao, this::adicionarCampoValor);
    }

}
