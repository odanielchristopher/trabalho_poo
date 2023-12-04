public enum TamanhoMotor {
  tam1("125cc"),
  tam2("250cc"),
  tam3("500cc"),
  tam4("100cc");

  private final String tam_motor;

  private TamanhoMotor(String tam_motor) {
    this.tam_motor = tam_motor;
  }

  public double getPreco() {
    switch (this.tam_motor) {
      case "125cc":
        return 12.5;

      case "250cc":
        return 25;

      case "500cc":
        return 50;

      default:
        return 80;
    }
  }

  public String getTam_motor() { return this.tam_motor; }
}
