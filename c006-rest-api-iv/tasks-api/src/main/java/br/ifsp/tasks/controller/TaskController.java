package br.ifsp.tasks.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ifsp.tasks.dto.page.PagedResponse;
import br.ifsp.tasks.dto.task.TaskPatchDTO;
import br.ifsp.tasks.dto.task.TaskRequestDTO;
import br.ifsp.tasks.dto.task.TaskResponseDTO;
import br.ifsp.tasks.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Tarefas", description = "API para gerenciamento de tarefas")
@Validated
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "Criar nova tarefa", description = "Cria uma nova tarefa com os dados fornecidos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "403", description = "Acesso negado", content = @Content)
    })
    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskRequestDTO dto) {
        TaskResponseDTO responseDTO = taskService.createTask(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @Operation(summary = "Buscar todas as tarefas", description = "Busca todas as tarefas com paginação")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso!"),
        @ApiResponse(responseCode = "403", description = "Acesso negado", content = @Content)
    })
    @GetMapping
    public ResponseEntity<PagedResponse<TaskResponseDTO>> getAllTasks(Pageable pageable) {
        return ResponseEntity.ok(taskService.getAllTasks(pageable));
    }

    @Operation(summary = "Buscar tarefa por ID", description = "Busca uma tarefa pelo ID fornecido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarefa encontrada"),
        @ApiResponse(responseCode = "404", description = "Tarefa não encontrada"),
        @ApiResponse(responseCode = "403", description = "Acesso negado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @Operation(summary = "Buscar tarefas por categoria", description = "Retorna uma lista paginada de tarefas cujo campo de categoria contém o termo pesquisado")
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado", content = @Content)
    })
    @GetMapping("/search")
    public ResponseEntity<PagedResponse<TaskResponseDTO>> getTasksByCategory(@RequestParam String category, Pageable pageable) {
        return ResponseEntity.ok(taskService.getTasksByCategory(category, pageable));
    }

    @Operation(summary = "Concluir tarefa", description = "Marca uma tarefa como concluída")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarefa concluída com sucesso"),
        @ApiResponse(responseCode = "404", description = "Tarefa não encontrada"),
        @ApiResponse(responseCode = "403", description = "Acesso negado", content = @Content),
        @ApiResponse(responseCode = "409", description = "Não é possível concluir uma tarefa já concluída")
    })
    @PatchMapping("/api/tasks/{id}/concluir")
    public ResponseEntity<TaskResponseDTO> completeTask(@PathVariable Long id) {
        TaskResponseDTO responseDTO = taskService.completeTask(id);
        return ResponseEntity.ok(responseDTO);
    }

    @Operation(summary = "Atualizar tarefa parcialmente", description = "Atualiza parcialmente uma tarefa existente com os dados fornecidos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Tarefa não encontrada"),
        @ApiResponse(responseCode = "403", description = "Acesso negado", content = @Content),
        @ApiResponse(responseCode = "409", description = "Não é possível atualizar uma tarefa já concluída")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> patchTask(@PathVariable Long id, @Valid @RequestBody TaskPatchDTO dto) {
        TaskResponseDTO responseDTO = taskService.patchTask(id, dto);
        return ResponseEntity.ok(responseDTO);
    }

    @Operation(summary = "Atualizar tarefa", description = "Atualiza uma tarefa existente com os dados fornecidos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Tarefa não encontrada"),
        @ApiResponse(responseCode = "403", description = "Acesso negado", content = @Content),
        @ApiResponse(responseCode = "409", description = "Não é possível atualizar uma tarefa já concluída")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id, @Valid @RequestBody TaskRequestDTO dto) {
        TaskResponseDTO responseDTO = taskService.updateTask(id, dto);
        return ResponseEntity.ok(responseDTO);
    }

    @Operation(summary = "Deletar tarefa", description = "Deleta uma tarefa existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Tarefa deletada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Tarefa não encontrada"),
        @ApiResponse(responseCode = "403", description = "Acesso negado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
    
}
