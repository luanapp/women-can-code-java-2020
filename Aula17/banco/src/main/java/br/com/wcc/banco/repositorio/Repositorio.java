package br.com.wcc.banco.repositorio;

public interface Repositorio<T> {
    /**
     * Busca uma {@link T entidade} que tenha o id fornecido
     *
     * @param id id da entidade
     * @return A {@link T entidade} com o id fornecido
     * ou {@code null} caso não exista uma entidade com esse id
     */
    T buscarPorId(Integer id);

    /**
     * Salva a entidade no repositório (CSV, Banco de Dados, etc)
     *
     * @param entidade entidade a ser salva
     */
    void salvar(T entidade);
}
