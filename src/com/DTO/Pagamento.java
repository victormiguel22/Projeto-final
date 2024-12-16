package com.DTO;

import java.time.LocalDateTime;

public class Pagamento {
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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

 

}