import java.util.*;
import java.text.*;

interface AgencyMethods {
  double avaliarVeiculo(Veiculo veiculo);
  double calcLucro();
  Veiculo procuraVeiculo(String id) throws Exception;
  void comprarVeiculo(String veiculoId) throws Exception;
  double venderVeiculo(Veiculo veiculo) throws Exception;
  void listarVeiculos(String listaId) throws Exception;
}

public class Concessionaria implements AgencyMethods {
  private Cliente cliente;
  private Avaliador avaliador;
  private final ArrayList<String> carros_populares;
  private final ArrayList<String> cores_populares;
  private final Map<String, Veiculo> disponiveis;
  private final Map<String, Veiculo> vendidos;

  // Metodos Privados
  private String geradorId() {
    Random rd = new Random();
    DecimalFormat df = new DecimalFormat("00");

    String id = df.format(rd.nextFloat() * 1000);

    while (disponiveis.containsKey(id) || vendidos.containsKey(id)) {
      id = df.format(rd.nextFloat() * 1000);
    }

    return id;
  }

  // Construtor
  public Concessionaria() {
    this.avaliador = new Avaliador();

    this.disponiveis = new LinkedHashMap<>();
    this.vendidos = new LinkedHashMap<>();

    this.carros_populares = new ArrayList<>(Arrays.asList("gol", "hb20", "onix", "uno", "argo"));
    this.cores_populares = new ArrayList<>(Arrays.asList("preto", "prata", "branco"));
  }

  // Mostrar as infos da loja
  public void show() {
    System.out.println("=================== dados ====================");
    System.out.println("Quantidade de veiculos disponiveis: " + disponiveis.size());
    System.out.println("Quantidade de veiculos vendidos: " + vendidos.size());
    System.out.println("Lucro total: " + calcLucro());
    System.out.println("==============================================");
  }

  // Adicionar veiculo em disponiveis
  public void addVeiculo(String id, Veiculo veiculo) throws Exception {
    if (disponiveis.containsKey(id)) {
      throw new Exception("fail: já temos um veiculo com esse id");
    }

    disponiveis.put(id, veiculo);
  }
;
  // Cliente
  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }
  public Cliente getCliente() { return this.cliente; }

  // Metodos da Interface
  public double avaliarVeiculo(Veiculo veiculo) {
    if (veiculo instanceof Carro) {
      return avaliador.avaliarCarro( (Carro) veiculo ) + veiculo.calcComissao(carros_populares);
    } else {
      return avaliador.avaliarMoto( (Moto) veiculo ) + veiculo.calcComissao(cores_populares);
    }
  }
  public double calcLucro() {
    double lucro = 0;

    for (Veiculo veiculo : this.disponiveis.values()) {
      if (veiculo instanceof Carro) {
        lucro += veiculo.calcComissao(carros_populares);
      } else if (veiculo instanceof Moto) {
        lucro += veiculo.calcComissao(cores_populares);
      } else {
        lucro += veiculo.calcComissao(List.of(""));
      }
    }

    for (Veiculo veiculo : this.vendidos.values()) {
      if (veiculo instanceof Carro) {
        lucro += veiculo.calcComissao(carros_populares);
      } else if (veiculo instanceof Moto) {
        lucro += veiculo.calcComissao(cores_populares);
      } else {
        lucro += veiculo.calcComissao(List.of(""));
      }
    }

    return lucro;
  }
  public Veiculo procuraVeiculo(String id) throws Exception {
    if (disponiveis.containsKey(id)) {
      return disponiveis.get(id);
    }

    if (vendidos.containsKey(id)) {
      return vendidos.get(id);
    }

    throw new Exception("fail: não temos o veiculo com o id " + id + " em nossa loja");
  }
  public void comprarVeiculo(String veiculoId) throws Exception {
    // Procurar o veiculo na loja;
    Veiculo veiculo = procuraVeiculo(veiculoId);

    // Verifar se o veiculo esta disponivel;
    if (!veiculo.getDisponivel()) {
      throw new Exception("fail: o veiculo com o id " + veiculoId + " nao esta disponivel");
    }

    // Verificar se o cliente consegue fazer a compra;
    cliente.comprarVeiculo(veiculo.getValor());

    veiculo.setDisponivel(false);
    this.disponiveis.remove(veiculoId);
    this.vendidos.put(veiculoId, veiculo);
  }
  public double venderVeiculo(Veiculo veiculo) throws Exception {
    String id = geradorId();

    double preco = avaliarVeiculo(veiculo);

    veiculo.setValor(preco);
    veiculo.setDisponivel(true);

    addVeiculo(id, veiculo);

    return preco * 0.75;
  }
  public void listarVeiculos(String listaId) throws Exception {
    Map<String, Veiculo> lista;
    if (listaId.equals("vendidos")) {
      lista = this.vendidos;
    } else {
      lista = this.disponiveis;
    }

    if (lista.isEmpty()) {
      throw new Exception("fail: não há carros " + listaId + " no momento");
    }

    System.out.println("===============================================");
    System.out.println("Abaixo estão os veiculos " + listaId + ":\n");
    for (Map.Entry<String, Veiculo> entry : lista.entrySet()) {
      System.out.printf("%3s : [ %s ]%n", entry.getKey(), entry.getValue());
    }
    System.out.println("===============================================");

  }
}

class Avaliador {
  public Avaliador() {}

  public double avaliarCarro(Carro carro) {
    double valor = 0;

    switch (carro.getTracao()) {
      case "dianteira":
        valor += 55;
        break;
      case "traseira":
        valor += 80;
        break;
      default:
        valor += 120;
    }
    switch (carro.getTipo()) {
      case "sedan":
        valor += 24;
        break;
      case "suv":
        valor += 27;
        break;
      case "hatch":
        valor += 12;
        break;
      default:
        valor += 32;
        break;
    }

    valor += carro.getAno() * 0.05;

    return valor * 1000 / 2.5;
  }
  public double avaliarMoto(Moto moto) {
    double valor = 0;

    switch (moto.getTamanhoMotor()) {
      case "125cc":
        valor += 40;
        break;
      case "250cc":
        valor += 55;
        break;
      case "500cc":
        valor += 80;
        break;
      default:
        valor += 100;
    }
    valor += moto.getQtdMarcha() * 12;
    valor += moto.getAno() * 0.08;
    if (moto.getPossuiCapacete()) {
      valor += 21;
    }

    return valor * 1000 / 8;
  }
}