// Receita herda de Transacao (recebe descrição, valor e data) e implementa a interface Relatorio
public class Receita extends Transacao implements Relatorio {
    private String nomeCliente;
    private String telefone;
    private String status;
    private int id;
    // Construtor repassa os dados para a classe pai Transacao via super()
    public Receita(String descricao, double valor, String data, String nomeCliente, String telefone, String status) {
        super(descricao, valor, data);

        this.nomeCliente = nomeCliente;
        this.telefone = telefone;
        this.status = status;
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNomeCliente() {
        return this.nomeCliente;
    }
    public void setnomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String gettelefone() {
        return this.telefone;
    }
    public void settelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getstatus() {
        return this.status;
    }
    public void setstatus(String status) {
        this.status = status;
    }

    // Implementação obrigatória do método definido na interface Relatorio
    public void ExibirResumo() {
  System.out.println("┌─────────────────────────────────┐");
    System.out.println("│ Cliente : " + getNomeCliente());
    System.out.println("│ Serviço : " + getDescricao());
    System.out.println("│ Telefone: " + gettelefone());
    System.out.println("│ Data    : " + getData());
    System.out.println("│ Valor   : R$ " + getValor());
    System.out.println("│ Status  : " + getstatus());
  System.out.println("└─────────────────────────────────┘");
    }
}