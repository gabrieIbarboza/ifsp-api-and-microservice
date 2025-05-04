package br.ifsp.tasks.task;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import br.ifsp.tasks.dto.task.TaskRequestDTO;
import br.ifsp.tasks.dto.task.TaskResponseDTO;
import br.ifsp.tasks.exception.InvalidTaskStateException;
import br.ifsp.tasks.exception.ResourceNotFoundException;
import br.ifsp.tasks.mapper.PagedResponseMapper;
import br.ifsp.tasks.model.Prioridade;
import br.ifsp.tasks.model.Task;
import br.ifsp.tasks.repository.TaskRepository;
import br.ifsp.tasks.service.TaskService;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    
    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PagedResponseMapper pagedResponseMapper;

    @InjectMocks
    private TaskService taskService;
    
    @Test
    void shouldCreateTaskWithValidData() {
        TaskRequestDTO dto = new TaskRequestDTO();
        dto.setTitulo("Valid Task");
        dto.setPrioridade(Prioridade.MEDIA);
        dto.setDataLimite(LocalDateTime.now().plusDays(2));
        dto.setCategoria("Trabalho");
        
        Task taskEntity = new Task();
        Task savedTask = new Task();
        savedTask.setId(1L);
        savedTask.setTitulo("Valid Task");
        
        when(modelMapper.map(dto, Task.class)).thenReturn(taskEntity);
        when(taskRepository.save(any())).thenReturn(savedTask);
        when(modelMapper.map(savedTask, TaskResponseDTO.class)).thenReturn(new TaskResponseDTO());
        
        TaskResponseDTO response = taskService.createTask(dto);
        assertNotNull(response);
    }
    
    @Test
    void shouldFetchTaskById() {
        Task task = new Task();
        task.setId(1L);
        
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(modelMapper.map(any(), eq(TaskResponseDTO.class))).thenReturn(new TaskResponseDTO());
        
        TaskResponseDTO response = taskService.getTaskById(1L);
        assertNotNull(response);
    }

    @Test
    void shouldNotFoundTaskById() {
        Long id = 1L;

        when(taskRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> { taskService.getTaskById(id); });
    }
    
    @Test
    void shouldThrowErrorWhenDeletingCompletedTask() {
        Task completedTask = new Task();
        completedTask.setId(1L);
        completedTask.setConcluida(true);
        
        when(taskRepository.findById(1L)).thenReturn(Optional.of(completedTask));
        
        assertThrows(InvalidTaskStateException.class, () -> taskService.deleteTask(1L));
    }
    
}
