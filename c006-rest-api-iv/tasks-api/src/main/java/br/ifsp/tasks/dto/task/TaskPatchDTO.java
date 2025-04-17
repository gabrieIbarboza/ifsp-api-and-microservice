package br.ifsp.tasks.dto.task;

import java.time.LocalDate;
import java.util.Optional;

import br.ifsp.tasks.enums.Prioridade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskPatchDTO {
    private Optional<String> titulo = Optional.empty();
    private Optional<String> descricao = Optional.empty();
    private Optional<Prioridade> prioridade = Optional.empty();
    private Optional<LocalDate> dataLimite = Optional.empty();
    private Optional<Boolean> concluida = Optional.empty();
    private Optional<String> categoria = Optional.empty();
}
