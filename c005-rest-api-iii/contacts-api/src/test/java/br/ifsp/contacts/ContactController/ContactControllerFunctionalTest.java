package br.ifsp.contacts.ContactController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ContactControllerFunctionalTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateContactSuccessfully() throws Exception {
        String json = """
                    {
                        "nome": "Maria Oliveira",
                        "email": "maria@example.com",
                        "telefone": "11988887777",
                        "addresses": [
                            {
                                "rua": "Rua das Rosas",
                                "cidade": "Campinas",
                                "estado": "SP",
                                "cep": "13000-000"
                            }
                        ]
                    }
                """;

        mockMvc.perform(post("/api/contacts")
                .contentType("application/json")
                .content(json))
                .andDo(print()) // <-- aqui exibe a resposta no console
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Maria Oliveira"))
                .andExpect(jsonPath("$.email").value("maria@example.com"));
    }

    @Test
    void shouldReturnNotFoundWhenContactDoesNotExist() throws Exception {
        mockMvc.perform(get("/api/contacts/9999"))
                .andExpect(status().isNotFound());
    }
}