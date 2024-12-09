package projetofinal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Relatorio {
    private LocalDateTime data;
    private double faturamentoTotal;
    private List<Veiculo> veiculosRegistrados;
    private int tempoTotalPermanencia;

    public Relatorio() {
        this.data = LocalDateTime.now();
        this.veiculosRegistrados = new ArrayList<>();
        this.faturamentoTotal = 0.0;
        this.tempoTotalPermanencia = 0;
    }

    public void relatorioFaturamento(Estacionamento estacionamento) {
        System.out.println("\n--- RELATÓRIO DE FATURAMENTO ---");
        System.out.println("Data: " + data);
        
        double totalFaturamento = 0.0;
        List<Veiculo> veiculos = estacionamento.getVeiculos();
        
        for (Veiculo veiculo : veiculos) {
            double valorTarifa = veiculo.calcularTarifa(estacionamento.getTarifa());
            totalFaturamento += valorTarifa;
        }
        
        System.out.println("Faturamento Total: R$" + String.format("%.2f", totalFaturamento));
        System.out.println("Número de Veículos: " + veiculos.size());
        System.out.println("-----------------------------");
    }

    public void relatorioFluxo(Estacionamento estacionamento) {
        System.out.println("\n--- RELATÓRIO DE FLUXO ---");
        System.out.println("Data: " + data);
        
        List<Veiculo> veiculos = estacionamento.getVeiculos();
        
        System.out.println("Total de Veículos Registrados: " + veiculos.size());
        
        int totalTempoEstadia = 0;
        for (Veiculo veiculo : veiculos) {
            totalTempoEstadia += veiculo.calcularTempoPermanencia();
        }
        
        double mediaTempoEstadia = veiculos.isEmpty() ? 0 : (double) totalTempoEstadia / veiculos.size();
        
        System.out.println("Tempo Médio de Estadia: " + String.format("%.2f", mediaTempoEstadia) + " horas");
        System.out.println("Vagas Disponíveis: " + estacionamento.calcularVagasDisponiveis());
        System.out.println("-----------------------------");
    }
}