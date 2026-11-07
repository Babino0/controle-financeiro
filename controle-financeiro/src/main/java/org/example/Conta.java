package org.example;

import java.util.ArrayList;
import java.util.List;

public class Conta {
    private String usuario;
    private double saldo;
    private List<Transacao> transacoes = new ArrayList<Transacao>();

    public Conta(String usuario, double saldo) {
        this.usuario = usuario;
        this.saldo = saldo;
    }

    public void realizarTransacao(double saldo, String descricao) {
        double saldoFinal = this.saldo + saldo;
        if (saldoFinal < 0) {
            System.out.println("Saldo insuficiente");
            saldoFinal = 0;

        } else {
            setSaldo(saldoFinal);
        }

        Transacao transacao = new Transacao(saldoFinal, descricao);
        transacoes.add(transacao);
    }
    public  void mostrarTransacoes() {
        transacoes.forEach(System.out::println);
    }

    public String getUsuario() {
        return usuario;
    }

    public double getSaldo() {
        return saldo;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
