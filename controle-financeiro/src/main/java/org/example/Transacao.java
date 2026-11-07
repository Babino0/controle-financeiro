package org.example;

import java.time.LocalDateTime;


public class Transacao {
    private double valor;
    private final LocalDateTime data = LocalDateTime.now();
    private String descricao;

    public Transacao( double valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "valor=" + valor +
                ", data=" + data +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
