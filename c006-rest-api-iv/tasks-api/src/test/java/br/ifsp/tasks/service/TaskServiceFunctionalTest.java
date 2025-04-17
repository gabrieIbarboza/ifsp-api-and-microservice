package br.ifsp.tasks.service;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TaskServiceFunctionalTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TaskService taskService; // Mock the service layer

    @Test
    void testCreateTaskSuccess() throws Exception {
        String titulo = "Tarefa de Teste";
        String descricao = "Descrição da tarefa";
        String prioridade = "ALTA";
        String concluida = "false";
        String categoria = "Trabalho";
        // Definindo a data limite como 5 dias a partir de hoje
        LocalDate today = LocalDate.now().plusDays(5);
        String todayString = today.toString();
        String json = """
                    {
                        "titulo": "%s",
                        "descricao": "%s",
                        "prioridade": "%s",
                        "dataLimite": "%s",
                        "concluida": "%s",
                        "categoria": "%s"
                    }
                """.formatted(titulo, descricao, prioridade, todayString, concluida, categoria);

        mockMvc.perform(post("/api/tasks")
                .contentType("application/json")
                .content(json))
                .andDo(print()) // <-- aqui exibe a resposta no console
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo").value(titulo))
                .andExpect(jsonPath("$.descricao").value(descricao))
                .andExpect(jsonPath("$.prioridade").value(prioridade))
                .andExpect(jsonPath("$.dataLimite").value(todayString))
                .andExpect(jsonPath("$.concluida").value(concluida))
                .andExpect(jsonPath("$.categoria").value(categoria));
    }

    @Test
    void testCreateTaskInvalid() throws Exception {
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
                .andExpect(status().isBadRequest())
                .andExpect((jsonPath("$.titulo").exists()))
                .andExpect((jsonPath("$.descricao").exists()))
                .andExpect((jsonPath("$.prioridade").exists()))
                .andExpect((jsonPath("$.dataLimite").exists()))
                .andExpect((jsonPath("$.categoria").exists()));
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
    void testCreateTaskInvalidConcluida() throws Exception {
        String concluida = "INVALIDA";
        String json = """
                    {
                        "concluida": "%s"
                    }
                """.formatted(concluida);

        mockMvc.perform(post("/api/tasks")
                .contentType("application/json")
                .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
