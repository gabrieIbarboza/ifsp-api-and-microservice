package br.ifsp.tasks.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ifsp.tasks.dto.task.TaskRequestDTO;
import br.ifsp.tasks.model.Prioridade;
import br.ifsp.tasks.model.Task;
import br.ifsp.tasks.repository.TaskRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private TaskRepository taskRepository;
    
    @BeforeEach
    void cleanDb() {
        taskRepository.deleteAll();
    }
    
    @Test
    void shouldCreateTask() throws Exception {
        String titulo = "Tarefa de Teste";
        String descricao = "Descrição da tarefa";
        String prioridade = "ALTA";
        String concluida = "false";
        String categoria = "Trabalho";
        LocalDateTime date = LocalDateTime.now().plusDays(5);

        TaskRequestDTO dto = new TaskRequestDTO();
        dto.setTitulo(titulo);
        dto.setDescricao(descricao);
        dto.setPrioridade(Prioridade.fromString(prioridade));
        dto.setConcluida(false);
        dto.setDataLimite(date);
        dto.setCategoria(categoria);
        
        mockMvc.perform(post("/api/tasks").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))).andExpect(status().isCreated())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo").value(titulo))
                .andExpect(jsonPath("$.descricao").value(descricao))
                .andExpect(jsonPath("$.prioridade").value(prioridade))
                .andExpect(jsonPath("$.concluida").value(concluida))
                .andExpect(jsonPath("$.categoria").value(categoria));
    }
    
    @Test
    void shouldFailWithPastDueDate() throws Exception {
        TaskRequestDTO dto = new TaskRequestDTO();
        dto.setTitulo("Invalid Task");
        dto.setPrioridade(Prioridade.BAIXA);
        dto.setDataLimite(LocalDateTime.now().minusDays(1));
        dto.setCategoria("Trabalho");
        
        mockMvc.perform(post("/api/tasks").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))).andExpect(status().isBadRequest());
    }

    @Test
    void shouldFailTaskInvalid() throws Exception {
        String titulo = "";

        // descricao com mais de 500 caracteres
        StringBuilder descricaoBuilder = new StringBuilder();
        for (int i = 0; i < 501; i++) {
            descricaoBuilder.append("a");
        }
        String descricao = descricaoBuilder.toString();

        String categoria = "";

        // Definindo a data limite como 5 dias atrás
        LocalDate today = LocalDate.now().minusDays(5);
        String todayString = today.toString();
        
        String json = """
                    {
                        "titulo": "%s",
                        "descricao": "%s",
                        "dataLimite": "%s",
                        "categoria": "%s"
                    }
                """.formatted(titulo, descricao, todayString, categoria);

        mockMvc.perform(post("/api/tasks")
                .contentType("application/json")
                .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreateTaskInvalidPrioridade() throws Exception {
        String prioridade = "INVALIDA";
        String json = """
                    {
                        "prioridade": "%s"
                    }
                """.formatted(prioridade);

        mockMvc.perform(post("/api/tasks")
                .contentType("application/json")
                .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
    
    @Test
    void shouldGetTaskById() throws Exception {
        Task task = new Task();
        task.setTitulo("Search Me");
        task.setPrioridade(Prioridade.MEDIA);
        task.setDataLimite(LocalDateTime.now().plusDays(2));
        task.setCategoria("Outros");
        task.setConcluida(false);
        task.setCriadaEm(LocalDateTime.now().minusDays(2));
        Task saved = taskRepository.save(task);
        
        mockMvc.perform(get("/api/tasks/" + saved.getId())).andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Search Me"));
    }
    
    @Test
    void shouldNotDeleteCompletedTask() throws Exception {
        Task task = new Task();
        task.setTitulo("Search Me");
        task.setPrioridade(Prioridade.MEDIA);
        task.setDataLimite(LocalDateTime.now().plusDays(2));
        task.setCategoria("Outros");
        task.setConcluida(true);
        task.setCriadaEm(LocalDateTime.now().minusDays(2));
        Task saved = taskRepository.save(task);
        
        mockMvc.perform(delete("/api/tasks/" + saved.getId())).andExpect(status().isConflict());
    }
    
    @Test
    void shouldListTasksWithPagination() throws Exception {
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Task task = new Task();
            task.setTitulo("Task " + i);
            task.setCategoria("Trabalho");
            task.setDataLimite(LocalDateTime.now().plusDays(3));
            task.setCriadaEm(LocalDateTime.now());
            task.setConcluida(false);
            tasks.add(task);
        }
        taskRepository.saveAll(tasks);
        
        mockMvc.perform(get("/api/tasks?page=0&size=5")).andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(5));
    }
    
    @Test
    void shouldSearchByCategory() throws Exception {
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Task task = new Task();
            task.setTitulo("Task " + i);
            task.setCategoria("Estudo");
            task.setDataLimite(LocalDateTime.now().plusDays(3));
            task.setCriadaEm(LocalDateTime.now());
            task.setConcluida(false);
            tasks.add(task);
        }

        for (int i = 0; i < 5; i++) {
            Task task = new Task();
            task.setTitulo("Task Work" + i);
            task.setCategoria("Trabalho");
            task.setDataLimite(LocalDateTime.now().plusDays(3));
            task.setCriadaEm(LocalDateTime.now());
            task.setConcluida(false);
            tasks.add(task);
        }

        taskRepository.saveAll(tasks);
        
        mockMvc.perform(get("/api/tasks/search").param("category", "Trabalho")).andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].categoria").value("Trabalho"));
    }
}
