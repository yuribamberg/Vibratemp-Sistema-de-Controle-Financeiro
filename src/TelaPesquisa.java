import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import java.util.ArrayList;

public class TelaPesquisa {

    public static VBox criar() {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));

        TextField campoBusca = new TextField();
        campoBusca.setPromptText("Digite nome, serviço, data ou status...");

        Button btnPesquisar = new Button("Pesquisar");

        TableView<Receita> tabela = new TableView<>();

        TableColumn<Receita, String> colCliente  = new TableColumn<>("Cliente");
        TableColumn<Receita, String> colServico  = new TableColumn<>("Serviço");
        TableColumn<Receita, String> colTelefone = new TableColumn<>("Telefone");
        TableColumn<Receita, String> colData     = new TableColumn<>("Data");
        TableColumn<Receita, Double> colValor    = new TableColumn<>("Valor");
        TableColumn<Receita, String> colStatus   = new TableColumn<>("Status");

        colCliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        colServico.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

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
                        r.setStatus(novoStatus);
                        getTableView().refresh();
                    });
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });

        // ── COLUNA EXCLUIR ──
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

        tabela.getColumns().addAll(colCliente, colServico, colTelefone, colData, colValor, colStatus, colMudarStatus, colExcluir);

        btnPesquisar.setOnAction(e -> {
            String busca = campoBusca.getText();

            if (busca.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Atenção");
                alert.setHeaderText(null);
                alert.setContentText("Digite algo para pesquisar!");
                alert.showAndWait();
                return;
            }

            tabela.getItems().clear();
            ArrayList<Receita> resultado = Database.pesquisarCliente(busca);

            if (resultado.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Pesquisa");
                alert.setHeaderText(null);
                alert.setContentText("Nenhum resultado encontrado!");
                alert.showAndWait();
            } else {
                tabela.getItems().addAll(resultado);
            }
        });

        Label dica = new Label("💡 Passe o mouse sobre o nome ou serviço para ver o texto completo.");
        dica.setStyle("-fx-font-size: 10px; -fx-text-fill: #666666;");

        layout.getChildren().addAll(
            new Label("Pesquisar:"),
            campoBusca,
            btnPesquisar,
            dica,
            tabela
        );

        return layout;
    }
}