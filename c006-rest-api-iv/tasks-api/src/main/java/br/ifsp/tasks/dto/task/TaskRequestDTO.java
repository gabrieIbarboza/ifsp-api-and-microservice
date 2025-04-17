package br.ifsp.tasks.dto.task;

import br.ifsp.tasks.enums.Prioridade;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDTO {

    @NotBlank(message = "O título não pode estar vazio")
    @Size(max = 100, message = "O título deve ter no máximo 100 caracteres")
    private String titulo;

    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    private String descricao;

    @NotNull(message = "A prioridade não pode estar vazia")
    private Prioridade prioridade;

    @NotNull(message = "A data limite não pode estar vazia")
    @FutureOrPresent(message = "A data limite deve ser no presente ou no futuro")
    private LocalDate dataLimite;

    private Boolean concluida;

    @NotBlank(message = "A categoria não pode estar vazia")
    @Size(max = 50, message = "A categoria deve ter no máximo 50 caracteres")
    private String categoria;
}
