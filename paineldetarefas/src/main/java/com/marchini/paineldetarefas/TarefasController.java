package com.marchini.paineldetarefas;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {
    private TarefasService tarefasService;
    public TarefasController(TarefasService tarefasService){
     this.tarefasService = tarefasService;
    }
    @GetMapping
    public ResponseEntity<List<TarefaEntity>> getTarefas() {
        return ResponseEntity.ok(this.tarefasService.obterTodasTarefas());
    }
    @PostMapping
    public ResponseEntity<TarefaEntity> addTarefa(@RequestBody TarefaEntity tarefaEntity) {
        TarefaEntity tarefa =  this.tarefasService.addTarefa(tarefaEntity.getTitulo(), tarefaEntity.getDescricao());
        return ResponseEntity.ok(tarefa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeTarefa(@PathVariable Integer id) {
        this.tarefasService.deletarTarefaPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaEntity> obterPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(this.tarefasService.obterTarefaPorId(id));
    }

    @PutMapping
    public ResponseEntity<TarefaEntity> atualizar(@RequestBody TarefaEntity tarefaEntity) {
        TarefaEntity tarefa =  this.tarefasService.atualizarTarefaPorId(tarefaEntity.getId(), tarefaEntity.getTitulo(), tarefaEntity.getDescricao());
        return ResponseEntity.ok(tarefa);
    }
}
