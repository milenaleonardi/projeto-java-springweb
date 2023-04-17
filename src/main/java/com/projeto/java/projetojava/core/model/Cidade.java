package com.projeto.java.projetojava.core.model;

import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Objects;

@Entity
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String codigo;
    private String nome;
    private String uf;

    public Cidade() {
    }

    public Cidade(String nome, String uf) {
        this.nome = nome;
        this.uf = uf;
    }

    public Cidade(String codigo, String nome, String uf) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.uf = uf;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cidade cidade = (Cidade) o;
        return id == cidade.id && Objects.equals(codigo, cidade.codigo) && Objects.equals(nome, cidade.nome) && Objects.equals(uf, cidade.uf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo, nome, uf);
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", uf='" + uf + '\'' +
                '}';
    }
}
