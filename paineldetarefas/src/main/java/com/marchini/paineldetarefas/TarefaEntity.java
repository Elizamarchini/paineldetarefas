package com.marchini.paineldetarefas;


public class TarefaEntity {
    private String titulo;
    private String descricao;
    private Integer id;

    public TarefaEntity(String titulo, String descricao, Integer id){
        this.titulo = titulo;
        this.descricao = descricao;
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getId() {
        return this.id;
    }

};
