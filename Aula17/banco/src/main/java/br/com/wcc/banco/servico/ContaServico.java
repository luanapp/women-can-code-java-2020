package br.com.wcc.banco.servico;

import br.com.wcc.banco.model.Conta;

public interface ContaServico {
    Conta buscarContaPorId(Integer id);
}
