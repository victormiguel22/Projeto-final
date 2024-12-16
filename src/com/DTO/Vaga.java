package com.DTO;

public class Vaga {
    private int numero;
    private boolean ocupada;
    private Veiculo veiculo;
    
    public Vaga(int numero, String tipo) {
        this.numero = numero;
        this.tipo = tipo;
        this.ocupada = false;
    }
    
    public void atribuirVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
        this.ocupada = true;
    }
    
    public void liberarVaga() {
        this.veiculo = null;
        this.ocupada = false;
    }
    
    public boolean estaDisponivel() {
        return !ocupada;
    }

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isOcupada() {
		return ocupada;
	}

	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
    

}
