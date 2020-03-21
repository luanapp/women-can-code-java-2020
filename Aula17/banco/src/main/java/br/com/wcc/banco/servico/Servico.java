package br.com.wcc.banco.servico;

public interface Servico<T> {
    /**
     * Busca uma entidade com o id fornecido
     *
     * @param id id da entidade
     * @return uma {@link T entidade}
     * ou {@code null} caso não exista uma entidade com o id fornecido
     */
    T buscarPorId(Integer id);

    /**
     * Salva uma entidade
     *
     * @param entidade entidade a ser salva
     */
    void salvar(T entidade);
}
