public class Receita extends Transacao implements Relatorio {
    private String nomeCliente;
    private String telefone;
    private String status;
    private String tipoVidro;
    private int id;

    public Receita(String descricao, double valor, String data, String nomeCliente, String telefone, String status, String tipoVidro) {
        super(descricao, valor, data);
        this.nomeCliente = nomeCliente;
        this.telefone = telefone;
        this.status = status;
        this.tipoVidro = tipoVidro;
    }

    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }

    public String getNomeCliente() { return this.nomeCliente; }
    public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; }

    public String getTelefone() { return this.telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getStatus() { return this.status; }
    public void setStatus(String status) { this.status = status; }

    public String getTipoVidro() { return this.tipoVidro; }
    public void setTipoVidro(String tipoVidro) { this.tipoVidro = tipoVidro; }

    public void ExibirResumo() {
        System.out.println("┌─────────────────────────────────┐");
        System.out.println("│ Cliente : " + getNomeCliente());
        System.out.println("│ Serviço : " + getDescricao());
        System.out.println("│ Telefone: " + getTelefone());
        System.out.println("│ Data    : " + getData());
        System.out.println("│ Valor   : R$ " + getValor());
        System.out.println("│ Status  : " + getStatus());
        System.out.println("│ Tipo    : " + getTipoVidro());
        System.out.println("└─────────────────────────────────┘");
    }
}