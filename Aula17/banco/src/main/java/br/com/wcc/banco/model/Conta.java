package br.com.wcc.banco.model;

import java.math.BigDecimal;

public class Conta {
    private Integer id;
    private Type tipoConta;
    private BigDecimal saldo;

    public Conta(Integer id, Type tipoConta, BigDecimal saldo) {
        this.id = id;
        this.tipoConta = tipoConta;
        this.saldo = saldo;
    }

    public Integer getId() {
        return id;
    }

    public Type getTipoConta() {
        return tipoConta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setTipoConta(Type tipoConta) {
        this.tipoConta = tipoConta;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public enum Type {
        REGULAR,
        PRIME
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Conta)) return false;

        Conta conta = (Conta) o;

        if (getId() != null ? !getId().equals(conta.getId()) : conta.getId() != null) return false;
        if (getTipoConta() != conta.getTipoConta()) return false;
        return getSaldo() != null ? getSaldo().equals(conta.getSaldo()) : conta.getSaldo() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getTipoConta() != null ? getTipoConta().hashCode() : 0);
        result = 31 * result + (getSaldo() != null ? getSaldo().hashCode() : 0);
        return result;
    }
}
