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

        tabelaReceitas.getColumns().addAll(colCliente, colServico, colTelefone, colData, colValor, colStatus);

        TableColumn<Despesa, String> colDescricao = new TableColumn<>("Descrição");
        TableColumn<Despesa, Double> colValorD    = new TableColumn<>("Valor");
        TableColumn<Despesa, String> colDataD     = new TableColumn<>("Data");

        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colValorD.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colDataD.setCellValueFactory(new PropertyValueFactory<>("data"));

        tabelaDespesas.getColumns().addAll(colDescricao, colValorD, colDataD);

        atualizar();

        layout.getChildren().addAll(
            new Label("Receitas"), tabelaReceitas,
            new Label("Despesas"), tabelaDespesas
        );

        return layout;
    }
}