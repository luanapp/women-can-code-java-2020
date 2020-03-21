package br.com.wcc.banco.servico;

import br.com.wcc.banco.model.Cliente;
import br.com.wcc.banco.repositorio.ClienteRepositorio;

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
