package org.example;

public class Categoria {
    String nome;

    public void getNome() {
        this.nome = nome;
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
