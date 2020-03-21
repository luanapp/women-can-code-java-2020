package br.com.wcc.banco.servico.impl;

import br.com.wcc.banco.model.Cliente;
import br.com.wcc.banco.repositorio.ClienteRepositorio;
import br.com.wcc.banco.servico.ClienteServico;

public class ClienteServicoImpl implements ClienteServico {
    private ClienteRepositorio clienteRepositorio;

    public ClienteServicoImpl(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    @Override
    public Cliente buscaClientePorId(Integer id) {
        return clienteRepositorio.buscaClientePorId(id);
    }
}
