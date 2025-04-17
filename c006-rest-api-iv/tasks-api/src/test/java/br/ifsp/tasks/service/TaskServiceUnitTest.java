package br.ifsp.tasks.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.util.ReflectionTestUtils;

import br.ifsp.tasks.dto.task.TaskResponseDTO;
import br.ifsp.tasks.enums.Prioridade;
import br.ifsp.tasks.exception.InvalidTaskStateException;
import br.ifsp.tasks.exception.ResourceNotFoundException;
import br.ifsp.tasks.model.Task;
import br.ifsp.tasks.repository.TaskRepository;

@ExtendWith(MockitoExtension.class)
public class TaskServiceUnitTest {
    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TaskService taskService;

    @Test
    void testGetTaskByIdSuccess() {
        Long id = 1L;
        Task task = new Task(
            "Tarefa de Teste", 
            "Descrição da tarefa", 
            Prioridade.ALTA, 
            LocalDate.now().plusDays(5), 
            false, 
            "Trabalho"
        );
        ReflectionTestUtils.setField(task, "id", id);

        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setId(id);
        dto.setTitulo("Tarefa de Teste");
        dto.setDescricao("Descrição da tarefa");
        dto.setPrioridade(Prioridade.ALTA);
        dto.setDataLimite(LocalDate.now().plusDays(5));
        dto.setConcluida(false);
        dto.setCategoria("Trabalho");

        when(taskRepository.findById(id)).thenReturn(Optional.of(task));
        when(modelMapper.map(task, TaskResponseDTO.class)).thenReturn(dto);
        
        TaskResponseDTO response = taskService.getTaskById(id);

        assertEquals(dto, response);
    }

    @Test
    void testGetTaskByIdNotFound() {
        Long id = 1L;

        when(taskRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> { taskService.getTaskById(id); });
    }

    @Test
    void testDeleteTaskSuccess() {
        Long id = 1L;
        Task task = new Task(
            "Tarefa de Teste", 
            "Descrição da tarefa", 
            Prioridade.ALTA, 
            LocalDate.now().plusDays(5), 
            false, 
            "Trabalho"
        );
        ReflectionTestUtils.setField(task, "id", id);

        when(taskRepository.findById(id)).thenReturn(Optional.of(task));

        taskService.deleteTask(id);

        assertEquals(false, task.isConcluida());
    }

    @Test
    void testDeleteTaskCompleted() {
        Long id = 1L;
        Task task = new Task(
            "Tarefa de Teste", 
            "Descrição da tarefa", 
            Prioridade.ALTA, 
            LocalDate.now().plusDays(5), 
            true, 
            "Trabalho"
        );
        ReflectionTestUtils.setField(task, "id", id);

        when(taskRepository.findById(id)).thenReturn(Optional.of(task));

        assertThrows(InvalidTaskStateException.class, () -> { taskService.deleteTask(id); });
    }

    @Test
    void testDeleteTaskNotFound() {
        Long id = 999L;

        when(taskRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> { taskService.deleteTask(id); });
    }

    @Test
    void testGetTasksWithPagination() {

        Task task = new Task(
            "Tarefa de Teste", 
            "Descrição da tarefa", 
            Prioridade.ALTA, 
            LocalDate.now().plusDays(5), 
            false, 
            "Trabalho"
        );
        ReflectionTestUtils.setField(task, "id", 1L);

        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setId(1L);
        dto.setTitulo("Tarefa de Teste");
        dto.setDescricao("Descrição da tarefa");
        dto.setPrioridade(Prioridade.ALTA);
        dto.setDataLimite(LocalDate.now().plusDays(5));
        dto.setConcluida(false);
        dto.setCategoria("Trabalho");

        PageImpl<Task> tasksPage = new PageImpl<>(List.of(task));

        when(taskRepository.findAll((org.springframework.data.domain.Pageable) ArgumentMatchers.any())).thenReturn(tasksPage);
        when(modelMapper.map(task, TaskResponseDTO.class)).thenReturn(dto);

        Page<TaskResponseDTO> responsePage = taskService.getAllTasks(ArgumentMatchers.any());
        
        assertEquals(1, responsePage.getTotalElements());
        assertEquals(1, responsePage.getContent().size());
        assertEquals(dto, responsePage.getContent().get(0));
        assertEquals(task.getId(), responsePage.getContent().get(0).getId());
        assertEquals(task.getTitulo(), responsePage.getContent().get(0).getTitulo());
        assertEquals(task.getDescricao(), responsePage.getContent().get(0).getDescricao());
    }

    @Test
    void testGetTasksByCategory() {
        String categoria = "Trabalho";
        Task task = new Task(
            "Tarefa de Teste", 
            "Descrição da tarefa", 
            Prioridade.ALTA, 
            LocalDate.now().plusDays(5), 
            false, 
            categoria
        );
        ReflectionTestUtils.setField(task, "id", 1L);

        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setId(1L);
        dto.setTitulo("Tarefa de Teste");
        dto.setDescricao("Descrição da tarefa");
        dto.setPrioridade(Prioridade.ALTA);
        dto.setDataLimite(LocalDate.now().plusDays(5));
        dto.setConcluida(false);
        dto.setCategoria(categoria);

        PageImpl<Task> tasksPage = new PageImpl<>(List.of(task));

        when(taskRepository.findByCategoriaContainingIgnoreCase(eq(categoria), (org.springframework.data.domain.Pageable) ArgumentMatchers.any())).thenReturn(tasksPage);
        when(modelMapper.map(task, TaskResponseDTO.class)).thenReturn(dto);

        Page<TaskResponseDTO> responsePage = taskService.getTasksByCategory(categoria, ArgumentMatchers.any());
        
        assertEquals(1, responsePage.getTotalElements());
        assertEquals(1, responsePage.getContent().size());
        assertEquals(dto, responsePage.getContent().get(0));
    }
}
