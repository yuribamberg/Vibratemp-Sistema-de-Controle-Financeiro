import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import java.util.ArrayList;

public class TelaListar {

    private static TableView<Receita> tabelaReceitas = new TableView<>();
    private static TableView<Despesa> tabelaDespesas = new TableView<>();

    public static void atualizar() {
        tabelaReceitas.getItems().clear();
        tabelaDespesas.getItems().clear();
        tabelaReceitas.getItems().addAll(Database.listarReceitas());
        tabelaDespesas.getItems().addAll(Database.listarDespesas());
    }

    public static VBox criar() {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));

        // ── COLUNAS DE RECEITA ──
        TableColumn<Receita, String> colCliente  = new TableColumn<>("Cliente");
        TableColumn<Receita, String> colServico  = new TableColumn<>("Serviço");
        TableColumn<Receita, String> colTelefone = new TableColumn<>("Telefone");
        TableColumn<Receita, String> colData     = new TableColumn<>("Data");
        TableColumn<Receita, Double> colValor    = new TableColumn<>("Valor");
        TableColumn<Receita, String> colStatus   = new TableColumn<>("Status");
        TableColumn<Receita, String> colTipo     = new TableColumn<>("Tipo");

        colCliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        colServico.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoVidro"));

        // Tooltip para expandir texto longo
        colCliente.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) { setText(null); setTooltip(null); }
                else { setText(item); setTooltip(new Tooltip(item)); }
            }
        });

        colServico.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) { setText(null); setTooltip(null); }
                else { setText(item); setTooltip(new Tooltip(item)); }
            }
        });

        colCliente.setMinWidth(120);
        colServico.setMinWidth(130);
        colTelefone.setMinWidth(110);
        colStatus.setMinWidth(90);
        colTipo.setMinWidth(90);

        // ── COLUNA MUDAR STATUS ──
        TableColumn<Receita, Void> colMudarStatus = new TableColumn<>("Mudar Status");
        colMudarStatus.setCellFactory(col -> new TableCell<>() {
            private final Button btn = new Button("Alterar");
            {
                btn.setStyle("-fx-background-color: #2980b9; -fx-text-fill: white;");
                btn.setOnAction(e -> {
                    Receita r = getTableView().getItems().get(getIndex());
                    ChoiceDialog<String> dialog = new ChoiceDialog<>(r.getStatus(),
                        "Pago", "Não Pago", "Pago 50%");
                    dialog.setTitle("Alterar Status");
                    dialog.setHeaderText("Cliente: " + r.getNomeCliente());
                    dialog.setContentText("Selecione o novo status:");
                    dialog.showAndWait().ifPresent(novoStatus -> {
                        Database.atualizarStatusReceita(r.getId(), novoStatus);
                        atualizar();
                    });
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });

        // ── COLUNA EXCLUIR RECEITA ──
        TableColumn<Receita, Void> colExcluir = new TableColumn<>("Ação");
        colExcluir.setCellFactory(col -> new TableCell<>() {
            private final Button btn = new Button("Excluir");
            {
                btn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
                btn.setOnAction(e -> {
                    Receita r = getTableView().getItems().get(getIndex());
                    Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
                    confirm.setTitle("Confirmar");
                    confirm.setHeaderText(null);
                    confirm.setContentText("Deseja excluir o registro de " + r.getNomeCliente() + "?");
                    confirm.showAndWait().ifPresent(response -> {
                        if (response == javafx.scene.control.ButtonType.OK) {
                            Database.excluirReceita(r.getId());
                            getTableView().getItems().remove(r);
                        }
                    });
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });

        tabelaReceitas.getColumns().addAll(
            colCliente, colServico, colTelefone, colData, colValor, colStatus, colTipo, colMudarStatus, colExcluir
        );

        // ── COLUNAS DE DESPESA ──
        TableColumn<Despesa, String> colDescricao = new TableColumn<>("Descrição");
        TableColumn<Despesa, Double> colValorD    = new TableColumn<>("Valor");
        TableColumn<Despesa, String> colDataD     = new TableColumn<>("Data");

        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colValorD.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colDataD.setCellValueFactory(new PropertyValueFactory<>("data"));

        colDescricao.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) { setText(null); setTooltip(null); }
                else { setText(item); setTooltip(new Tooltip(item)); }
            }
        });

        // ── COLUNA EXCLUIR DESPESA ──
        TableColumn<Despesa, Void> colExcluirD = new TableColumn<>("Ação");
        colExcluirD.setCellFactory(col -> new TableCell<>() {
            private final Button btn = new Button("Excluir");
            {
                btn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
                btn.setOnAction(e -> {
                    Despesa d = getTableView().getItems().get(getIndex());
                    Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
                    confirm.setTitle("Confirmar");
                    confirm.setHeaderText(null);
                    confirm.setContentText("Deseja excluir a despesa: " + d.getDescricao() + "?");
                    confirm.showAndWait().ifPresent(response -> {
                        if (response == javafx.scene.control.ButtonType.OK) {
                            Database.excluirDespesa(d.getId());
                            getTableView().getItems().remove(d);
                        }
                    });
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });

        tabelaDespesas.getColumns().addAll(colDescricao, colValorD, colDataD, colExcluirD);

        atualizar();

        Label lblReceitas = new Label("📋 Receitas");
        lblReceitas.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2e9e4f;");

        Label lblDespesas = new Label("📋 Despesas");
        lblDespesas.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2e9e4f;");

        Label dica = new Label("💡 Passe o mouse sobre o nome ou serviço para ver o texto completo.");
        dica.setStyle("-fx-font-size: 10px; -fx-text-fill: #666666;");

        layout.getChildren().addAll(
            dica,
            lblReceitas, tabelaReceitas,
            lblDespesas, tabelaDespesas
        );

        return layout;
    }
}