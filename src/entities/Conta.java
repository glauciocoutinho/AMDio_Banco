package entities;

import java.util.Objects;
import java.util.Random;

public abstract class Conta {
    Random random = new Random();

    private static final int AGENCIA_PADRAO = 1;

    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;

    public Conta(Cliente cliente) {
        this.agencia = Conta.AGENCIA_PADRAO;
        this.numero = 1 + random.nextInt(9999);
        this.cliente = cliente;
    }

    public void sacar(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            System.out.printf("Valor sacado R$ %.2f\n",valor);
        }else {
            System.err.println("Saldo insuficiente! Aproveite e faça um depósito!\n");
        }
    }

    public void depositar(double valor) {
        saldo += valor;
        System.out.printf("Valor depositado R$ %.2f\n",valor);
    }

    public void transferir(double valor, Conta contaDestino) {
        if (saldo >= valor) {
            this.sacar(valor);
            contaDestino.depositar(valor);
        }else {
            System.err.println("Saldo insuficiente, impossível realizar transferência!\n");
        }
    }


    protected void imprimirExtrato() {
        if (Objects.isNull(this.cliente.getNome())){
            System.err.println("Titular: Nome inválido!");
            System.err.println("Agencia: Agência inválida!");
            System.err.println("Numero: Número de conta Inválido!");
            System.err.printf("Saldo: %.2f", this.saldo);
        }else {
            System.out.printf("Titular: %s%n", this.cliente.getNome());
            System.out.printf("Agencia: %d%n", this.agencia);
            System.out.printf("Numero: %d%n", this.numero);
            System.out.printf("Saldo: %.2f%n", this.saldo);
        }
    }


    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }
}
