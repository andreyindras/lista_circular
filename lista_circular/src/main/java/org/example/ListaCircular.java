package org.example;

import java.util.Scanner;

public class ListaCircular {
    private No inicio;
    private No atual;

    public void inserir(String placa, String diaSemana, String horario) {
        No novo = new No(placa, diaSemana, horario);

        if (inicio == null) {
            inicio = novo;
            inicio.proximo = inicio;
            atual = inicio;
        } else {
            No temp = inicio;
            while (temp.proximo != inicio) {
                temp = temp.proximo;
            }
            temp.proximo = novo;
            novo.proximo = inicio;
        }
    }

    public void exibirLista() {
        if (inicio == null) {
            System.out.println("Nenhum veículo encontrado.");
            return;
        }

        No temp = inicio;
        do {
            System.out.println("Placa: " + temp.placa);
            System.out.println("Dia: " + temp.diaSemana);
            System.out.println("Horário: " + temp.horario);
            System.out.println();
            temp = temp.proximo;
        } while (temp != inicio);
    }

    public void navegarLista() {
        if (atual == null) {
            System.out.println("Lista vazia.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nVeículo atual:");
            System.out.println("Placa: " + atual.placa);
            System.out.println("Dia: " + atual.diaSemana);
            System.out.println("Horário: " + atual.horario);
            System.out.println("\nOpções:");
            System.out.println("1 - Próximo veículo");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();

            if (opcao == 1) {
                atual = atual.proximo;
            }
        } while (opcao != 0);
    }

    public void liberarLista() {
        if (inicio == null) return;

        No temp = inicio;
        No primeiro = inicio;

        do {
            No proximo = temp.proximo;
            temp.proximo = null;
            temp = proximo;
        } while (temp != primeiro);

        inicio = null;
        atual = null;
    }
}