package br.com.wcc.banco.servico;

import br.com.wcc.banco.model.Cliente;

public interface ClienteServico {
    /**
     * Busca um cliente com o id fornecido
     *
     * @param id id do cliente
     * @return um {@link Cliente cliente}
     * ou {@code null} caso não exista um cliente com o id fornecido
     */
    Cliente buscaClientePorId(Integer id);
}
