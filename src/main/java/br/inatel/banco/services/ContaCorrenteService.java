package br.inatel.banco.services;

import br.inatel.banco.interfaces.IContaCorrente;

public class ContaCorrenteService extends ContaBaseService implements IContaCorrente
{

    private double credito = 0;

    public ContaCorrenteService(String agencia, String numeroConta) {
        super(agencia, numeroConta);
    }

    @Override
    public void adicionarCredito(double valor) {
        this.credito += valor;
    }

    @Override
    public boolean pagarCredito(double valor) {
        if (valor <= 0)
            return false;

        this.pagarConta(valor, "Credito");

        this.credito -= valor;

        return true;
    }

    @Override
    public double consultaCredito() {
        return this.credito;
    }
}
