import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;

public class TelaDespesa {
    public static VBox criar() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        TextField campoDescricao = new TextField();
        TextField campoData = new TextField();
        TextField campoValor = new TextField();

        Button btnSalvar = new Button("Salvar despesa");

        btnSalvar.setOnAction(e -> {
            String descricao = campoDescricao.getText();
            String data = campoData.getText();
            
            if (descricao.isEmpty() || data.isEmpty() || campoValor.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Atenção");
                alert.setHeaderText(null);
                alert.setContentText("Preencha todos os campos obrigatórios!");
                alert.showAndWait();
                return;
            }
            try {
                double valor = Double.parseDouble(campoValor.getText());
                Despesa despesa = new Despesa(descricao, valor, data);
                Database.salvarDespesa(despesa);

                Alert sucesso = new Alert(Alert.AlertType.INFORMATION);
                    sucesso.setTitle("Sucesso");
                    sucesso.setHeaderText(null);
                    sucesso.setContentText("Cadastrado com sucesso!");
                    sucesso.showAndWait();
                    
                // Limpa os campos após salvar
                campoDescricao.clear();
                campoData.clear();
                campoValor.clear();

                System.out.println("Despesa salva!");
            } catch (NumberFormatException ex) {
                System.out.println("Valor inválido!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Digite um valor válido! Use ponto para centavos. Ex: 150.50");
                alert.showAndWait();
            }
        });
            
        layout.getChildren().addAll(
            new Label("Descrição:"), campoDescricao,
            new Label("Data:"),            campoData,
            new Label("Valor (R$) - use ponto para centavos:"),      campoValor,
            btnSalvar
        );
        return layout;            
    }
}
