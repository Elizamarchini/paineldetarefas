package com.marchini.paineldetarefas;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class TarefasService {
    private HashMap<Integer, TarefaEntity>  listaTarefas = new HashMap<>();
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada");
        }
        return tarefa;
    }

    public void deletarTarefaPorId(Integer id) {
        if (!this.listaTarefas.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada");
        }
        this.listaTarefas.remove(id);
    }

    public TarefaEntity atualizarTarefaPorId(Integer id, String titulo, String descricao) {
        TarefaEntity tarefaAtualizada = this.obterTarefaPorId(id);
        tarefaAtualizada.setDescricao(descricao);
        tarefaAtualizada.setTitulo(titulo);
        this.listaTarefas.put(id, tarefaAtualizada);
        return tarefaAtualizada;
    }
}
