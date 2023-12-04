import java.util.*;

public class Carro extends Veiculo {
  private final CategoriaCarro tipo;
  private final Tracao tracao;

  public Carro(String modelo, String cor, int ano, CategoriaCarro tipo, Tracao tracao) {
    super(modelo, cor, ano);
    this.tipo = tipo;
    this.tracao = tracao;
  }

  public String toString() {
    return "CAR " + super.toString();
  }

  @Override
  public double calcComissao(List<String> veiculos_populares) {
    double valor = (veiculos_populares.contains(this.getModelo())) ? 30 : 12;
    
    valor += tipo.getPreco() + tracao.getPreco();
    
    valor += (getAno() > 2008) ? 30 : 18;
    
    return valor * 10;
  }

  @Override
  public void showInfos() {
    super.showInfos();
    System.out.println("Categoria: " + this.tipo);
    System.out.println("Tração: " + this.tracao);
    System.out.println("====================================");
  }

  // Getters
  public String getTipo() { return this.tipo.toString(); }
  public String getTracao() { return this.tracao.toString(); }
}

