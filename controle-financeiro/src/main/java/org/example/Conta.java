package org.example;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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
        Objects.requireNonNull(tipo, "Tipo não pode ser nulo");
        Objects.requireNonNull(valor, "Valor não pode ser nulo");
        Objects.requireNonNull(categoria, "Categoria não pode ser nula");
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor deve ser positivo");
        }

        switch (tipo) {
            case DESPESA -> retirar(valor);
            case RECEITA -> depositar(valor);
        }
        Transacao transacao = new Transacao(tipo, valor, descricao, categoria);
        this.transacoes.add(transacao);

    }

    private BigDecimal somarTotal(TipoTransacao tipoTransacao) {


        return this.transacoes.stream().
                filter(t -> t.getTipo() == tipoTransacao)
                .map(Transacao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


    }

    public BigDecimal totalReceitas() {

        return somarTotal(TipoTransacao.RECEITA);
    }

    public BigDecimal totalDespesas() {

        return somarTotal(TipoTransacao.DESPESA);
    }

    public Map<Categoria, BigDecimal> gastosPorCategoria() {
        return transacoes.stream()
                .filter(t -> t.getTipo() == TipoTransacao.DESPESA)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        Transacao::getCategoria,
                        Collectors.reducing(BigDecimal.ZERO, Transacao::getValor, BigDecimal::add)));
    }

    //A retirada do saldo é feita primeiro, para conferir se há saldo suficiente.
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
        return transacoes.stream()
                .filter(t -> t.getTipo() == tipo)
                .toList();
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
