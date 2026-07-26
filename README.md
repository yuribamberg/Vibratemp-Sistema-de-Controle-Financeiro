<div align="center">

# 💎 Vibratemp — Sistema de Controle Financeiro

**Gestão financeira simples, rápida e local para o dia a dia da loja.**

![Java](https://img.shields.io/badge/Java-21%2B-orange?style=for-the-badge&logo=openjdk&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-UI-blue?style=for-the-badge&logo=java&logoColor=white)
![SQLite](https://img.shields.io/badge/SQLite-Banco%20de%20Dados-003B57?style=for-the-badge&logo=sqlite&logoColor=white)
![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow?style=for-the-badge)

</div>

---

## 📖 Sobre o Projeto

O **Vibratemp - Sistema de Controle Financeiro** é uma aplicação desktop desenvolvida em **Java** com interface gráfica em **JavaFX**, criada sob medida para uso interno da **Vibratemp Vidros Espelhos Molduras**.

O sistema centraliza o controle financeiro da loja, permitindo registrar receitas vinculadas a clientes, despesas operacionais, gerar relatórios e pesquisar transações de forma dinâmica — tudo com persistência local em **SQLite**, sem depender de servidores externos ou conexão com a internet.

---

## ✨ Funcionalidades

| Módulo | Descrição |
|---|---|
| 💰 **Cadastro de Receitas** | Registro vinculado a clientes: nome, serviço, telefone, data, valor e status de pagamento |
| 📉 **Cadastro de Despesas** | Registro de gastos operacionais: descrição, valor e data |
| 📋 **Listagem Completa** | Visualização unificada de todas as receitas e despesas cadastradas |
| 📊 **Relatório Financeiro** | Totais de receitas, despesas e saldo final consolidado |
| 🔎 **Pesquisa Dinâmica** | Busca por nome do cliente, serviço, data ou status de pagamento |
| 💾 **Persistência de Dados** | Armazenamento permanente em banco de dados local SQLite |

---

## 🧱 Arquitetura & Estrutura do Projeto

```
Vibratemp-Sistema-de-Controle-Financeiro/
├── src/
│   ├── MainApp.java        # Classe principal — inicia a interface gráfica
│   ├── App.java            # Menu via terminal (versão anterior)
│   ├── Transacao.java      # Classe abstrata base
│   ├── Receita.java        # Herda Transacao — representa entrada de cliente
│   ├── Despesa.java        # Herda Transacao — representa gasto da loja
│   ├── Relatorio.java      # Interface com contrato ExibirResumo()
│   ├── Database.java       # Conexão e operações com SQLite
│   ├── TelaReceita.java    # Tela de cadastro de receita
│   ├── TelaDespesa.java    # Tela de cadastro de despesa
│   ├── TelaListar.java     # Tela de listagem de transações
│   ├── TelaRelatorio.java  # Tela de relatório financeiro
│   ├── TelaPesquisa.java   # Tela de pesquisa
│   └── resources/
│       └── estilo.css      # Estilo visual do sistema
├── lib/
│   └── sqlite-jdbc.jar     # Driver do banco de dados
└── Iniciar.bat             # Script de execução rápida para o PC da loja
```

O projeto segue uma modelagem orientada a objetos enxuta: `Transacao` como classe abstrata base, especializada em `Receita` e `Despesa`, com `Relatorio` definindo o contrato de exibição de resumo — favorecendo extensibilidade sem acoplamento com a camada de interface.

---

## 🛠️ Requisitos para o PC da Loja

- **Java**: JDK/JRE 21 ou superior instalado na máquina ([baixar aqui](https://www.oracle.com/java/technologies/downloads/))
- **JavaFX**: pasta do JavaFX SDK extraída obrigatoriamente na raiz do disco `C:` (`C:\javafx`)

---

## 🚀 Como Instalar e Rodar

1. **Copiar o JavaFX** — garanta que a pasta do JavaFX SDK está em `C:\javafx`
2. **Copiar o Projeto** — coloque a pasta inteira do projeto no computador da loja
3. **Executar** — dê dois cliques em `Rodar.bat` (ou `Vibratemp.bat`) na pasta raiz do projeto

---

## 💻 Compilação e Execução Manual (Terminal)

Caso precise compilar ou rodar manualmente via CMD/Terminal:

**Compilar:**
```bash
javac --module-path "C:\javafx\lib" --add-modules javafx.controls,javafx.fxml -cp "lib\sqlite-jdbc-3.53.2.0.jar" -d bin src\*.java
```

**Executar:**
```bash
java --module-path "C:\javafx\lib" --add-modules javafx.controls,javafx.fxml -cp "bin;lib\sqlite-jdbc-3.53.2.0.jar" MainApp
```

---

## 🗄️ Banco de Dados

O sistema utiliza **SQLite**. O arquivo do banco é criado automaticamente na primeira execução, dentro da pasta raiz do projeto. Não é necessário configurar nenhum servidor externo.

---

## 📊 Status do Projeto

- [x] Interface gráfica funcional com JavaFX
- [x] Integração com SQLite
- [x] Execução facilitada via script `.bat` para a loja
- [ ] Próxima etapa: geração de executável instalável (`.exe`)

---

## 📍 Desenvolvedor e Contato

**Desenvolvedor:** Yuri Ivo Luiz Bamberg
**Empresa:** Vibratemp Vidros Espelhos Molduras
**Endereço:** R. Piauí, 1115 — Funcionários, Belo Horizonte / MG
**Telefone:** (31) 99586-3199

<div align="center">

*Desenvolvido com foco em simplicidade, controle e autonomia para a gestão financeira da loja.*

</div>
