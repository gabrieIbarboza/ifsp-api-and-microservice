package br.ifsp.tasks.dto.task;

import java.time.LocalDateTime;

import br.ifsp.tasks.model.Prioridade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private Prioridade prioridade;
    private LocalDateTime dataLimite;
    private boolean concluida;
    private String categoria;
    private LocalDateTime criadaEm;
}
