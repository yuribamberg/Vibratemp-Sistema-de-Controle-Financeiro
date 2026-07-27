import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        Database.criarTabelas();

        TabPane tabPane = new TabPane();

        System.out.println("VERSAO NOVA");
        
        Tab tabReceita  = new Tab("Cadastrar Receita");
        Tab tabDespesa  = new Tab("Cadastrar Despesa");
        Tab tabListar   = new Tab("Listar");
        Tab tabRelatorio = new Tab("Relatório");
        Tab tabPesquisa = new Tab("Pesquisa");

        tabReceita.setClosable(false);
        tabDespesa.setClosable(false);
        tabListar.setClosable(false);
        tabRelatorio.setClosable(false);
        tabPesquisa.setClosable(false);

        tabReceita.setContent(TelaReceita.criar());
        tabDespesa.setContent(TelaDespesa.criar());
        tabListar.setContent(TelaListar.criar());
        tabRelatorio.setContent(TelaRelatorio.criar());
        tabPesquisa.setContent(TelaPesquisa.criar());

        tabPane.getTabs().addAll(tabReceita, tabDespesa, tabListar, tabRelatorio, tabPesquisa);

        Scene scene = new Scene(tabPane, 900, 600);
        stage.setTitle("Vidraçaria - Controle Financeiro");
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("resources/estilo.css").toExternalForm());
        stage.show();

        tabListar.setOnSelectionChanged(e -> {
            if (tabListar.isSelected()) {
                TelaListar.atualizar();
            }
        });
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}