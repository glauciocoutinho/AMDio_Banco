package entities;

import java.util.Locale;
import java.util.Scanner;

public class Menu {
    Scanner input = new Scanner(System.in);

    Cliente cliente = new Cliente();
    Conta cc = new ContaCorrente(cliente);
    Conta cp = new ContaPoupanca(cliente);


    public void menu(){
        double valor;
        String titular;

        System.out.println("\n      ===========================");
        System.out.println("        O que Você deseja fazer? ");
        System.out.println("        |   1 - Depositar   |");
        System.out.println("        |   2 - Sacar   |");
        System.out.println("        |   3 - Transferir para Poupança  |");
        System.out.println("        |   4 - Extrato   |");
        System.out.println("        |   0 - Sair   |");
        System.out.println("         ========================\n");
        System.out.print("Opção Desejada: ");
        int op = input.nextInt();

        do {
            switch (op){
                case 1:
                    System.out.print("Qual é o nome do titular da conta? ");
                    cliente.setNome(titular = input.next().toUpperCase());
                    try {
                        if (cliente.getNome().substring(0, 2).matches("[A-Z]*")) {
                            System.out.print("Quanto Você deseja depositar? ");
                            System.out.print("R$ ");
                            valor = input.nextDouble();
                            cc.depositar(valor);
                        }
                    }catch (StringIndexOutOfBoundsException e){
                        System.err.println("Nome inválido! Por favor digite um nome válido!");
                    }
                    menu();
                    break;
                case 2:
                    System.out.print("Quanto Você deseja sacar? ");
                    System.out.print("R$ ");
                    valor = input.nextDouble();
                    cc.sacar(valor);
                    menu();
                    break;
                case 3:
                    System.out.print("Quanto Você deseja transferir para a sua Conta Poupança? ");
                    System.out.print("R$ ");
                    valor = input.nextDouble();
                    cc.transferir(valor, cp);
                    menu();
                    break;
                case 4:
                    cc.imprimirExtrato();
                    cp.imprimirExtrato();
                    menu();
                    break;
                case 0:
                    System.out.println("Você está saindo do Menu... Tchau, até mais!");
                    break;
                default:
                    System.err.println("Opção Inválida");
                    menu();
                    break;
            }
        }while (op != 0);
    }
}
