package org.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Transacao {
    private BigDecimal valor;
    private final LocalDateTime data = LocalDateTime.now();
    private String descricao;
    private TipoTransacao tipo;

    public static final DateTimeFormatter DATA_FORMATADA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public Transacao(TipoTransacao tipo, BigDecimal valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Transacao{" + " tipo=" + tipo +
                ", valor=" + valor +
                ", data=" + data.format(DATA_FORMATADA) +
                ", descricao='" + descricao + '\'' +

                '}';
    }
}
