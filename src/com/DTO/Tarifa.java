package com.DTO;

public class Tarifa {
    private double valorPorHora;
    private double fracaoMinimaHora;

    public Tarifa(double valorPorHora, double fracaoMinimaHora) {
        this.valorPorHora = valorPorHora;
        this.fracaoMinimaHora = fracaoMinimaHora;
    }

    public double calcularValor(int tempoPermanencia) {
        if (tempoPermanencia < 1) {
            return valorPorHora * fracaoMinimaHora;
        } else {
        return tempoPermanencia * valorPorHora;
        }
    }

    // Setter para modificar valor por hora
    public void setValorPorHora(double valorPorHora) {
        this.valorPorHora = valorPorHora;
    }

    // Getters
    public double getValorPorHora() { return valorPorHora; }
    public double getFracaoMinimaHora() { return fracaoMinimaHora; }
}