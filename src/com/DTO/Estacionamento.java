package com.DTO;

import java.time.LocalDateTime;
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
        inicializarUsuarios();
    }
    
    private void inicializarVagas() {
        for (int i = 1; i <= capacidade; i++) {
            vagas.add(new Vaga(i, "Padrão"));
        }
    }
    
    private void inicializarUsuarios() {
        Funcionario admin = new Funcionario();
        admin.nome = "Administrador";
        admin.login = "admin";
        admin.senha = "admin123";
        admin.setAdminStatus(true);
        usuarios.add(admin);
        
        Funcionario funcionario = new Funcionario();
        funcionario.nome = "Funcionário Padrão";
        funcionario.login = "funcionario";
        funcionario.senha = "func123";
        usuarios.add(funcionario);
        
        Cliente cliente = new Cliente();
        cliente.nome = "Cliente Padrão";
        cliente.login = "cliente";
        cliente.senha = "cliente123";
        usuarios.add(cliente);
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