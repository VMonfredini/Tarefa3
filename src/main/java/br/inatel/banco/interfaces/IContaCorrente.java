package br.inatel.banco.interfaces;

public interface IContaCorrente {
    public void adicionarCredito(double valor);
    public boolean pagarCredito(double valor);
    public double consultaCredito();
}
