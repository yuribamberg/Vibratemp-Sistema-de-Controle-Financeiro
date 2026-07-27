import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;

public class TelaReceita {

    public static VBox criar() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        TextField campoNome     = new TextField();
        TextField campoServico  = new TextField();
        TextField campoTelefone = new TextField();
        TextField campoData     = new TextField();
        TextField campoValor    = new TextField();

        ComboBox<String> campoStatus = new ComboBox<>();
        campoStatus.getItems().addAll("Pago", "Não Pago", "Pago 50%");
        campoStatus.setValue("Pago");

        ComboBox<String> campoTipoVidro = new ComboBox<>();
        campoTipoVidro.getItems().addAll("Temperado", "Comum", "Espelho", "Moldura", "Outro");
        campoTipoVidro.setValue("Temperado");

        Button btnSalvar = new Button("Salvar Receita");

        btnSalvar.setOnAction(e -> {
            String nome     = campoNome.getText();
            String servico  = campoServico.getText();
            String telefone = campoTelefone.getText();
            String data     = campoData.getText();
            String status   = campoStatus.getValue();
            String tipo     = campoTipoVidro.getValue();

            if (nome.isEmpty() || servico.isEmpty() || campoValor.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Atenção");
                alert.setHeaderText(null);
                alert.setContentText("Preencha todos os campos obrigatórios!");
                alert.showAndWait();
                return;
            }

            try {
                double valor = Double.parseDouble(campoValor.getText());
                Receita receita = new Receita(servico, valor, data, nome, telefone, status, tipo);
                Database.salvarReceita(receita);

                Alert sucesso = new Alert(Alert.AlertType.INFORMATION);
                sucesso.setTitle("Sucesso");
                sucesso.setHeaderText(null);
                sucesso.setContentText("Receita cadastrada com sucesso!");
                sucesso.showAndWait();

                campoNome.clear();
                campoServico.clear();
                campoTelefone.clear();
                campoData.clear();
                campoValor.clear();
                campoStatus.setValue("Pago");
                campoTipoVidro.setValue("Temperado");

            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Digite um valor válido! Use ponto para centavos. Ex: 150.50");
                alert.showAndWait();
            }
        });

        layout.getChildren().addAll(
            new Label("Nome do Cliente:"), campoNome,
            new Label("Serviço:"),         campoServico,
            new Label("Telefone:"),        campoTelefone,
            new Label("Data (dd/mm/aaaa):"), campoData,
            new Label("Valor (R$) - use ponto para centavos:"), campoValor,
            new Label("Status:"),          campoStatus,
            new Label("Tipo de Vidro:"),   campoTipoVidro,
            btnSalvar
        );

        return layout;
    }
}