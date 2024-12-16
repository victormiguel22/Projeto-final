package com.DTO;

import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;

public class Veiculo {
	
    private static final LocalDateTime inicioPrograma = LocalDateTime.now();
    private static final long inicioRealMillis = System.currentTimeMillis();
	private static final int conversor = 120;
	private String placa;
    private String modelo;
    private String tipo;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;
    private double tarifaCalculada;
    
    public Veiculo(String placa, String modelo, String tipo) {
        this.placa = placa;
        this.modelo = modelo;
        this.tipo = tipo;
    }
    
    
    private static LocalDateTime getTempoLogico() {
        long tempoRealDecorridoMillis = System.currentTimeMillis() - inicioRealMillis; // tempo real (ms)
        long segundosLogicos = (tempoRealDecorridoMillis / 1000) * conversor; // tempo acelerado
        return inicioPrograma.plus(segundosLogicos, ChronoUnit.SECONDS); // ajusta o tempo
    }
    
    public int calcularTempoPermanencia() {
    	if (horaEntrada == null) return 0;

        LocalDateTime horarioFinal = horaSaida != null ? horaSaida : getTempoLogico(); // usa o tempo acelerado
        return (int) java.time.Duration.between(horaEntrada, horarioFinal).toHours();
    }
    
    public double calcularTarifa(Tarifa tarifa) {
        int tempoPermanencia = calcularTempoPermanencia();
        tarifaCalculada = tarifa.calcularValor(tempoPermanencia);
        return tarifaCalculada;
    }
    
    // getters e setters
    public String getPlaca() { return placa; }
    public String getModelo() { return modelo; }
    public String getTipo() { return tipo; }
    public void setHoraEntrada(LocalDateTime horaEntrada) { this.horaEntrada = horaEntrada; }
    public void setHoraSaida(LocalDateTime horaSaida) { this.horaSaida = horaSaida; }
}