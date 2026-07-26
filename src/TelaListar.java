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
            colCliente, colServico, colTelefone, colData, colValor, colStatus, colExcluir
        );

        TableColumn<Despesa, String> colDescricao = new TableColumn<>("Descrição");
        TableColumn<Despesa, Double> colValorD    = new TableColumn<>("Valor");
        TableColumn<Despesa, String> colDataD     = new TableColumn<>("Data");

        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colValorD.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colDataD.setCellValueFactory(new PropertyValueFactory<>("data"));

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

        layout.getChildren().addAll(
            lblReceitas, tabelaReceitas,
            lblDespesas, tabelaDespesas
        );

        return layout;
    }
}