package projetofinal;

import java.time.LocalDateTime;

class Vaga {
    private int numero;
    private String tipo;
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
    
    public Veiculo getVeiculo() { return veiculo; }
    public int getNumero() { return numero; }
}

