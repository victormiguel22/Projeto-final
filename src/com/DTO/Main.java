package com.Main;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.DTO.*;
public class Main {

	 public static void main(String[] args) {
	        Estacionamento estacionamento = new Estacionamento("Estacionamento Catarinense", 5);
	        Scanner scanner = new Scanner(System.in);
 
	        while (true) {
	            System.out.println("\n--- Sistema de Estacionamento ---");
	            System.out.println("1. Login");
	            System.out.println("2. Sair");
	            System.out.print("Escolha uma opção: ");
	            
	            int opcao = scanner.nextInt();
	            scanner.nextLine(); // pular linha
	            
	            if (opcao == 2) {
	            	System.out.println("\n---  Fim do Programa  ---");
	            	break;
	            } else if (opcao == 1) {
	                System.out.print("Login: ");
	                String login = scanner.nextLine();
	                System.out.print("Senha: ");
	                String senha = scanner.nextLine();
	                
	                Usuario usuarioLogado = fazerLogin(estacionamento, login, senha);
	                
	                if (usuarioLogado instanceof Funcionario && ((Funcionario) usuarioLogado).isAdmin()) {
	                    menuAdmin((Funcionario) usuarioLogado, estacionamento, scanner);
	                } else if (usuarioLogado instanceof Funcionario) {
	                    menuFuncionario((Funcionario) usuarioLogado, estacionamento, scanner);
	                } else if (usuarioLogado instanceof Cliente) {
	                    menuCliente((Cliente) usuarioLogado, estacionamento, scanner);
	                }
	            } else {
	            	System.out.println("\n--- Opção Inválida ---");
	            }
	        }
	    }
	    
	 private static Usuario fazerLogin(Estacionamento estacionamento, String login, String senha) {
		    for (Usuario usuario : estacionamento.getUsuarios()) {
		        if (usuario.autenticar(login, senha)) {
		            if (usuario instanceof Funcionario && login.equals("admin")) {
		                ((Funcionario) usuario).setAdminStatus(true);
		            }
		            return usuario;
		        }
		    }
		    System.out.println("Login ou senha inválidos.");
		    return null;
		}
	    
