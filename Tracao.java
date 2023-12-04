public enum Tracao {
  DIANTEIRA,
  TRASEIRA,
  INTEGRAL;

  public double getPreco() {
    switch (this.name().toLowerCase()) {
      case "dianteira":
        return 50;

      case "traseira":
        return 70;

      case "integral":
        return 100;

      default:
        return 0;
    }
  }

  @Override
  public String toString() {
    return this.name().toLowerCase();
  }
}
