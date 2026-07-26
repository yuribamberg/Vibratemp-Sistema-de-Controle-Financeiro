import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import java.util.ArrayList;

public class TelaRelatorio {

    public static VBox criar() {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));

        Label lblTotalReceitas = new Label("Total de Receitas: R$ 0.00");
        Label lblTotalDespesas = new Label("Total de Despesas: R$ 0.00");
        Label lblSaldo         = new Label("Saldo: R$ 0.00");

        Button btnAtualizar = new Button("Calcular");

        btnAtualizar.setOnAction(e -> {
            ArrayList<Receita> receitas = Database.listarReceitas();
            ArrayList<Despesa> despesas = Database.listarDespesas();

            double totalReceitas = 0;
            double totalDespesas = 0;

            for (Receita r : receitas) totalReceitas += r.getValor();
            for (Despesa d : despesas) totalDespesas += d.getValor();

            double saldo = totalReceitas - totalDespesas;

            lblTotalReceitas.setText("Total de Receitas: R$ " + String.format("%.2f", totalReceitas));
            lblTotalDespesas.setText("Total de Despesas: R$ " + String.format("%.2f", totalDespesas));
            lblSaldo.setText("Saldo: R$ " + String.format("%.2f", saldo));
        });

        layout.getChildren().addAll(
            btnAtualizar,
            lblTotalReceitas,
            lblTotalDespesas,
            lblSaldo
        );

        return layout;
    }
}