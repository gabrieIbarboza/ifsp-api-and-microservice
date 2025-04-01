package br.uff.ic.agenda.model;

import java.io.Serializable;

public class Contato implements Serializable {
    private String nome;
    private String telefone;
    private String detalhes;
    private String celular;
    private String endComercial;
    private String endResidencial;

    public Contato() {
        nome = "Novo Contato";
        telefone = "";
        detalhes = "";
        celular = "";
        endComercial = "";
        endResidencial = "";
    }

	public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }
    
    public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEndComercial() {
		return endComercial;
	}

	public void setEndComercial(String endComercial) {
		this.endComercial = endComercial;
	}

	public String getEndResidencial() {
		return endResidencial;
	}

	public void setEndResidencial(String endResidencial) {
		this.endResidencial = endResidencial;
	}

    @Override
    public String toString() {
        return nome;
    }    
}