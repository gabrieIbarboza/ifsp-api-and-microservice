package br.ifsp.tasks.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;
    private Prioridade prioridade;
    private LocalDateTime dataLimite;
    private Boolean concluida;
    private String categoria;
    private LocalDateTime criadaEm;

    public Task(String titulo, String descricao, Prioridade prioridade, LocalDateTime dataLimite, Boolean concluida, String categoria) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.dataLimite = dataLimite;
        this.concluida = concluida;
        this.categoria = categoria;
        
        this.criadaEm = LocalDateTime.now();
    }

    public Task(String titulo, String descricao, Prioridade prioridade, LocalDateTime dataLimite, String categoria) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.dataLimite = dataLimite;
        this.categoria = categoria;

        this.concluida = false;
        this.criadaEm = LocalDateTime.now();
    }
}
