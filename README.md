# Vibratemp — Sistema de Controle Financeiro

Sistema desktop desenvolvido em Java com interface gráfica JavaFX, criado para uso interno da Vibratemp Vidros Espelhos Molduras para controle financeiro da loja.

## Funcionalidades

- **Cadastro de Receitas**: Vinculadas a clientes (nome, serviço, telefone, data, valor e status de pagamento)
- **Cadastro de Despesas**: Registrar gastos da loja (descrição, valor e data)
- **Listagem Completa**: Visualização de receitas e despesas registradas
- **Relatório Financeiro**: Exibição de totais de receitas, despesas e saldo final
- **Pesquisa**: Busca por nome do cliente, serviço, data ou status
- **Persistência de Dados**: Dados salvos permanentemente em banco de dados local (SQLite)

---

## Estrutura do Projeto

```text
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

---

## Requisitos para o PC da Loja

- **Java (JDK/JRE 21 ou superior)** instalado na máquina.
- Pasta do **JavaFX SDK 26.0.2** extraída na raiz do disco C: (`C:\javafx`).

---

## Como Instalar e Rodar no PC da Loja

1. **Instale o Java**: Caso ainda não tenha, baixe e instale o Java (https://java.com/download).
2. **Copie a pasta JavaFX**: Coloque a pasta do JavaFX SDK no caminho `C:\javafx`.
3. **Copie o Projeto**: Copie a pasta inteira do projeto para o PC da loja.
4. **Executar**: Basta dar dois cliques no arquivo `.bat` (ex: `Iniciar.bat` / `Vibratemp.bat`) presente na pasta do projeto.

---

## Compilação e Execução Manual (Terminal)

Caso precise compilar ou rodar manualmente via CMD/Terminal:

**Compilar:**
```cmd
javac --module-path "C:\javafx\lib" --add-modules javafx.controls,javafx.fxml -cp "lib\sqlite-jdbc-3.53.2.0.jar" -d bin src\*.java
```

**Executar:**
```cmd
java --module-path "C:\javafx\lib" --add-modules javafx.controls,javafx.fxml -cp "bin;lib\sqlite-jdbc-3.53.2.0.jar" MainApp
```

---

## Banco de Dados

O sistema utiliza **SQLite**. O arquivo `vidracaria.db` é criado automaticamente na primeira execução dentro da pasta raiz do projeto. Não é necessário instalar ou configurar nenhum servidor de banco de dados.

---

## Desenvolvedor e Contato

**Yuri Ivo Luiz Bamberg** Desenvolvido para uso interno da **Vibratemp Vidros Espelhos Molduras** 📍 R. Piauí, 1115 - Funcionários, Belo Horizonte / MG  
📞 (31) 99586-3199  

---

## Status do Projeto

- [x] Interface gráfica funcional com JavaFX
- [x] Integração com SQLite
- [x] Execução facilitada via script `.bat` para a loja
- [ ] Próxima etapa: Geração de executável instalável (`.exe`)