package com.marchini.paineldetarefas.exception;

public class TarefaNaoEncontradaException extends RuntimeException {

    private final Integer id;

    public TarefaNaoEncontradaException(Integer id) {
        super("Tarefa com ID " + id + " não encontrada");
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}