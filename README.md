# Vibratemp - Sistema de Controle Financeiro

Sistema desktop desenvolvido em Java com interface gráfica JavaFX, criado para uso interno da **Vibratemp Vidros Espelhos Molduras** para controle financeiro da loja.

## Funcionalidades
- **Cadastro de Receitas:** Vinculadas a clientes (nome, serviço, telefone, data, valor e status de pagamento).
- **Cadastro de Despesas:** Registrar gastos operacionais da loja (descrição, valor e data).
- **Listagem Completa:** Visualização de todas as receitas e despesas registradas.
- **Relatório Financeiro:** Exibição de totais de receitas, despesas e saldo final.
- **Pesquisa:** Busca dinâmica por nome do cliente, serviço, data ou status.
- **Persistência de Dados:** Dados salvos permanentemente em banco de dados local (SQLite).

---

## 📂 Estrutura do Projeto

```text
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

🛠️ Requisitos para o PC da Loja
Java: JDK/JRE 21 ou superior instalado na máquina (Baixar Java).
JavaFX: Pasta do JavaFX SDK extraída obrigatoriamente na raiz do disco C: (C:\javafx).

🚀 Como Instalar e Rodar
1.Copiar o JavaFX: Certifique-se de que a pasta do JavaFX SDK está em C:\javafx.
2.Copiar o Projeto: Coloque a pasta inteira do projeto no computador da loja.
3.Executar: Dê dois cliques no arquivo iciar.bat (ou Vibratemp.bat) presente na pasta raiz do projeto.

💻 Compilação e Execução Manual (Terminal)
Caso precise compilar ou rodar manualmente via CMD/Terminal:
Compilar:
javac --module-path "C:\javafx\lib" --add-modules javafx.controls,javafx.fxml -cp "lib\sqlite-jdbc-3.53.2.0.jar" -d bin src\*.java

Executar:
java --module-path "C:\javafx\lib" --add-modules javafx.controls,javafx.fxml -cp "bin;lib\sqlite-jdbc-3.53.2.0.jar" MainApp

🗄️ Banco de Dados
O sistema utiliza SQLite. O arquivo do banco de dados é criado automaticamente na primeira execução dentro da pasta raiz do projeto. Não é necessário configurar nenhum servidor externo.

📊 Status do Projeto
[x] Interface gráfica funcional com JavaFX
[x] Integração com SQLite
[x] Execução facilitada via script .bat para a loja
[ ] Próxima etapa: Geração de executável instalável (.exe)

📍 Desenvolvedor e Contato
Desenvolvedor: Yuri Ivo Luiz Bamberg
Empresa: Vibratemp Vidros Espelhos Molduras
Endereço: R. Piauí, 1115 - Funcionários, Belo Horizonte / MG
Telefone: (31) 99586-3199
