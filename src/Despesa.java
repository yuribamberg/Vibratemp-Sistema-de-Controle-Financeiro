// Despesa herda de Transacao (recebe descrição, valor e data) e implementa a interface Relatorio
public class Despesa extends Transacao implements Relatorio {

    // Construtor repassa os dados para a classe pai Transacao via super()
    public Despesa(String descricao, double valor, String data) {
        super(descricao, valor, data);
    }
    private int id;

    public int getId() {
      return this.id;
    }
    public void setId(int id) {
      this.id = id;
    }
    
    // Implementação obrigatória do método definido na interface Relatorio
   public void ExibirResumo() {
  System.out.println("┌─────────────────────────────────┐");
    System.out.println("│ Despesa : " + getDescricao());
    System.out.println("│ Valor   : R$ " + getValor());
    System.out.println("│ Data    : " + getData());
  System.out.println("└─────────────────────────────────┘");
    }
}