package br.inatel.banco.controller;

import br.inatel.banco.services.ContaBaseService;
import br.inatel.banco.services.ContaCorrenteService;
import br.inatel.banco.services.ContaPoupancaService;

import java.util.Scanner;

public class ManipulaConta {
    ContaBaseService ContaCorrente;
    ContaBaseService ContaPoupanca;

    public void menuInicial() {
        System.out.println("Selecione seu tipo de conta: \n");
        System.out.println(" (1) -> Conta Corrente");
        System.out.println(" (2) -> Conta Poupança");
        System.out.print("Escolha uma opção: ");

        Scanner teclado = new Scanner(System.in);

        short op = teclado.nextShort();

        teclado.nextLine();

        System.out.println("Entre com a agência: ");
        String agencia = teclado.nextLine();

        System.out.println("Entre com a conta: ");
        String conta = teclado.nextLine();

        System.out.println("------------------------------------");

        switch (op) {
            case 1:
                ContaCorrente = new ContaCorrenteService(agencia, conta);
                this.menuConta(ContaCorrente);
                break;
            case 2:
                ContaPoupanca = new ContaPoupancaService(agencia, conta);
                this.menuConta(ContaPoupanca);
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    public void menuConta(ContaBaseService Conta){
        short op = 0;
        while(op != 3){
            System.out.println("Conta: ");
            System.out.println(" (1) -> Depositar dinheiro em conta");
            System.out.println(" (2) -> Pagar Conta");
            System.out.println(" (3) -> Sair");
            System.out.println(" (4) -> Verificar Extrato");
            System.out.println("Escolha uma opção: ");

            Scanner teclado = new Scanner(System.in);

            op = teclado.nextShort();

            teclado.nextLine();

            switch (op){
                case 1:
                    System.out.print("Entre com o nome do depósito: ");
                    String nomeDeposito = teclado.nextLine();

                    System.out.print("Entre com o valor do depósito: ");
                    double valorDeposito = teclado.nextDouble();

                    teclado.nextLine();

                    System.out.println(this.depositarValor(Conta, valorDeposito, nomeDeposito));

                    break;
                case 2:
                    System.out.print("Entre com o nome do pagamento: ");
                    String nomePagamento = teclado.nextLine();

                    System.out.println("Entre com o valor do pagamento: ");
                    double valorPagamento = teclado.nextDouble();

                    teclado.nextLine();

                    System.out.println(this.pagamentoConta(Conta, valorPagamento, nomePagamento));

                    break;
                case 3:
                    System.out.println("Fim da operação.");
                    break;
                case 4:
                    Conta.consultaEstrato();
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    public String depositarValor(ContaBaseService Conta, double valor, String nome){
        boolean resposta = Conta.depositoConta(valor, nome);

        if (!resposta)
            return "Falha ao realizar depósito";

        return "Depósito realizado com sucesso";
    }

    public String pagamentoConta(ContaBaseService Conta, double valor, String nome){
        boolean resposta = Conta.pagarConta(valor, nome);

        if (!resposta)
            return  "Falha ao realizar pagamento";

        return "Pagamento realizado com sucesso";
    }
}
