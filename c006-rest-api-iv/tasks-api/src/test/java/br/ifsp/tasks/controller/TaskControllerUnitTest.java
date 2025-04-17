package br.ifsp.tasks.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;
import br.ifsp.tasks.dto.task.TaskResponseDTO;
import br.ifsp.tasks.enums.Prioridade;
import br.ifsp.tasks.exception.ResourceNotFoundException;
import br.ifsp.tasks.model.Task;
import br.ifsp.tasks.service.TaskService;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TaskControllerUnitTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @Test
    void testGetTaskByIdSuccessfully() {
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

        when(taskService.getTaskById(id)).thenReturn(dto);
        
        ResponseEntity<TaskResponseDTO> response = taskController.getTaskById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testGetTaskByIdNotFound() {
        Long id = 1L;

        when(taskService.getTaskById(id)).thenThrow(new ResourceNotFoundException("Tarefa não encontrada"));

        assertThrows(ResourceNotFoundException.class, () -> taskController.getTaskById(id));
    }

}