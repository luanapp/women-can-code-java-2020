package br.com.wcc.banco.model;

import java.math.BigDecimal;

public interface Operacao {
    /**
     * Executa uma operação na {@link Conta conta} passada como parâmetro.
     * O retorno exibe o saldo da conta após a operação.
     * @param conta conta cuja operação será executada
     * @return Saldo da conta após a operação
     */
    BigDecimal executar(Conta conta);
}
