package org.example;

import java.math.BigDecimal;
import java.util.*;

public class Conta {
    private static final Categoria TRANSFERENCIA = new Categoria("Transferência");
    private String nome;
    private BigDecimal saldo;
    private List<Transacao> transacoes = new ArrayList<Transacao>();

    public Conta(String nome, BigDecimal saldo) {
        this.nome = nome;
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

    public void realizarTransacao(TipoTransacao tipo, BigDecimal valor, String descricao, Categoria categoria) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException();
        }
        switch (tipo) {
            case DESPESA -> retirar(valor);
            case RECEITA -> depositar(valor);
        }
        Transacao transacao = new Transacao(tipo, valor, descricao, categoria);
        this.transacoes.add(transacao);

    }

    private BigDecimal somar(TipoTransacao tipoTransacao) {
        BigDecimal soma = BigDecimal.ZERO;

        for (int i = 0; i < this.transacoes.size(); i++) {
            Transacao transacao = this.transacoes.get(i);
            if (transacao.getTipo() == tipoTransacao) {
                soma = soma.add(this.transacoes.get(i).getValor());
            }
        }
        return soma;
    }

    public BigDecimal totalReceitas() {

        return somar(TipoTransacao.RECEITA);
    }

    public BigDecimal totalDespesas() {
        return somar(TipoTransacao.DESPESA);
    }

    public Map<Categoria, BigDecimal> gastosPorCategoria() {

        Map<Categoria, BigDecimal> mapa = new HashMap<>();
        for (Transacao t : transacoes) {
            if (t.getTipo() == TipoTransacao.DESPESA) {
                mapa.merge(t.getCategoria(), t.getValor(), BigDecimal::add);
            }
        }
        return mapa;
    }

    //A retirado do saldo é realizada primeiro para conferir se há saldo o suficiente
    public void transferir(Conta destino, BigDecimal valor) {
        Objects.requireNonNull(destino, "Conta destino não pode ser nula");
        if (destino == this) {
            throw new IllegalArgumentException("Não dá para transferir para a mesma conta");
        }
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor deve ser positivo");
        }
        this.realizarTransacao(TipoTransacao.DESPESA, valor, "Transferencia para " + destino.getNome(), TRANSFERENCIA);
        destino.realizarTransacao(TipoTransacao.RECEITA, valor, "Transferencia de " + this.getNome(), TRANSFERENCIA);


    }

    public List<Transacao> transacoesDoTipo(TipoTransacao tipo) {
        List<Transacao> resultado = new ArrayList<>();
        for (Transacao t : transacoes) {
            if (t.getTipo() == tipo) {
                resultado.add(t);
            }
        }
        return Collections.unmodifiableList(resultado);
    }

    public void mostrarTransacoes() {
        transacoes.forEach(System.out::println);
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public List<Transacao> getTransacoes() {
        return Collections.unmodifiableList(transacoes);
    }


}
