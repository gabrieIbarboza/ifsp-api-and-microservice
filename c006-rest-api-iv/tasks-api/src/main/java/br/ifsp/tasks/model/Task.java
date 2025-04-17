package br.ifsp.tasks.model;

import br.ifsp.tasks.enums.Prioridade;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;
    private Prioridade prioridade;
    private LocalDate dataLimite;
    private Boolean concluida;
    private String categoria;
    private LocalDate criadaEm;

    public Task() { 
        this.criadaEm = LocalDate.now();
    }

    public Task(String titulo, String descricao, Prioridade prioridade, LocalDate dataLimite, Boolean concluida, String categoria) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.dataLimite = dataLimite;
        this.concluida = ((concluida == null) ? false : concluida);
        this.categoria = categoria;
        this.criadaEm = LocalDate.now();
    }

    public Long getId() { return id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Prioridade getPrioridade() { return prioridade; }
    public void setPrioridade(Prioridade prioridade) { this.prioridade = prioridade; }

    public LocalDate getDataLimite() { return dataLimite; }
    public void setDataLimite(LocalDate dataLimite) { this.dataLimite = dataLimite; }

    public boolean isConcluida() { return concluida; }
    public void setConcluida(boolean concluida) { this.concluida = concluida; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    
    public LocalDate getCriadaEm() { return criadaEm; }
    //public void setCriadaEm(LocalDate criadaEm) { this.criadaEm = criadaEm; }
}
