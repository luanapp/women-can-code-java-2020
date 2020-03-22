package br.com.wcc.banco.operacao;

import br.com.wcc.banco.model.Cliente;
import br.com.wcc.banco.model.Conta;

import java.math.BigDecimal;

public class SaldoOperacao extends Operacao<Void, BigDecimal> {
    public SaldoOperacao() {
        super(Type.SALDO);
    }

    @Override
    public BigDecimal executar(Cliente cliente, Conta conta, Void parametro) {
        return conta.getSaldo();
    }
}
