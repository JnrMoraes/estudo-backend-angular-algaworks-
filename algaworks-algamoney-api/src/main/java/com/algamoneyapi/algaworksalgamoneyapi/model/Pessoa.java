package com.algamoneyapi.algaworksalgamoneyapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotNull
    private String nome;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Pessoa(String nome, Endereco endereco, Boolean ativo) {
        this.nome = nome;
        this.endereco = endereco;
        this.ativo = ativo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa)) return false;

        Pessoa pessoa = (Pessoa) o;

        if (getNome() != null ? !getNome().equals(pessoa.getNome()) : pessoa.getNome() != null) return false;
        if (getEndereco() != null ? !getEndereco().equals(pessoa.getEndereco()) : pessoa.getEndereco() != null)
            return false;
        return getAtivo() != null ? getAtivo().equals(pessoa.getAtivo()) : pessoa.getAtivo() == null;
    }

    @Override
    public int hashCode() {
        int result = getNome() != null ? getNome().hashCode() : 0;
        result = 31 * result + (getEndereco() != null ? getEndereco().hashCode() : 0);
        result = 31 * result + (getAtivo() != null ? getAtivo().hashCode() : 0);
        return result;
    }
}
