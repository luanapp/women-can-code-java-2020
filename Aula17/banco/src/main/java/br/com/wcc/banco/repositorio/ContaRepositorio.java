package br.com.wcc.banco.repositorio;

import br.com.wcc.banco.model.Conta;

public interface ContaRepositorio {
    /**
     * Busca uma conta que tenha o id fornecido
     *
     * @param id id da conta
     * @return A {@link Conta conta} com o id fornecido
     * ou {@code null} caso não exista uma conta com esse id
     */
    Conta buscarContaPorId(Integer id);

    /**
     * Salva a conta no repositório (CSV, Banco de Dados, etc)
     *
     * @param conta conta a ser salva
     */
    void salvarConta(Conta conta);
}
