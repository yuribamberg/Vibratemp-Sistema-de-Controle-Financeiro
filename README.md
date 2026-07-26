# Vibratemp — Sistema de Controle Financeiro

Sistema desktop desenvolvido em Java com interface gráfica JavaFX, criado para uso interno da **Vibratemp Vidros Espelhos Molduras** para controle financeiro da loja.

---

## Funcionalidades

- Cadastro de receitas vinculadas a clientes (nome, serviço, telefone, data, valor e status de pagamento)
- Cadastro de despesas da loja (descrição, valor e data)
- Listagem completa de receitas e despesas
- Relatório financeiro com total de receitas, despesas e saldo
- Pesquisa por nome do cliente, serviço, data ou status
- Dados salvos permanentemente em banco de dados local (SQLite)

---

## Estrutura do Projeto

```
src/
├── MainApp.java        # Classe principal — inicia a interface gráfica
├── App.java            # Menu via terminal (versão anterior)
├── Transacao.java      # Classe abstrata base
├── Receita.java        # Herda Transacao — representa entrada de cliente
├── Despesa.java        # Herda Transacao — representa gasto da loja
├── Relatorio.java      # Interface com contrato ExibirResumo()
├── Database.java       # Conexão e operações com SQLite
├── TelaReceita.java    # Tela de cadastro de receita
├── TelaDespesa.java    # Tela de cadastro de despesa
├── TelaListar.java     # Tela de listagem de transações
├── TelaRelatorio.java  # Tela de relatório financeiro
├── TelaPesquisa.java   # Tela de pesquisa
└── resources/
    └── estilo.css      # Estilo visual do sistema
lib/
└── sqlite-jdbc.jar     # Driver do banco de dados
```

---

## Requisitos

- Java 25 ou superior
- JavaFX SDK 26.0.2

---

## Como Compilar e Executar

### Compilar

```powershell
javac --module-path "CAMINHO\javafx-sdk-26.0.2\lib" --add-modules javafx.controls,javafx.fxml -cp "lib\sqlite-jdbc-3.53.2.0.jar" -d bin src\*.java
```

### Executar

```powershell
java --module-path "CAMINHO\javafx-sdk-26.0.2\lib" --add-modules javafx.controls,javafx.fxml -cp "bin;lib\sqlite-jdbc-3.53.2.0.jar" MainApp
```

> Substitua `CAMINHO` pelo caminho completo onde o JavaFX SDK está instalado.

---

## Banco de Dados

O sistema utiliza **SQLite** — o arquivo `vidracaria.db` é criado automaticamente na primeira execução na pasta raiz do projeto. Não é necessário instalar nenhum servidor de banco de dados.

---

## Desenvolvedor

**Yuri Ivo Luiz Bamberg**

Desenvolvido para uso interno da **Vibratemp Vidros Espelhos Molduras**

R. Piauí, 1115 - Funcionários, Belo Horizonte

📞 (31) 99586-3199

---

## Status do Projeto

Sistema funcional com interface gráfica. Próxima etapa: geração de executável `.exe` para instalação na loja.
