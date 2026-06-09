package com.marchini.paineldetarefas.service;

import com.marchini.paineldetarefas.exception.TarefaNaoEncontradaException;
import com.marchini.paineldetarefas.model.TarefaEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TarefasService {

    private final HashMap<Integer, TarefaEntity> listaTarefas = new HashMap<>();
    private Integer contadorId = 1;

    public TarefaEntity addTarefa(String titulo, String descricao) {
        TarefaEntity novaTarefa = new TarefaEntity(titulo, descricao, this.contadorId);
        this.listaTarefas.put(this.contadorId, novaTarefa);
        this.contadorId += 1;
        return novaTarefa;
    }

    public List<TarefaEntity> obterTodasTarefas() {
        return new ArrayList<>(this.listaTarefas.values());
    }

    public TarefaEntity obterTarefaPorId(Integer id) {
        TarefaEntity tarefa = this.listaTarefas.get(id);
        if (tarefa == null) {
            throw new TarefaNaoEncontradaException(id);
        }
        return tarefa;
    }

    public void deletarTarefaPorId(Integer id) {
        if (!this.listaTarefas.containsKey(id)) {
            throw new TarefaNaoEncontradaException(id);
        }
        this.listaTarefas.remove(id);
    }

    public TarefaEntity atualizarTarefaPorId(Integer id, String titulo, String descricao) {
        TarefaEntity tarefaAtualizada = this.obterTarefaPorId(id);
        tarefaAtualizada.setTitulo(titulo);
        tarefaAtualizada.setDescricao(descricao);
        this.listaTarefas.put(id, tarefaAtualizada);
        return tarefaAtualizada;
    }
}