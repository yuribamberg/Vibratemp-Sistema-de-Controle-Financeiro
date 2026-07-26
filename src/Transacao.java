// Classe abstrata — não pode ser instanciada diretamente, só serve de base para Receita e Despesa
public abstract class Transacao {

    // Atributos privados — só acessíveis pelos getters e setters (encapsulamento)
    private double valor;
    private String descricao;
    private String data;

    public Transacao(String descricao, double valor, String data) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    // Getters e setters — única forma de acessar ou modificar os atributos privados de fora da classe
    public double getValor() {
        return this.valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return this.descricao;
    }
    public String getNomeCliente() { 
        return "-"; 
    }
    public String getTelefone() { 
        return "-"; 
    }
    public String getStatus() { 
        return "-"; 
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return this.data;
    }
    public void setData(String data) {
        this.data = data;
    }
}