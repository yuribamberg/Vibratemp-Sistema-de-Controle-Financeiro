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