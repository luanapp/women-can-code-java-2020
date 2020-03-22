package br.com.wcc.banco.operacao;

import br.com.wcc.banco.model.Cliente;
import br.com.wcc.banco.model.Conta;
import br.com.wcc.banco.servico.ContaServico;

import java.math.BigDecimal;

public class DepositoOperacao extends Operacao<BigDecimal, BigDecimal> {
    ContaServico contaServico;

    public DepositoOperacao(ContaServico contaServico) {
        super(Type.DEPOSITO);
        this.contaServico = contaServico;
    }

    @Override
    public BigDecimal executar(Cliente cliente, Conta conta, BigDecimal deposito) {
        conta.setSaldo(conta.getSaldo().add(deposito));
        contaServico.salvar(conta);
        return conta.getSaldo();
    }
}
