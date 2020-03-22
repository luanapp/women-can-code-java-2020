package br.com.wcc.banco.operacao;

import br.com.wcc.banco.model.Cliente;
import br.com.wcc.banco.model.Conta;

public abstract class Operacao<P, R> {
    private Type tipo;

    Operacao(Type tipo) {
        this.tipo = tipo;
    }

    public abstract R executar(Cliente cliente, Conta conta, P parametro);

    public Type getTipo() {
        return tipo;
    }

    public enum Type {
        SAQUE("Saque"),
        SALDO("Saldo"),
        DEPOSITO("Depósito");

        private String texto;

        Type(String texto) {
            this.texto = texto;
        }

        public String getTexto() {
            return texto;
        }
    }
}
