import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import java.util.ArrayList;

public class TelaRelatorio {

    public static VBox criar() {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));

        // ── CÁLCULO TOTAL ──
        Label lblTitulo1 = new Label("📊 Cálculo Total");
        lblTitulo1.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2e9e4f;");

        Label lblTotalReceitas = new Label("Total de Receitas: R$ 0.00");
        Label lblTotalDespesas = new Label("Total de Despesas: R$ 0.00");
        Label lblSaldo         = new Label("Saldo: R$ 0.00");

        Button btnCalcularTudo = new Button("Calcular Tudo");
        btnCalcularTudo.setOnAction(e -> {
            ArrayList<Receita> receitas = Database.listarReceitas();
            ArrayList<Despesa> despesas = Database.listarDespesas();

            double totalR = 0, totalD = 0;
            for (Receita r : receitas) totalR += r.getValor();
            for (Despesa d : despesas) totalD += d.getValor();

            lblTotalReceitas.setText("Total de Receitas: R$ " + String.format("%.2f", totalR));
            lblTotalDespesas.setText("Total de Despesas: R$ " + String.format("%.2f", totalD));
            lblSaldo.setText("Saldo: R$ " + String.format("%.2f", totalR - totalD));
        });

        // ── CÁLCULO POR PERÍODO ──
        Label lblTitulo2 = new Label("📅 Cálculo por Período");
        lblTitulo2.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2e9e4f;");

        TextField campoInicio = new TextField();
        campoInicio.setPromptText("dd/mm/aaaa");

        TextField campoFim = new TextField();
        campoFim.setPromptText("dd/mm/aaaa");

        ComboBox<String> campoTipo = new ComboBox<>();
        campoTipo.getItems().addAll("Todos", "Temperado", "Comum", "Espelho", "Moldura", "Outro");
        campoTipo.setValue("Todos");

        Label lblPeriodoReceitas = new Label("Total de Receitas no período: R$ 0.00");
        Label lblPeriodoDespesas = new Label("Total de Despesas no período: R$ 0.00");
        Label lblPeriodoSaldo    = new Label("Saldo no período: R$ 0.00");

        Button btnCalcularPeriodo = new Button("Calcular Período");
        btnCalcularPeriodo.setOnAction(e -> {
            String inicio = campoInicio.getText();
            String fim    = campoFim.getText();
            String tipo   = campoTipo.getValue();

            if (inicio.isEmpty() || fim.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Atenção");
                alert.setHeaderText(null);
                alert.setContentText("Preencha as datas de início e fim!");
                alert.showAndWait();
                return;
            }

            double[] resultado = Database.calcularRelatorioPorPeriodo(inicio, fim, tipo);
            lblPeriodoReceitas.setText("Total de Receitas no período: R$ " + String.format("%.2f", resultado[0]));
            lblPeriodoDespesas.setText("Total de Despesas no período: R$ " + String.format("%.2f", resultado[1]));
            lblPeriodoSaldo.setText("Saldo no período: R$ " + String.format("%.2f", resultado[2]));
        });

        layout.getChildren().addAll(
            lblTitulo1,
            btnCalcularTudo,
            lblTotalReceitas,
            lblTotalDespesas,
            lblSaldo,
            new Separator(),
            lblTitulo2,
            new Label("Data Início:"), campoInicio,
            new Label("Data Fim:"),    campoFim,
            new Label("Tipo de Vidro:"), campoTipo,
            btnCalcularPeriodo,
            lblPeriodoReceitas,
            lblPeriodoDespesas,
            lblPeriodoSaldo
        );

        return layout;
    }
}