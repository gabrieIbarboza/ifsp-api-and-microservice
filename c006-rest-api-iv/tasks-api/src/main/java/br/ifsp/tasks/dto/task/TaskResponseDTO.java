package br.ifsp.tasks.dto.task;

import java.time.LocalDate;

import br.ifsp.tasks.enums.Prioridade;
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
    private LocalDate dataLimite;
    private boolean concluida;
    private String categoria;
    private LocalDate criadaEm;
}
