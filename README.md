INTEGRANTES: 
- Yuri Ivo Luiz Bamberg
- Miguel José Ribeiro Gomes
- Davi Fernandes Alves
- Guilherme Victor Takaki Pyramo

# Sistema de Controle Financeiro Pessoal

Aplicação desenvolvida em Java com execução via terminal, com o objetivo de simular um sistema de controle financeiro pessoal. O sistema permite cadastrar receitas e despesas, listar transações, visualizar relatórios e contar com uma consultoria financeira inteligente integrada a dados de mercado em tempo real.

---

## Funcionalidades Implementadas

- Cadastro de receitas (descrição, valor e data)
- Cadastro de despesas (descrição, valor e data)
- Listagem de todas as transações cadastradas
- Relatório financeiro com total de receitas, total de despesas e saldo final
- Validação de entrada com tratamento de exceções
- *Consultoria Financeira Inteligente 🤖* — análise do saldo com dicas personalizadas e cotação do dólar em tempo real

---

## Estrutura do Projeto


PROJETO/
├── src/
│   ├── App.java          # Classe principal — menu e controle do sistema
│   ├── Transacao.java    # Classe abstrata base para Receita e Despesa
│   ├── Receita.java      # Classe que representa uma receita financeira
│   ├── Despesa.java      # Classe que representa uma despesa financeira
│   ├── Relatorio.java    # Interface que define o contrato de exibição
│   └── Api.java          # Integração com a AwesomeAPI (cotação de moedas)
├── bin/                  # Arquivos compilados (.class)
└── README.md


---

## Integração com API Externa — AwesomeAPI

O sistema utiliza a [AwesomeAPI](https://docs.awesomeapi.com.br/api-de-moedas) para buscar a cotação do dólar (USD) em tempo real, sem necessidade de cadastro ou chave de acesso.

*Endpoint utilizado:*

GET https://economia.awesomeapi.com.br/json/last/USD-BRL


*Como funciona na aplicação:*

A classe Api.java é responsável pela comunicação HTTP com a AwesomeAPI. O método enviarComando() realiza a requisição GET usando HttpURLConnection (nativo do Java, sem dependências externas) e retorna o JSON bruto da resposta. O método extrairTextoDoJson() faz o parse manual do campo "bid", que representa o valor atual de compra do dólar em reais.

Essa cotação é exibida na opção *5 — Consultoria Financeira Inteligente* e usada como contexto para as dicas geradas ao usuário.

---

## Consultoria Financeira Inteligente 🤖

A opção 5 do menu combina os dados financeiros do usuário com a cotação em tempo real do dólar para gerar uma análise personalizada da saúde financeira. O sistema avalia o saldo atual e exibe insights e dicas de acordo com quatro situações:

| Situação | Status | Dica gerada |
|---|---|---|
| Saldo negativo | 🚨 NEGATIVO | Alerta para corte de gastos e cuidado com compras em dólar |
| Saldo zerado | ⚠️ ZERADO | Recomenda evitar novas despesas e iniciar uma reserva |
| Saldo positivo baixo (< R$ 500) | 👍 MODERADO | Incentiva guardar pelo menos 10% do saldo |
| Saldo positivo alto (≥ R$ 500) | 💰 EXCELENTE | Sugere estudar investimentos em renda fixa |

---

## Requisitos

- Java 11 ou superior

Para verificar sua versão do Java, execute no terminal:

bash
java -version


---

## Como Compilar e Executar

### Compilar

No terminal, dentro da pasta raiz do projeto, execute:

bash
javac src/*.java -d bin


### Executar

bash
java -cp bin App


---

## Como Usar o Sistema

Ao executar, o menu principal será exibido:


===== CONTROLE FINANCEIRO PESSOAL =====
1 - Cadastrar Receita
2 - Cadastrar Despesa
3 - Listar Transações
4 - Relatórios
5 - Consultoria Inteligente 🤖
0 - Sair
Escolha uma opção:


Digite o número da opção desejada e pressione Enter. Para cadastrar uma transação, informe a descrição, o valor e a data quando solicitado. Para acessar a consultoria financeira, certifique-se de ter pelo menos uma transação cadastrada e uma conexão com a internet ativa.

---

## Conceitos Aplicados

- Orientação a Objetos: classes, herança, encapsulamento e interface
- Estruturas de decisão: switch/case e if/else
- Estruturas de repetição: do/while e for
- Tratamento de exceções: try/catch
- Integração com API REST externa via HttpURLConnection
- Parse manual de JSON

---

## Tecnologias Utilizadas

- Java
- AwesomeAPI (cotação de moedas)
- Git
- GitHub

---

## Status do Projeto

Sprint 2 concluída — entidades OOP implementadas, CRUD funcional e integração com API externa de cotação de moedas operacional.
