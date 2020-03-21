package br.com.wcc.banco.repositorio;

import br.com.wcc.banco.model.Cliente;

public interface ClienteRepositorio {
    /**
     * Busca um cliente que tenha o id passado como parâmetro
     *
     * @param id id do cliente
     * @return {@Cliente cliente} cliente com o id fornecido
     * ou {@code null} caso o cliente não foi encontrado
     */
    Cliente buscaClientePorId(Integer id);
}
