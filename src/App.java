import java.util.ArrayList;                // Lista dinâmica para armazenar receitas e despesas
import java.util.InputMismatchException;   // Exceção lançada quando o usuário digita texto em vez de número
import java.util.Scanner;                  // Leitura de entradas do usuário via terminal
import java.util.Locale;                   // Configuração regional (usado para definir o separador decimal)

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        // Locale.US garante que o separador decimal seja ponto (.) em vez de vírgula
        scanner.useLocale(Locale.US); 

        int opcao;

        Database.criarTabelas();
        // do/while garante que o menu apareça pelo menos uma vez antes de checar a condição de saída
        do {
            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("  ║     VIDRAÇARIA - CONTROLE FINANCEIRO ║");
            System.out.println("  ╚══════════════════════════════════════╝");
            System.out.println(" [1] Cadastrar Receita");
            System.out.println(" [2] Cadastrar Despesa");
            System.out.println(" [3] Listar Transações");
            System.out.println(" [4] Relatórios");
            System.out.println(" [5] Pesquisar");
            System.out.println(" [0] Sair");
            System.out.print("\n  » Escolha uma opção: ");

            try { 
                opcao = scanner.nextInt();
            } catch (InputMismatchException e) {
                // Se o usuário digitar texto em vez de número, evita que o programa trave
                System.out.println("Opção inválida! Digite um número.");
                scanner.nextLine(); // limpa o buffer do scanner para a próxima leitura funcionar
                opcao = -1;
            }

            switch (opcao) {
                case 1: {
                    scanner.nextLine();
                    System.out.print("Nome do cliente: ");
                    String nomeCliente = scanner.nextLine();

                    System.out.print("Serviço: ");
                    String descricao = scanner.nextLine();
                    
                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine();

                    System.out.print("Data: ");
                    String data = scanner.nextLine();

                    System.out.print("Valor(use pontos para centavos): R$");
                    double valor = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Status(Pago, não pago ou pago 50%): ");
                    String status = scanner.nextLine();

                    System.out.print("Tipo de Vidro(Temperado, Comum, Espelho, Moldura, Outro): ");
                    String tipoVidro = scanner.nextLine();

                    Receita receita = new Receita(descricao, valor, data, nomeCliente, telefone, status, tipoVidro);
                    Database.salvarReceita(receita);

                    System.out.println("Receita cadastrada!");
                    break;
                }
                case 2: {
                    scanner.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();

                    System.out.print("Valor (use pontos para centavos): R$");
                    double valor = scanner.nextDouble();
                    scanner.nextLine();
                    
                    System.out.print("Data: ");
                    String data = scanner.nextLine();

                    Despesa despesa = new Despesa(descricao, valor, data);
                    Database.salvarDespesa(despesa);

                    System.out.println("Despesa cadastrada!");
                    break;
                }
                case 3: {
                    ArrayList<Receita> receitas = Database.listarReceitas();
                    ArrayList<Despesa> despesas = Database.listarDespesas();

                    System.out.println("\n====== Transações ======");

                    if (receitas.isEmpty() && despesas.isEmpty()) {
                        System.out.println("Nenhuma transação cadastrada!");
                    } else {
                        for (Receita r : receitas) {
                            r.ExibirResumo();
                            System.out.println("--------------------");
                        }
                        for (Despesa d : despesas) {
                            d.ExibirResumo();
                            System.out.println("--------------------");
                        }
                }
                break;
            }
                case 4: {
                    ArrayList<Receita> receitas = Database.listarReceitas();
                    ArrayList<Despesa> despesas = Database.listarDespesas();

                    double totalReceitas = calcularTotalReceitas(receitas);
                    double totalDespesas = calcularTotalDespesas(despesas);
                    double saldo = totalReceitas - totalDespesas;

                    System.out.println("\n====== Relatório ======");
                    System.out.println("Total de Receitas: R$" + totalReceitas);
                    System.out.println("Total de Despesas: R$" + totalDespesas);
                    System.out.println("Saldo: R$" + saldo);
                    break;
                }
                case 5: {
                    scanner.nextLine();
                    System.out.print("Digite nome, serviço, data ou status: ");
                    String busca = scanner.nextLine();
                    
                    ArrayList<Receita> resultado = Database.pesquisarCliente(busca);

                    if (resultado.isEmpty()) {
                        System.out.println("Nenhum resultado encontrado!");
                    } else {
                        System.out.println("\n====== Resultados ======");
                        for (Receita r : resultado) {
                            r.ExibirResumo();
                            System.out.println("--------------------");
                        }
                    }
                    break;
                }
                case 0:
                    System.out.println("Encerrando sistema...");
                    break;
                    
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
        scanner.close();
    
    }

    private static double calcularTotalReceitas(ArrayList<Receita> receitas) {
        double total = 0;
        for (Receita r : receitas) {
            total += r.getValor();
        }
        return total;
    }

    private static double calcularTotalDespesas(ArrayList<Despesa> despesas) {
        double total = 0;
        for (Despesa d : despesas) {
            total += d.getValor();
        }
        return total;
    }

}