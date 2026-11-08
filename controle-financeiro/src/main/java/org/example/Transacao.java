package org.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Transacao {
    private BigDecimal valor;
    private final LocalDateTime data = LocalDateTime.now();
    private String descricao;
    private TipoTransacao tipo;
    private Categoria categoria;

    public static final DateTimeFormatter DATA_FORMATADA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public Transacao(TipoTransacao tipo, BigDecimal valor, String descricao, Categoria categoria) {
        this.valor = valor;
        this.descricao = descricao;
        this.tipo = tipo;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Transacao{" + " tipo=" + tipo +
                ", valor=" + valor +
                ", data=" + data.format(DATA_FORMATADA) +
                ", descricao='" + descricao + '\'' +
                ", categoria=" + categoria +
                '}';
    }

    public BigDecimal getValor() {
        return valor;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public Categoria getCategoria() {
        return categoria;
    }
}
