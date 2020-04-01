package br.com.wcc.banco.model;

import java.math.BigDecimal;

public class Cliente {
    private Integer id;
    private String nome;
    private Integer idConta;
    private BigDecimal salario;

    public Cliente(Integer id, String nome, Integer idConta, BigDecimal salario) {
        this.id = id;
        this.nome = nome;
        this.idConta = idConta;
        this.salario = salario;
    }

    public Integer getIdConta() {
        return idConta;
    }

    public String getNome() {
        return nome;
    }

    public Cliente(BigDecimal salario) {
        this.salario = salario;
    }
}
