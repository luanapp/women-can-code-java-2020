package br.com.wcc.banco.repositorio;

import br.com.wcc.banco.model.Cliente;

public interface ClienteRepositorio {
    Cliente buscaClientePorId(Integer id);
}
