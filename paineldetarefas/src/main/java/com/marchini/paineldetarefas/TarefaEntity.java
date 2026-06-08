package com.marchini.paineldetarefas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TarefaEntity {
    @NotBlank(message = "Título é obrigatório")
    @Size(max = 100)
    private String titulo;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    private Integer id;
    private String status;

    public TarefaEntity(String titulo, String descricao, Integer id) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.id = id;
        this.status = "PENDENTE";
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

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}