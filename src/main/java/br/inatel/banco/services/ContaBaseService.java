package br.inatel.banco.services;

import br.inatel.banco.interfaces.IContaService;
import br.inatel.banco.model.Pagamentos;

import java.util.ArrayList;

public abstract class ContaBaseService implements IContaService {
    private String agencia;
    private String numeroConta;
    private double saldo = 0;

    ArrayList<Pagamentos> extrato = new ArrayList<Pagamentos>();

    public ContaBaseService(String agencia, String numeroConta) {
        this.agencia = agencia;
        this.numeroConta = numeroConta;
    }

    @Override
    public boolean pagarConta(double valor, String nome) {
        if (this.saldo < valor)
            return false;

        this.saldo -= valor;

        Pagamentos Pagemento = new Pagamentos(-valor, nome, "Pagamento");

        extrato.add(Pagemento);

        return true;

    }

    @Override
    public boolean depositoConta(double valor, String nome) {
        if(valor <= 0)
            return false;

        this.saldo += valor;

        Pagamentos Deposito = new Pagamentos(valor, nome, "Deposito");

        extrato.add(Deposito);

        return true;
    }

    @Override
    public double consultaSaldo() {
        return this.saldo;
    }

    @Override
    public void consultaEstrato() {
        if(!this.extrato.isEmpty()) {
            for (Pagamentos pagamento : this.extrato) {
                System.out.println("Nome: " + pagamento.getNome());
                System.out.println("Valor: " + pagamento.getValor());
                System.out.println("Operação: " + pagamento.getOperacao());
                System.out.println("---------------------------------------");
            }
            System.out.println("Saldo Total: " + this.saldo);
        }
        else
        {
            System.out.println("Extrato vazio");
        }
    }

    public String getAgencia() {
        return agencia;
    }

    public String getNumeroConta() {
        return numeroConta;
    }
}
