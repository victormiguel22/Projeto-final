package com.DTO;

import java.time.LocalDateTime;

public class Veiculo {
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
    
    public int calcularTempoPermanencia() {
        if (horaEntrada == null)     	
        return 0;
  
        LocalDateTime horarioFinal = horaSaida != null ? horaSaida : LocalDateTime.now();
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