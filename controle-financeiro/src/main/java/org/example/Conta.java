package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Conta {
    private String usuario;
    private BigDecimal saldo;
    private List<Transacao> transacoes = new ArrayList<Transacao>();

    public Conta(String usuario, BigDecimal saldo) {
        this.usuario = usuario;
        this.saldo = saldo;
    }

    private void depositar(BigDecimal valor) {
        this.saldo = this.saldo.add(valor);

    }

    private void retirar(BigDecimal valor) {
        if (saldo.compareTo(valor) < 0) {
            throw new SaldoInsuficienteException();
        }
        saldo = saldo.subtract(valor);
    }

    public void realizarTransacao(TipoTransacao tipo, BigDecimal valor, String descricao) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {  // valor zero ou negativo
            throw new IllegalArgumentException("Valor deve ser positivo");
        }
        if (tipo == TipoTransacao.RECEITA) {
            depositar(valor);
        } else if (tipo == TipoTransacao.DESPESA) {
            retirar(valor);

        }

        Transacao transacao = new Transacao(tipo, valor, descricao);
        transacoes.add(transacao);

    }


    public void mostrarTransacoes() {
        transacoes.forEach(System.out::println);
    }

    public String getUsuario() {
        return usuario;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public List<Transacao> getTransacoes() {
        return Collections.unmodifiableList(transacoes);
    }

}
