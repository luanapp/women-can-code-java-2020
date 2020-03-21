package br.com.wcc.banco.servico.impl;

import br.com.wcc.banco.model.Conta;
import br.com.wcc.banco.repositorio.ContaRepositorio;
import br.com.wcc.banco.servico.ContaServico;

public class ContaServicoImpl implements ContaServico {
    ContaRepositorio contaRepositorio;

    public ContaServicoImpl(ContaRepositorio contaRepositorio) {
        this.contaRepositorio = contaRepositorio;
    }

    @Override
    public Conta buscarPorId(Integer id) {
        return contaRepositorio.buscarPorId(id);
    }

    @Override
    public void salvar(Conta conta) {

    }
}
