public enum CategoriaCarro {
  SEDAN,
  HATCH,
  SUV,
  PICAPE;

  public double getPreco() {
    switch (this.name().toLowerCase()) {
      case "sedan":
        return 100;

      case "hatch":
        return 80;

      case "suv":
        return 120;

      default:
        return 110;
    }
  }

  @Override
  public String toString() {
    return this.name().toLowerCase();
  }
}
