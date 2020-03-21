package br.com.wcc.banco.servico;

import br.com.wcc.banco.model.Conta;

public interface ContaServico {
    /**
     * Busca uma {@link Conta conta} com o id fornecido
     *
     * @param id id da conta
     * @return A {@link Conta conta} encontrada
     * ou {@code null} caso não exista uma conta com o id fornecido
     */
    Conta buscarContaPorId(Integer id);

    /**
     * Salva a conta fornecida
     *
     * @param conta conta a ser salva
     */
    void salvarConta(Conta conta);
}
