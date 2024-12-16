package com.DTO;
import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
    private String nome;
    private List<Vaga> vagas = new ArrayList<>();
    private List<Veiculo> veiculos = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private double faturamento;
    private int capacidade;
    private Tarifa tarifa;
    
    public Estacionamento(String nome, int capacidade) {
        this.nome = nome;
        this.capacidade = capacidade;
        this.tarifa = new Tarifa(10.0, 0.5); // Tarifa inicial: R$10 por hora
        inicializarVagas();
        inicializarAdmin();
    }
    
    private void inicializarVagas() {
        for (int i = 1; i <= capacidade; i++) {
            vagas.add(new Vaga(i));
        }
    }
    
    private void inicializarAdmin() {
        Funcionario admin = new Funcionario();
        admin.nome = "Administrador";
        admin.login = "admin";
        admin.senha = "admin123";
        admin.setAdminStatus(true);
        usuarios.add(admin);
    }
    
    public boolean registrarFuncionario(Funcionario admin, String nome, String login, String senha, boolean isAdmin) {
        if (!admin.isAdmin()) {
            System.out.println("Apenas administradores podem registrar funcionários.");
            return false;
        }
        
        for (Usuario usuario : usuarios) {
            if (usuario.login.equals(login)) {
                System.out.println("Login já existe. Por favor, escolha outro.");
                return false;
            }
        }
        
        Funcionario novoFuncionario = new Funcionario();
        novoFuncionario.nome = nome;
        novoFuncionario.login = login;
        novoFuncionario.senha = senha;
        novoFuncionario.setAdminStatus(isAdmin);
        
        usuarios.add(novoFuncionario);
        System.out.println("Funcionário " + nome + " registrado.");
        return true;
    }
    
    public boolean registrarCliente(Usuario usuarioRegistrador, String nome, String login, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.login.equals(login)) {
                System.out.println("Login já existe. Por favor, escolha outro.");
                return false;
            }
        }
        
        Cliente novoCliente = new Cliente();
        novoCliente.nome = nome;
        novoCliente.login = login;
        novoCliente.senha = senha;
        
        usuarios.add(novoCliente);
        System.out.println("Cliente " + nome + " registrado com sucesso.");
        return true;
    }
    
    public List<Veiculo> getVeiculos() {
        return veiculos;
    }
    
    public void adicionarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }
    
    public Veiculo encontrarVeiculoPorPlaca(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        System.out.println("Veículo não encontrado.");
        return null;
    }
    
    public Vaga encontrarVagaDisponivel() {
        for (Vaga vaga : vagas) {
            if (vaga.estaDisponivel()) {
                return vaga;
            }
        }
        return null;
    }

    public Vaga encontrarVagaPorNumero(int numero) {
        for (Vaga vaga : vagas) {
            if (vaga.getNumero() == numero) {
                return vaga;
            }
        }
        System.out.println("Vaga não encontrada.");
        return null;
    }
    
    public int calcularVagasDisponiveis() {
        int vagasDisponiveis = 0;
        for (Vaga vaga : vagas) {
            if (vaga.estaDisponivel()) {
                vagasDisponiveis++;
            }
        }
        return vagasDisponiveis;
    }
    
    public void mensagemEstacionamentoCheio() {
        if (calcularVagasDisponiveis() == 0) {
            System.out.println("Estacionamento está lotado!");
        }
    }
    
    // Getters
    public List<Vaga> getVagas() {
        return vagas;
    }
    
    public List<Usuario> getUsuarios() { return usuarios; }
    public Tarifa getTarifa() { return tarifa; }
}
