package com.marchini.paineldetarefas.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Representa uma tarefa no sistema")
public class TarefaEntity {

    @Schema(description = "Identificador único da tarefa", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @Schema(description = "Título da tarefa", example = "Estudar Spring Boot")
    @NotBlank(message = "Título é obrigatório")
    @Size(max = 100, message = "Título deve ter no máximo 100 caracteres")
    private String titulo;

    @Schema(description = "Descrição detalhada da tarefa", example = "Revisar anotações da aula")
    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @Schema(description = "Status atual da tarefa", example = "PENDENTE", accessMode = Schema.AccessMode.READ_ONLY)
    private String status;

    public TarefaEntity(String titulo, String descricao, Integer id) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.id = id;
        this.status = "PENDENTE";
    }

    public Integer getId() { return id; }

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}