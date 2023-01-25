package br.inatel.banco.services;

import br.inatel.banco.interfaces.IContaPoupanca;

public class ContaPoupancaService extends ContaBaseService implements IContaPoupanca {

    private double rendimento = 0;

    public ContaPoupancaService(String agencia, String numeroConta) {
        super(agencia, numeroConta);
    }

    @Override
    public void adicionaRendimento() {
        this.rendimento += this.consultaSaldo()*0.01;
    }

    @Override
    public double consultaRendimento() {
        return this.rendimento;
    }
}
