package br.com.wcc.banco.operacao;

import br.com.wcc.banco.model.Cliente;
import br.com.wcc.banco.model.Conta;
import br.com.wcc.banco.servico.ContaServico;

import java.math.BigDecimal;

public class SaqueOperacao extends Operacao<BigDecimal, BigDecimal> {
    private ContaServico contaServico;

    public SaqueOperacao(ContaServico contaServico) {
        super(Type.SAQUE);
        this.contaServico = contaServico;
    }


    @Override
    public BigDecimal executar(Cliente cliente, Conta conta, BigDecimal saque) {
        conta.setSaldo(conta.getSaldo().subtract(saque));
        contaServico.salvar(conta);
        return conta.getSaldo();
    }
}
