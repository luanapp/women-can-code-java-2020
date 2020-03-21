package br.com.wcc.banco.repositorio;

import br.com.wcc.banco.model.Conta;

public interface ContaRepositorio {
    Conta buscarContaPorId(Integer id);
    void salvarConta(Conta conta);
}
