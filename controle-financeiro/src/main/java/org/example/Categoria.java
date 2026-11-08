package org.example;

public class Categoria {
    private String nome;

    public String getNome() {
        return nome;
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
