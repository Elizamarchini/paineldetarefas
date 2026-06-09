package com.marchini.paineldetarefas.controller;

import com.marchini.paineldetarefas.model.TarefaEntity;
import com.marchini.paineldetarefas.service.TarefasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tarefas")
@Tag(name = "Tarefas", description = "Operações de gerenciamento de tarefas pessoais")
public class TarefasController {

    private final TarefasService tarefasService;

    public TarefasController(TarefasService tarefasService) {
        this.tarefasService = tarefasService;
    }

    @GetMapping
    @Operation(summary = "Listar tarefas", description = "Retorna todas as tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<List<TarefaEntity>> getTarefas() {
        return ResponseEntity.ok(tarefasService.obterTodasTarefas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar tarefa por ID", description = "Retorna uma única tarefa pelo seu identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tarefa encontrada"),
        @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    public ResponseEntity<TarefaEntity> obterPorId(
            @Parameter(description = "ID da tarefa") @PathVariable Integer id) {
        return ResponseEntity.ok(tarefasService.obterTarefaPorId(id));
    }

    @PostMapping
    @Operation(summary = "Criar tarefa", description = "Adiciona uma nova tarefa com status PENDENTE")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<TarefaEntity> addTarefa(@Valid @RequestBody TarefaEntity tarefaEntity) {
        TarefaEntity tarefa = tarefasService.addTarefa(tarefaEntity.getTitulo(), tarefaEntity.getDescricao());
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefa);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar tarefa", description = "Atualiza o título e a descrição de uma tarefa existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    public ResponseEntity<TarefaEntity> atualizar(
            @Parameter(description = "ID da tarefa") @PathVariable Integer id,
            @Valid @RequestBody TarefaEntity tarefaEntity) {
        TarefaEntity tarefa = tarefasService.atualizarTarefaPorId(id, tarefaEntity.getTitulo(), tarefaEntity.getDescricao());
        return ResponseEntity.ok(tarefa);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir tarefa", description = "Remove uma tarefa pelo seu identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Tarefa excluída com sucesso"),
        @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    public ResponseEntity<Void> removeTarefa(
            @Parameter(description = "ID da tarefa") @PathVariable Integer id) {
        tarefasService.deletarTarefaPorId(id);
        return ResponseEntity.noContent().build();
    }
}