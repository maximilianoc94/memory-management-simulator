/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

/**
 *
 * @author Administrador
 */
public class pagina {
    private int numeroPagina;
    private int numeroProceso;

    public pagina(int numeroPagina, int numProceso) {
        this.numeroPagina = numeroPagina;
        this.numeroProceso = numProceso;
    }

    public int getNumeroPagina() {
        return numeroPagina;
    }

    public void setNumeroPagina(int numeroPagina) {
        this.numeroPagina = numeroPagina;
    }

    public int getNumeroProceso() {
        return numeroProceso;
    }

    public void setNumeroProceso(int numeroProceso) {
        this.numeroProceso = numeroProceso;
    }
    
    
}
