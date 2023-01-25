package br.inatel.banco.tests;

import br.inatel.banco.services.ContaCorrenteService;
import br.inatel.banco.services.ContaPoupancaService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ContaTest {
    ContaCorrenteService cc;
    ContaPoupancaService cp;

    /*
    @Before
    public void setup(){
        cc = new ContaCorrenteService("1234", "12345678-9");
        cp = new ContaPoupancaService("4321", "98765432-1");
    }
     */

    @Test
    public void ContaCorrentePagarCredito_Ok(){
        cc = new ContaCorrenteService("1234", "12345678-9");
        boolean verifica = cc.pagarCredito(10);
        assertEquals(true, verifica);
    }

    @Test
    public void ContaCorrentePagarCredito_Error(){
        cc = new ContaCorrenteService("1234", "12345678-9");
        assertEquals(false, cc.pagarCredito(-10));
    }

    @Test
    public void contaPoupancaAdicionaRendimento_Ok(){
        cp = new ContaPoupancaService("4321", "98765432-1");
        cp.depositoConta(5600, "teste");
        cp.adicionaRendimento();
        assertEquals(56.0, cp.consultaRendimento(), 0);
    }

    @Test
    public void ContaPoupancaAdicionaRendimento_Ok2(){
        cp = new ContaPoupancaService("4321", "98765432-1");
        cp.depositoConta(10000, "teste");
        cp.adicionaRendimento();
        assertEquals(100,cp.consultaRendimento(),0);
    }

    @Test
    public void ContaPoupancaConfereRendimento_Ok(){
        cp = new ContaPoupancaService("4321", "98765432-1");
        cp.adicionaRendimento();
        assertEquals(0,cp.consultaRendimento(),0);
    }

    @Test
    public void ContaPoupancaConfereRendimento_Error(){
        cc = new ContaCorrenteService("1234", "12345678-9");
        cp = new ContaPoupancaService("4321", "98765432-1");
        cp.adicionaRendimento();
        assertEquals(0,cp.consultaRendimento(),0);
    }

    @Test
    public void ContaCorrenteConsultaCredito_Ok(){
        cc = new ContaCorrenteService("1234", "12345678-9");
        assertEquals(0,cc.consultaCredito(),0);
    }
}
