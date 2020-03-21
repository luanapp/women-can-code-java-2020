package br.com.wcc.banco.model;

public class Cliente {
    private Integer id;
    private String nome;
    private Integer idConta;

    public Cliente(Integer id, String nome, Integer idConta) {
        this.id = id;
        this.nome = nome;
        this.idConta = idConta;
    }

    public Integer getIdConta() {
        return idConta;
    }

    public String getNome() {
        return nome;
    }
}
