package org.example;

public class No {
    public String placa;
    public String diaSemana;
    public String horario;
    public No proximo;

    public No(String placa, String diaSemana, String horario) {
        this.placa = placa;
        this.diaSemana = diaSemana;
        this.horario = horario;
        this.proximo = null;
    }
}