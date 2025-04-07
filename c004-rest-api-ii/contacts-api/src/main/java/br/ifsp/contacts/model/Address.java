package br.ifsp.contacts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Classe que representa o modelo de dados para um Endereço.
 */
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotBlank(message = "A rua não pode estar vazia")
    private String rua;

    //@NotBlank(message = "A cidade não pode estar vazia")
    private String cidade;

    //@NotBlank(message = "O estado não pode estar vazio")
    //@Size(min = 2, max = 2, message = "O estado deve ter exatamente 2 caracteres (sigla)")
    //@Pattern(regexp = "[A-Z]{2}", message = "O estado deve ser representado por duas letras maiúsculas")
    private String estado;

    //@NotBlank(message = "O CEP não pode estar vazio")
    //@Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 99999-999")
    private String cep;

    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = false)
    @JsonBackReference
    private Contact contact;

    // Construtor vazio exigido pelo JPA
    public Address() {}

    // Construtor para facilitar a criação de objetos
    public Address(String rua, String cidade, String estado, String cep, Contact contact) {
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.contact = contact;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }

    public Contact getContact() {
        return contact;
    }
    public void setContact(Contact contact) {
        this.contact = contact;
    }
}