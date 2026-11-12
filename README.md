# Controle Financeiro (Java puro)

Núcleo de regras de um controle financeiro pessoal, escrito em Java puro, sem framework.
Projeto de estudo de orientação a objetos. A versão com API REST está em `controle-financeiro-api`.

## O que faz

- `Usuario` com várias `Conta`s
- Receitas e despesas (`Transacao`) com categoria
- Transferência entre contas, sem alterar nada se o saldo for insuficiente
- Relatórios: total de receitas, total de despesas e gasto por categoria (com streams)
- Validações com exceções (`SaldoInsuficienteException`, valor inválido e nulos)

## Decisões de design

- Dinheiro com `BigDecimal`, nunca `double`
- Tipo da transação em enum (`TipoTransacao`)
- Listas expostas somente para leitura (`unmodifiableList`)
- O saldo só muda por métodos da própria `Conta`, sem setter público

## Como rodar

Requisito: JDK 21.

Abra o projeto no IntelliJ e execute a classe `Main`. Ela roda cenários de teste e
imprime `OK` ou `FALHOU` para cada um.

## Próximos passos

Migrar as regras para uma API com Spring Boot e JPA.
