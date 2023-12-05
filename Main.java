import java.text.*;
import java.util.*;

public class Main {
  static Scanner scanner = new Scanner(System.in);
  static Concessionaria loja = new Concessionaria();
  static Veiculo veiculo;
  static Cliente cliente = new Cliente("", 0);
  static String line = "";
  static DecimalFormat df = new DecimalFormat("0.00");

  public static void main(String[] args) {
    try {
      initConcessionaria();
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }

    System.out.println("============= Dev'eiculos =============");
    System.out.println("Olá, seja bem-vindo à Dev'eiculos");
    System.out.println("Nós somos um estacionamento para Dev's\n");

    System.out.println("Por favor, para um melhor atendimento, faça o seu cadastro.");
    cadastroCliente();

    System.out.println("\nEstamos felizes em recebe-lo(a), " + cliente.getNome() + "!");

    boolean continuar = true;
    boolean firstTime = true;
    boolean error = false;
    while (continuar) {
      if (firstTime)
      {
        firstTime = false;
        comandos();
        System.out.println("O que voce deseja?");

        System.out.print("Sua resposta: ");
        line = scanner.nextLine();
      }
      else
      {
        if (!error) {
          System.out.println("\n\nQueres fazer algo mais?");
        }
        System.out.println("Caso queira relembrar os comandos, digite 'help'");

        System.out.print("Sua resposta: ");
        line = scanner.nextLine();

        if (line.equals("help")) {
          comandos();
          System.out.print("Sua resposta: ");
          line = scanner.nextLine();
        }
      }

      line = line.toLowerCase();

      try {
        switch (line) {
        // Comandos da Classe Concessionaria
          case "infosloja":
            loja.show();
            break;

          case "showdisp":
            loja.listarVeiculos("disponiveis");
            break;

          case "showvend":
            loja.listarVeiculos("vendidos");
            break;

          case "procurar":
            procurarVeiculo();
            break;

          case "comprar":
            comprar();
            break;

          case "vender":
            vender();
            break;

          case "lucro":
            mostrarLucro();
            break;

        // Comandos da Classe Cliente
          case "editarinfos":
            editarCliente();
            break;

          case "addcash":
            addDinheiro();
            break;

          case "infoscli":
            System.out.println("=====================================");
            System.out.println("Essas sao as suas informações atuais:");
            cliente.show();
            System.out.println("=====================================");
            break;

        // Comando para Sair da loja
          case "sair":
            continuar = false;
            break;

          default:
            System.out.println("Voce digitou um comando invalido\n\n");
            error = true;
            break;
        }
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }


    System.out.println("\n\n!!! Muito obrigado pelo uso do nosso servico !!!");
  }

  // Mostrar os comandos que o cliente pode utilizar
  public static void comandos() {
    System.out.println("\n================= COMANDOS ==================");
    System.out.println("------------ Cliente ------------");
    System.out.println("addCash:     adicionar um valor ao seu cash");
    System.out.println("editarInfos: editar a sua conta");
    System.out.println("infosCli:    mostra as informações do cliente");
    System.out.println("------------  Loja  ------------");
    System.out.println("infosLoja:        mostrar as informações da loja");
    System.out.println("showDisp:    mostrar os veiculos disponveis");
    System.out.println("showVend:    mostrar os veiculos vendidos");
    System.out.println("comprar:     comprar um veiculo especifico");
    System.out.println("vender:      nos vender um veiculo");
    System.out.println("procurar:    mostrar as informacoes de um veiculo da sua escolha");
    System.out.println("lucro:       mostra o lucro da empresa");
    System.out.println("--------- Sair da Loja --------");
    System.out.println("sair:        sair da loja");
    System.out.println("===============================================\n");
  }

  // Menus para operacoes da loja
  public static void mostrarLucro() {
    System.out.println("================= Lucro da loja =================");
    System.out.println("Esse é o lucro que a loja tem até agora");
    System.out.println("(levando em consideração os carros disponiveis e os já vendidos)");
    System.out.printf("LUCRO: R$ %f mil", (loja.calcLucro() / 1000));
    System.out.println("=================================================");
  }
  public static void procurarVeiculo() {
    boolean continuar = true;
    System.out.println("================== Procurar Veiculo ==================");

    do {
      System.out.println("Diga o id do veiculo que voce deseja ver as informações.");
      System.out.print("Digite aqui: ");
      line = scanner.nextLine();

      try {
        veiculo = loja.procuraVeiculo(line);
      }
      catch (Exception e) {
        System.out.println("============= ERROR ============");
        System.out.println(e.getMessage());
        System.out.println("================================");
      }

      System.out.println("\n============ VEICULO ENCOTRADO ===============");
      System.out.println("Aqui estão as informações dele:");
      veiculo.showInfos();

      System.out.println("Deseja procurar novamente? 'sim' ou 'nao'");
      System.out.print("Digite aqui: ");
      line = scanner.nextLine();

      while (!line.equals("sim") && !line.equals("nao")) {
        System.out.println("==================================");
        System.out.println("! Você digitou um valor inválido !");
        System.out.println("==================================");
        System.out.print("Digite 'sim' ou nao': ");
        line = scanner.nextLine();
      }

      if (line.equals("nao")) {
        continuar = false;
      }
    } while (continuar);

  }
  public static void comprar() {
    System.out.println("================== COMPRAR ===================");
    do {
      System.out.println(cliente.getNome() + ", por favor, nos diga o id do veiculo que deseja comprar.");
      System.out.print("Digite aqui: ");
      line = scanner.nextLine();

      try {
        loja.comprarVeiculo(line);
      } catch (Exception e) {
        System.out.println("\n================ ERROR ================");
        System.out.println(e.getMessage());
        System.out.println("========================================\n");
      }

      System.out.println("\n================ SUCESSO =================");
      System.out.println("! Seu veiculo foi comprado com sucesso !");
      System.out.println("==========================================\n");

      System.out.println("Deseja comprar mais algum veiculo? 'sim' ou 'nao'");
      System.out.print("Sua resposta: ");
      line = scanner.nextLine().toLowerCase();

      verificaSimNao();

      if (line.equals("nao")) {
        System.out.println("Obrigado por comprar conosco!");
        System.out.println("==============================================");
        return;
      }

    } while (true);
  }

  public static void vender() throws Exception {
    System.out.println("================== VENDER =================");
    do {
      System.out.println(cliente.getNome() + ", por favor, nos diga qual o veiculo que deseja nos vender.");
      System.out.println("---------- veiculos ----------");
      System.out.println("- CARRO (1)");
      System.out.println("- MOTO  (2)");
      System.out.println("------------------------------");
      System.out.print("Sua resposta: ");

      line = scanner.nextLine();

      while (!line.equals("1") && !line.equals("2")) {
        System.out.println("\n================ ERROR ================");
        System.out.println("Cliente colocou um valor invalido!");
        System.out.println("=======================================\n");
        System.out.print("Digite '1' para carro ou '2' para moto: ");
        line = scanner.nextLine();
      }

      switch (line) {
        case "1":
          venderCarro();
          break;

        case "2":
          venderMoto();
          break;
      }

      System.out.println("Deseja nos vender mais algum veiculo? 'sim' ou 'nao'");
      System.out.print("Sua resposta: ");
      line = scanner.nextLine();

      verificaSimNao();

      if (line.equals("nao")) {
        System.out.println("Obrigado por comprar conosco!");
        System.out.println("==============================================");
        return;
      }
    } while (true);
  }

  public static void venderCarro() throws Exception {
    System.out.println("=============== CARRO ===============");
    List<String> resp = pegaInfosBasicas();
    // Pega a Categoria que o Carro possui
    System.out.println("Digite a categoria do carro:");
    System.out.println("- SEDAN");
    System.out.println("- HATCH");
    System.out.println("- SUV");
    System.out.println("- PICAPE");
    System.out.print("Sua reposta: ");
    line = scanner.nextLine().toLowerCase();

    // Verifica a resposta
    while (!line.equals("sedan") && !line.equals("hatch") && !line.equals("suv") && !line.equals("picape")) {
      valorInvalidoError();
      System.out.println("Digite a categoria do carro valida:");
      System.out.println("- SEDAN");
      System.out.println("- HATCH");
      System.out.println("- SUV");
      System.out.println("- PICAPE");
      System.out.print("Sua reposta: ");
      line = scanner.nextLine().toLowerCase();
    }
    resp.add(line);

    // Pega a Tracao que o Carro possui
    System.out.println("Digite a tracao do carro:");
    System.out.println("- d : dianteira");
    System.out.println("- t : traseira");
    System.out.println("- i : integral");
    System.out.print("Sua reposta: ");
    line = scanner.nextLine().toLowerCase();

    // Verifica a respota
    while (!line.equals("d") && !line.equals("t") && !line.equals("i")) {
      valorInvalidoError();
      System.out.println("Digite uma tracao de carro valida:");
      System.out.println("- d : dianteira");
      System.out.println("- t : traseira");
      System.out.println("- i : integral");
      System.out.print("Sua reposta: ");
      line = scanner.nextLine().toLowerCase();
    }
    resp.add(line);

    // Cria o carro
    Carro carro = criaCarro(resp);

    double valor_cliente = loja.venderVeiculo(carro);

    System.out.println("============== SUCESSO ==============");
    System.out.println("Seu carro " + carro.getModelo() + " foi vendido com sucesso!");
    System.out.println("Voce recebera R$ " + df.format(valor_cliente / 1000) + " mil pelo Carro");
    System.out.println("=====================================");

    cliente.addDinheiro(valor_cliente);
  }
  public static void venderMoto() throws Exception {
    System.out.println("=============== MOTO ===============");
    List<String> resp = pegaInfosBasicas();

    // Pega a Quantidade de Marchas da Moto
    System.out.print("Digite a quantidade de marchas da moto: ");
    line = scanner.nextLine();

    if (parseDb(line) > 10) {
      while (parseDb(line) > 10) {
        valorInvalidoError();
        System.out.println("Pra quê tanta marcha, rapaz?!");
        System.out.print("Digite um valor menor que 10: ");
        line = scanner.nextLine();
      }
    }
    resp.add(line);

    // Pega o Tamanho do Motor que a Moto possui
    System.out.println("Digite o tamanho do motor:");
    System.out.println("------- Tamanhos -------");
    System.out.println("1 - 125cc");
    System.out.println("2 - 250cc");
    System.out.println("3 - 500cc");
    System.out.println("4 - 1000cc");
    System.out.println("------------------------");
    System.out.print("Sua resposta: ");
    line = scanner.nextLine().toLowerCase();

    // Verifica a resposta
    while (!line.equals("1") && !line.equals("2") && !line.equals("3") && !line.equals("4")) {
      valorInvalidoError();
      System.out.println("Digite um tamanho de motor valido:");
      System.out.println("1 - 125cc");
      System.out.println("2 - 250cc");
      System.out.println("3 - 500cc");
      System.out.println("4 - 1000cc");
      System.out.print("Sua reposta: ");
      line = scanner.nextLine().toLowerCase();
    }
    resp.add(line);

    // Verifica se a moto vem com capacete ou nao
    System.out.println("A moto vem com capacete? 'sim' ou 'nao'");
    System.out.print("Sua resposta: ");
    line = scanner.nextLine();

    verificaSimNao();

    resp.add(line);

    // Cria o carro
    Moto moto = criaMoto(resp);

    double valor_cliente = loja.venderVeiculo(moto);

    System.out.println("============== SUCESSO ==============");
    System.out.println("Sua moto " + moto.getModelo() + " foi vendida com sucesso!");
    System.out.println("Voce recebera R$ " + df.format(valor_cliente / 1000) + " mil pela Moto");
    System.out.println("=====================================");

    cliente.addDinheiro(valor_cliente);
  }

  // Menus do Cliente
  public static void cadastroCliente() {
    System.out.println("================== cadastro ===================");
    System.out.print("Diga seu nome: ");
    String nome = scanner.nextLine();
    System.out.println("Para comecar, voce tera 10 mil reais");
    System.out.println("você pode incrementar ou mudar o valor depois");
    System.out.println("===============================================");

    cliente = new Cliente(nome, 10000);
    loja.setCliente(cliente);
  }
  public static void editarCliente() {
    System.out.println("================ Menu Cliente =================");
    System.out.println("Voce escolheu editar suas informacoes:");
    System.out.println("Diga o que deseja que seja editado\n\n");

    do {
      System.out.println("mudarCli: mudar todas as informacoes");
      System.out.println("mudarNome: editar o seu nome");
      System.out.println("addCash: adicionar um valor ao seu cash");
      System.out.println("back: voltar para o menu principal\n");
      System.out.print("Digite aqui: ");
      line = scanner.nextLine();

      line = line.toLowerCase();

      switch (line) {
        case "nome":
          System.out.print("Diga seu novo nome: ");
          line = scanner.nextLine();
          loja.getCliente().setNome(line);
          break;

        case "addcash":
          addDinheiro();
          break;

        case "mudarcli":
          cadastroCliente();
          break;

        case "back":
          System.out.println("==========================================\n");
          return;

        default:
          System.out.println("Voce digitou um comando invalido\n");
          break;
      }

      System.out.println("Deseja editar mais alguma informação?");
    } while (true);
  }
  public static void addDinheiro() {
    System.out.println("\n=============== Adicionar Cash ================");
    System.out.print("Diga a quantia que deve ser adicionada: ");
    line = scanner.nextLine();

    if (parseDb(line) > 200000) {
      do {
        System.out.println("\n! Você colocou um valor muito alto, seja mais humilde !");
        System.out.print("Diga a quantia que deve ser adicionada: ");
        line = scanner.nextLine();

      } while (!(parseDb(line) < 200000));
    }

    if (parseDb(line) < 0) {
      do {
        System.out.println("\n! O valor precisa ser acima de 0 !");
        System.out.print("Diga a quantia que deve ser adicionada: ");
        line = scanner.nextLine();

      } while (!(parseDb(line) < 200000));
    }

    loja.getCliente().addDinheiro(Double.parseDouble(line));
    System.out.println("Modificação efetuada");
    System.out.println("=================================================");
  }

  // Utilitarios
  public static double parseDb(String valor) { return Double.parseDouble(valor); }
  public static void verificaSimNao() {
    while (!line.equals("sim") && !line.equals("nao")) {
      System.out.println("\n================ ERROR ================");
      System.out.println("Cliente colocou um valor invalido!");
      System.out.println("========================================");
      System.out.print("Digite 'sim' ou 'nao': ");
      line = scanner.nextLine().toLowerCase();
    }
  }
  public static void valorInvalidoError() {
    System.out.println("\n============ ERROR ===========");
    System.out.println("Cliente colocou um valor invalido!");
    System.out.println("===============================\n");
  }
  public static Carro criaCarro(List<String> lista) {
    // Informacoes Basicas
    String modelo = lista.get(0);
    String cor = lista.get(1);
    int ano = (int) parseDb(lista.get(2));
    // Informacoes Adicionais
    // Categoria

    System.out.println("Aqui");
    CategoriaCarro categoria;
    switch (lista.get(3)) {
      case "sedan":
        categoria = CategoriaCarro.SEDAN;
        break;

      case "hatch":
        categoria = CategoriaCarro.HATCH;
        break;

      case "suv":
        categoria = CategoriaCarro.SUV;
        break;

      default:
        categoria = CategoriaCarro.PICAPE;
        break;
    }
    // Tracao
    Tracao tracao;
    switch (lista.get(4)) {
      case "d":
        tracao = Tracao.DIANTEIRA;
        break;

      case "t":
        tracao = Tracao.TRASEIRA;
        break;

      default:
        tracao = Tracao.INTEGRAL;
        break;
    }

    return new Carro(modelo, cor, ano, categoria, tracao);
  }
  public static Moto criaMoto(List<String> lista) {
    // Infos basicas
    String modelo = lista.get(0);
    String cor = lista.get(1);
    int ano = (int) parseDb(lista.get(2));

    // Infos adicionais
    // Quantidade de Marchas
    int qtd_marchas = (int) parseDb(lista.get(3));

    // Tamanho do Motor
    TamanhoMotor tam_motor;
    switch (lista.get(4)) {
      case "1":
        tam_motor = TamanhoMotor.tam1;
        break;
      case "2":
        tam_motor = TamanhoMotor.tam2;
        break;
      case "3":
        tam_motor = TamanhoMotor.tam3;
        break;
      default:
        tam_motor = TamanhoMotor.tam4;
        break;
    }

    // Possui Capacete
    boolean possui_capacete = lista.get(5).equals("sim");

    return new Moto(modelo, cor, ano, qtd_marchas, tam_motor, possui_capacete);
  }
  public static List<String> pegaInfosBasicas() {
    ArrayList<String> resp = new ArrayList<>();
    // Pega o Modelo da Moto
    System.out.print("Digite o modelo: ");
    resp.add(scanner.nextLine());
    // Pega a cor da Moto
    System.out.print("Digite a cor: ");
    resp.add(scanner.nextLine());
    // Pega o ano de lancamento da Moto
    System.out.print("Digite o ano de lancamento: ");
    resp.add(scanner.nextLine());

    return resp;
  }
  public static void initConcessionaria() throws Exception {
    Veiculo[] veiculos = new Veiculo[] {
        new Moto("CG 150", "preta", 2005, 6, TamanhoMotor.tam2, true),
        new Moto("CB 300", "vermelha", 2006, 7, TamanhoMotor.tam3, true),
        new Moto("Fazer 250", "azul", 2007, 5, TamanhoMotor.tam4,true),
        new Moto("Ninja 300", "verde", 2008, 6, TamanhoMotor.tam4,false),
        new Moto("MT-03", "branca", 2009, 7, TamanhoMotor.tam4,true),
        new Carro("Fusca", "azul", 1966, CategoriaCarro.HATCH, Tracao.DIANTEIRA),
        new Carro("Palio", "preto", 2015, CategoriaCarro.SEDAN, Tracao.TRASEIRA),
        new Carro("Civic", "prata", 2020, CategoriaCarro.SEDAN, Tracao.TRASEIRA),
        new Carro("Cruze", "branco", 2022, CategoriaCarro.SEDAN, Tracao.TRASEIRA),
        new Carro("Maverick", "prata", 2010, CategoriaCarro.PICAPE, Tracao.INTEGRAL),
        new Carro("Renegade", "branco", 2015, CategoriaCarro.SUV, Tracao.INTEGRAL),
    };

    for (Veiculo v : veiculos) {
      loja.venderVeiculo(v);
    }
  }
}
