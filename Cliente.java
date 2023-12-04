import java.text.DecimalFormat;

public class Cliente {
  private String nome;
  private double dinheiro;

  public Cliente(String nome, double valor) {
    this.nome = nome;
    setDinheiro(valor);
  }

  public void show() {
    DecimalFormat df = new DecimalFormat("0.00");
    System.out.println("Nome: " + this.nome);
    System.out.println("Dinheiro: R$ " + df.format(this.dinheiro / 1000) + " mil");
  }

  public void addDinheiro(double valor) {
    this.dinheiro += valor;
  }
  public void comprarVeiculo(double valor) throws Exception {
    if (valor > this.dinheiro) {
      throw new Exception("fail: " + this.nome + " nao possui dinheiro para efetuar a compra");
    }

    this.dinheiro -= valor;
  }

  // Getters
  public String getNome() { return this.nome; }
  public double getDinheiro() { return this.dinheiro; }

  // Setters
  public void setNome(String novo_nome) {
    this.nome = novo_nome;
  }
  public void setDinheiro(double valor) { this.dinheiro = valor; }
}
