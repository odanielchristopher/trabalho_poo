import java.text.*;
import java.util.*;

public abstract class Veiculo {
  private String modelo;
  private String cor;
  private int ano;
  private double valor;
  private boolean disponivel;

  public Veiculo(String modelo, String cor, int ano) {
    this.modelo = modelo;
    this.cor = cor;
    this.ano = ano;
    this.disponivel = true;
  }

  public String toString() {
    DecimalFormat df = new DecimalFormat("0.00");
    return String.format("%12s : %10s : %4s : R$ %4s mil", this.modelo, this.cor, this.ano, df.format(this.valor / 1000));
  }

  public abstract double calcComissao(List<String> list);
  public void showInfos() {
    System.out.println("=============== dados ==============");
    System.out.println("Modelo: " + this.modelo);
    System.out.println("Cor: " + this.cor);
    System.out.println("Ano: " + this.ano);
    System.out.println("Valor: " + this.valor);
    System.out.println("Disponivel: " + (this.disponivel ? "sim" : "não"));
    System.out.println("------ adicionais ------");
  }

  // Getters
  public String getModelo() { return this.modelo; }
  public String getCor() { return this.cor; }
  public int getAno() { return this.ano; }

  // Disponivel
  public void setDisponivel(boolean valor) { this.disponivel = valor; }
  public boolean getDisponivel() { return this.disponivel; }

  // Valor
  public void setValor(double valor) { this.valor = valor; }
  public double getValor() { return this.valor; }
}

