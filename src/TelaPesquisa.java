import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import java.util.ArrayList;
import java.util.HashSet; // NOVO
import java.util.Set;      // NOVO

public class TelaPesquisa {

    public static VBox criar() {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));

        TextField campoBusca = new TextField();
        campoBusca.setPromptText("Digite nome, serviço, data ou status...");

        Button btnPesquisar = new Button("Pesquisar");

        TableView<Receita> tabela = new TableView<>();

        // NOVO: guarda quais linhas estão expandidas
        Set<Receita> linhasExpandidas = new HashSet<>();

        TableColumn<Receita, String> colCliente  = new TableColumn<>("Cliente");
        TableColumn<Receita, String> colServico  = new TableColumn<>("Serviço");
        TableColumn<Receita, String> colTelefone = new TableColumn<>("Telefone");
        TableColumn<Receita, String> colData     = new TableColumn<>("Data");
        TableColumn<Receita, Double> colValor    = new TableColumn<>("Valor");
        TableColumn<Receita, String> colStatus   = new TableColumn<>("Status");

        //Coluna mínima para visualização das informações
        colServico.setMinWidth(150);
        colStatus.setMinWidth(100);
        colTelefone.setMinWidth(120);

        colCliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        // NOVO: célula customizada da coluna Serviço — clicável, expande a linha
        colServico.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colServico.setCellFactory(col -> new TableCell<Receita, String>() {
            private final Label label = new Label();

            {
                label.prefWidthProperty().bind(colServico.widthProperty().subtract(10));
                setOnMouseClicked(e -> {
                    Receita r = getTableRow() != null ? getTableRow().getItem() : null;
                    if (r == null) return;
                    if (linhasExpandidas.contains(r)) {
                        linhasExpandidas.remove(r);
                    } else {
                        linhasExpandidas.add(r);
                    }
                    getTableView().refresh();
                });
                setStyle("-fx-cursor: hand;");
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    Receita r = getTableRow() != null ? getTableRow().getItem() : null;
                    boolean expandido = r != null && linhasExpandidas.contains(r);
                    label.setWrapText(expandido);
                    label.setText(item);
                    setGraphic(label);
                }
            }
        });

        // NOVO: faz a linha crescer de altura quando o item estiver expandido
        tabela.setRowFactory(tv -> new TableRow<Receita>() {
            @Override
            protected void updateItem(Receita item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setPrefHeight(USE_COMPUTED_SIZE);
                } else if (linhasExpandidas.contains(item)) {
                    setPrefHeight(90);
                } else {
                    setPrefHeight(USE_COMPUTED_SIZE);
                }
            }
        });

        tabela.getColumns().addAll(colCliente, colServico, colTelefone, colData, colValor, colStatus);

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
            linhasExpandidas.clear(); // NOVO: limpa expansões da pesquisa anterior
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

        layout.getChildren().addAll(
            new Label("Pesquisar:"),
            campoBusca,
            btnPesquisar,
            tabela
        );

        return layout;
    }
}