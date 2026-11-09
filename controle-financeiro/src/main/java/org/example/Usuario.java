package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Usuario {
    private String nome;
    private String email;
    private List<Conta> contas = new ArrayList<Conta>();

    public void adicionarConta(Conta conta) {
        Objects.requireNonNull(conta, "Conta não pode ser nula");

        contas.add(conta);

    }
    public BigDecimal saldoTotal(){
        BigDecimal soma = BigDecimal.ZERO;
        for (Conta conta : contas) {
            soma = soma.add(conta.getSaldo());
        }

        return soma;
    }




    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }
    public String getNome() {
        return nome;
    }
    public String getEmail(){
        return email;
    }
    public List<Conta> getContas() {
        return Collections.unmodifiableList(contas);
    }


}
