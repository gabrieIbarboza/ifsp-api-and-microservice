package br.ifsp.tasks.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ifsp.tasks.dto.page.PagedResponse;
import br.ifsp.tasks.dto.task.TaskPatchDTO;
import br.ifsp.tasks.dto.task.TaskRequestDTO;
import br.ifsp.tasks.dto.task.TaskResponseDTO;
import br.ifsp.tasks.exception.InvalidTaskStateException;
import br.ifsp.tasks.exception.ResourceNotFoundException;
import br.ifsp.tasks.mapper.PagedResponseMapper;
import br.ifsp.tasks.model.Task;
import br.ifsp.tasks.repository.TaskRepository;
import jakarta.validation.Valid;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;
    private final PagedResponseMapper pagedResponseMapper;

    public TaskService(TaskRepository taskRepository, ModelMapper modelMapper, PagedResponseMapper pagedResponseMapper) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
        this.pagedResponseMapper = pagedResponseMapper;
    }

    public TaskResponseDTO createTask(@Valid TaskRequestDTO dto) {
        Task task = modelMapper.map(dto, Task.class);
        task.setCriadaEm(LocalDateTime.now());
        task.setConcluida(false);
        task = taskRepository.save(task);
        return modelMapper.map(task, TaskResponseDTO.class);
    }

    public PagedResponse<TaskResponseDTO> getAllTasks (Pageable pageable) {
        Page<Task> tasks = taskRepository.findAll(pageable);
        return pagedResponseMapper.toPagedResponse(tasks, TaskResponseDTO.class);
    }

    public TaskResponseDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada: " + id));
        return modelMapper.map(task, TaskResponseDTO.class);
    }

    public PagedResponse<TaskResponseDTO> getTasksByCategory(String categoria, Pageable pageable) {
        Page<Task> tasks = taskRepository.findByCategoriaContainingIgnoreCase(categoria, pageable);
        return pagedResponseMapper.toPagedResponse(tasks, TaskResponseDTO.class);
    }

    public TaskResponseDTO completeTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada: " + id));
        if (task.getConcluida()) {
            throw new InvalidTaskStateException("Não é possível concluir uma tarefa já concluída: Id " + id);
        }
        task.setConcluida(true);
        task = taskRepository.save(task);
        return modelMapper.map(task, TaskResponseDTO.class);
    }

    public TaskResponseDTO patchTask(Long id, @Valid TaskPatchDTO dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada: " + id));
        if (task.getConcluida()) {
            throw new InvalidTaskStateException("Não é possível atualizar uma tarefa concluída: Id " + id);
        }

        modelMapper.map(dto, task);
        task.setId(id);
        task.setCriadaEm(task.getCriadaEm());
        task = taskRepository.save(task);
        return modelMapper.map(task, TaskResponseDTO.class);
    }

    public TaskResponseDTO updateTask(Long id, @Valid TaskRequestDTO dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada: " + id));
        if (task.getConcluida()) {
            throw new InvalidTaskStateException("Não é possível atualizar uma tarefa concluída: Id " + id);
        }
        modelMapper.map(dto, task);
        task = taskRepository.save(task);
        return modelMapper.map(task, TaskResponseDTO.class);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada: " + id));
        if (task.getConcluida()) {
            throw new InvalidTaskStateException("Não é possível deletar uma tarefa concluída: Id " + id);
        }
        taskRepository.delete(task);
    }

}
