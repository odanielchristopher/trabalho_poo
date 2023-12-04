import java.util.*;

public class Moto extends Veiculo {
  private final int qtd_marcha;
  private final TamanhoMotor tam_motor;
  private boolean possui_capacete;

  public Moto(String modelo, String cor, int ano, int qtd_marcha, TamanhoMotor tam_motor, boolean possui_capacete) {
    super(modelo, cor, ano);
    this.qtd_marcha = qtd_marcha;
    this.tam_motor = tam_motor;
    this.possui_capacete = possui_capacete;
  }

  public String toString() {
    return "MOT " + super.toString();
  }

  @Override
  public double calcComissao(List<String> cores_populares) {
    double valor = ((cores_populares.contains(getCor())) ? 18 : 11);

    valor += (qtd_marcha > 6) ? 50 : 30;
    valor += tam_motor.getPreco();
    valor += (possui_capacete) ? 40 : 0;

    return valor * 10;
  }
  @Override
  public void showInfos() {
    super.showInfos();
    System.out.println("Tamanho do motor: " + this.tam_motor);
    System.out.println("Marchas: " + this.qtd_marcha + " marchas");
    System.out.println("Possui capacete" + ((possui_capacete) ? "sim" : "não"));
    System.out.println("====================================");
  }

  // Getters
  public int getQtdMarcha() { return this.qtd_marcha; }
  public String getTamanhoMotor() { return this.tam_motor.getTam_motor(); }

  // Possui Capacete
  public void adicionarCapacete() { this.possui_capacete = true; }
  public boolean getPossuiCapacete() { return this.possui_capacete; }
}

