package br.com.wcc.banco.model;

public class Cliente {
    private Integer id;
    private String nome;

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
