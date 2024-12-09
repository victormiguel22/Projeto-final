package com.DTO;

import java.time.LocalDateTime;

public class Pagamento {
    private String tipo = "Padrão";
    private double valor;
    private LocalDateTime data;
    private Veiculo veiculo;
    private String status = "Pendente";

    public Pagamento(double valor, Veiculo veiculo) {
        this.valor = valor;
        this.veiculo = veiculo;
        this.data = LocalDateTime.now();
    }

    public void registrarPagamento() {
        this.status = "Pago";
        System.out.println("Pagamento registrado com sucesso.");
    }

    public String emitirRecibo() {
        return String.format(
            "--- RECIBO DE PAGAMENTO ---\n" +
            "Veículo: %s (Modelo: %s)\n" +
            "Placa: %s\n" +
            "Data: %s\n" + 
            "Valor: R$%.2f\n" +
            "Status: %s\n" +
            "--------------------------", 
            veiculo.getTipo(), 
            veiculo.getModelo(), 
            veiculo.getPlaca(), 
            data.toString(), 
            valor, 
            status
        );
    }

    // Getters
    public double getValor() { return valor; }
    public LocalDateTime getData() { return data; }
    public Veiculo getVeiculo() { return veiculo; }
    public String getStatus() { return status; }
}