	 private static void menuAdmin(Funcionario admin, Estacionamento estacionamento, Scanner scanner) {
		    while (true) {
		        System.out.println("\n--- Menu Admin ---");
		        
		        System.out.println("1. Cadastrar Funcionário");
		        System.out.println("2. Cadastrar Cliente");
		        System.out.println("3. Modificar Tarifa");
		        System.out.println("4. Visualizar Recibos de Clientes");
		        System.out.println("5. Ver Detalhes de Estadia de Veículo");
		        System.out.println("6. Realizar Pagamento para Cliente");
		        System.out.println("7. Registrar Saída de Veículo");
		        System.out.println("8. Voltar");
		        System.out.print("Escolha uma opção: ");
		        
		        int opcao = scanner.nextInt();
		        scanner.nextLine(); // pular linha
		        
		        switch (opcao) {
		            case 1:
	                    System.out.print("Nome do funcionário: ");
	                    String nomeFuncionario = scanner.nextLine();
	                    System.out.print("Login do funcionário: ");
	                    String loginFuncionario = scanner.nextLine();
	                    System.out.print("Senha do funcionário: ");
	                    String senhaFuncionario = scanner.nextLine();
	                    System.out.print("É admin? (true/false): ");
	                    boolean isAdmin = scanner.nextBoolean();
	                    scanner.nextLine(); // limpa o buffer
	                    
	                    estacionamento.registrarFuncionario(admin, nomeFuncionario, loginFuncionario, senhaFuncionario, isAdmin);
	                    break;
		               
		            case 2:
		            	System.out.print("Nome do cliente: ");
	                    String nomeCliente = scanner.nextLine();
	                    System.out.print("Login do cliente: ");
	                    String loginCliente = scanner.nextLine();
	                    System.out.print("Senha do cliente: ");
	                    String senhaCliente = scanner.nextLine();
	                    
	                    estacionamento.registrarCliente(admin, nomeCliente, loginCliente, senhaCliente);
	                	
	                    System.out.print("Placa do veículo: ");
	                    String placa = scanner.nextLine();
	                    System.out.print("Modelo do veículo: ");
	                    String modelo = scanner.nextLine();
	                    System.out.print("Tipo do veículo: ");
	                    String tipo = scanner.nextLine();
	                    
	                    System.out.println("Vagas disponíveis:");
	                    for (Vaga vaga : estacionamento.getVagas()) {
	                        if (vaga.estaDisponivel()) {
	                            System.out.println("Vaga " + vaga.getNumero());
	                        }
	                    }
	                    
	                    System.out.print("Número da vaga desejada: ");
	                    int vagaEscolhida = scanner.nextInt();
	                    scanner.nextLine();
	                    
	                    Vaga vaga = estacionamento.encontrarVagaPorNumero(vagaEscolhida);
	                    if (vaga != null && vaga.estaDisponivel()) {
	                        Veiculo novoVeiculo = admin.cadastrarVeiculo(placa, modelo, tipo);
	                        estacionamento.adicionarVeiculo(novoVeiculo);
	                        vaga.atribuirVeiculo(novoVeiculo);
	                        System.out.println("Veículo cadastrado na vaga " + vaga.getNumero());
	                        Veiculo veiculoEntrada = estacionamento.encontrarVeiculoPorPlaca(placa);
	                        admin.registrarEntrada(veiculoEntrada);
	                    } else {
	                        System.out.println("Vaga não disponível.");
	                    }
	                    break;

		            case 3:
	                    System.out.print("Digite o novo valor por hora: ");
			                double novoValor = scanner.nextDouble();
			                admin.modificarTarifa(estacionamento.getTarifa(), novoValor);
			                break;

		            case 5:
		                admin.visualizarRecibos(estacionamento);
		                break;
		            case 6:
		                System.out.print("Digite a placa do veículo: ");
		                String placaDetalhes = scanner.nextLine();
		                Veiculo veiculoDetalhes = estacionamento.encontrarVeiculoPorPlaca(placaDetalhes);
		                if (veiculoDetalhes != null) {
		                    admin.verDetalhesDaEstadia(veiculoDetalhes);
		                }
		                break;
	                case 7:
		                System.out.print("Digite a placa do veículo: ");
			                String placaPagamento = scanner.nextLine();
			                Veiculo veiculoPagamento = estacionamento.encontrarVeiculoPorPlaca(placaPagamento);
			                if (veiculoPagamento != null) {
			                    Pagamento pagamento = admin.realizarPagamento(veiculoPagamento, estacionamento.getTarifa());
			                        admin.visualizarRecibo(pagamento);
			                }
		                break;
	                    
	                case 8:
	                	System.out.print("Placa do veículo para saída: ");
	                    String placaSaida = scanner.nextLine();
	                    Veiculo veiculoSaida = estacionamento.encontrarVeiculoPorPlaca(placaSaida);
	                    if (veiculoSaida != null) {
	                        admin.registrarSaida(veiculoSaida, estacionamento);
	                    }
	                	break;
	                case 9:
	                	return;
		        }
		    }
	 }
	    private static void menuFuncionario(Funcionario funcionario, Estacionamento estacionamento, Scanner scanner) {
	        while (true) {
	            System.out.println("\n--- Menu Funcionário ---");
	            System.out.println("1. Cadastrar Cliente");
	            System.out.println("2. Registrar Saída de Veículo");
	            System.out.println("3. Voltar");
	            System.out.print("Escolha uma opção: ");
	            
	            int opcao = scanner.nextInt();
	            scanner.nextLine();
	            
	            switch (opcao) {
	                case 1:
	                	
	                	System.out.print("Nome do cliente: ");
	                    String nomeCliente = scanner.nextLine();
	                    System.out.print("Login do cliente: ");
	                    String loginCliente = scanner.nextLine();
	                    System.out.print("Senha do cliente: ");
	                    String senhaCliente = scanner.nextLine();
	                    
	                    estacionamento.registrarCliente(funcionario, nomeCliente, loginCliente, senhaCliente);
	                	
	                    System.out.print("Placa do veículo: ");
	                    String placa = scanner.nextLine();
	                    System.out.print("Modelo do veículo: ");
	                    String modelo = scanner.nextLine();
	                    System.out.print("Tipo do veículo: ");
	                    String tipo = scanner.nextLine();
	                    
	                    System.out.println("Vagas disponíveis:");
	                    for (Vaga vaga : estacionamento.getVagas()) {
	                        if (vaga.estaDisponivel()) {
	                            System.out.println("Vaga " + vaga.getNumero());
	                        }
	                    }
	                    
	                    System.out.print("Número da vaga desejada: ");
	                    int vagaEscolhida = scanner.nextInt();
	                    scanner.nextLine();
	                    
	                    Vaga vaga = estacionamento.encontrarVagaPorNumero(vagaEscolhida);
	                    if (vaga != null && vaga.estaDisponivel()) {
	                        Veiculo novoVeiculo = funcionario.cadastrarVeiculo(placa, modelo, tipo);
	                        estacionamento.adicionarVeiculo(novoVeiculo);
	                        vaga.atribuirVeiculo(novoVeiculo);
	                        System.out.println("Veículo cadastrado na vaga " + vaga.getNumero());
	                        Veiculo veiculoEntrada = estacionamento.encontrarVeiculoPorPlaca(placa);
	                        funcionario.registrarEntrada(veiculoEntrada);
	                    } else {
	                        System.out.println("Vaga não disponível.");
	                    }
	                    break;
	                    
	                case 2:
	                    System.out.print("Placa do veículo para saída: ");
	                    String placaSaida = scanner.nextLine();
	                    Veiculo veiculoSaida = estacionamento.encontrarVeiculoPorPlaca(placaSaida);
	                    if (veiculoSaida != null) {
	                        funcionario.registrarSaida(veiculoSaida, estacionamento);
	                    }
	                    break;
	                case 3:
	                    return;
	            }
	        }
	    }
	    
	    private static void menuCliente(Cliente cliente, Estacionamento estacionamento, Scanner scanner) {
	        while (true) {
	            System.out.println("\n--- Menu Cliente ---");
	            System.out.println("1. Ver Detalhes da Estadia");
	            System.out.println("2. Realizar Pagamento");
	            System.out.println("3. Voltar");
	            System.out.print("Escolha uma opção: ");
	            
	            int opcao = scanner.nextInt();
	            scanner.nextLine();
	            
	            switch (opcao) {
	                case 1:
	                    System.out.print("Placa do veículo: ");
	                    String placa = scanner.nextLine();
	                    Veiculo veiculo = estacionamento.encontrarVeiculoPorPlaca(placa);
	                    if (veiculo != null) {
	                        cliente.verDetalhesDaEstadia(veiculo);
	                    }
	                    break;
	                case 2:
	                    System.out.print("Placa do veículo: ");
	                    String placaPagamento = scanner.nextLine();
	                    Veiculo veiculoPagamento = estacionamento.encontrarVeiculoPorPlaca(placaPagamento);
	                    if (veiculoPagamento != null) {
	                        Pagamento pagamento = cliente.realizarPagamento(veiculoPagamento, estacionamento.getTarifa());
	                        cliente.visualizarRecibo(pagamento);
	                    }
	                    break;
	                case 3:
	                    return;
	            }
	        }
	    }
	}
	
