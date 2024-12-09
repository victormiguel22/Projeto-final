package com.DTO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Cliente extends Usuario {
    private List<Pagamento> recibos = new ArrayList<>();

    public void verDetalhesDaEstadia(Veiculo veiculo) {
        System.out.println("Detalhes da Estadia:");
        System.out.println("Placa: " + veiculo.getPlaca());
        System.out.println("Modelo: " + veiculo.getModelo());
        System.out.println("Tipo: " + veiculo.getTipo());
        System.out.println("Tempo de Permanência: " + veiculo.calcularTempoPermanencia() + " horas");
    }
    
    public Pagamento realizarPagamento(Veiculo veiculo, Tarifa tarifa) {
        double valorPagamento = veiculo.calcularTarifa(tarifa);
        Pagamento pagamento = new Pagamento(valorPagamento, veiculo);
        pagamento.registrarPagamento();
        recibos.add(pagamento);
        return pagamento;
    }
    
    public void visualizarRecibo(Pagamento pagamento) {
        System.out.println(pagamento.emitirRecibo());
    }

    public void imprimirRecibos() {
        if (recibos.isEmpty()) {
            System.out.println("Nenhum recibo encontrado para " + nome);
            return;
        }
        System.out.println("Recibos de " + nome + ":");
        for (Pagamento pagamento : recibos) {
            System.out.println(pagamento.emitirRecibo());
        }
    }
}