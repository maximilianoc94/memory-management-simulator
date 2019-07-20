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
public class marco {
    private pagina pagProceso;
    private int NumMarco;
    private String NombreMarco;

    public marco(int NumMarco) {
        this.NumMarco = NumMarco;
        NombreMarco = NumMarco + " - ";
    }

    public pagina getPagProceso() {
        return pagProceso;
    }

    public void setPagProceso(pagina pagProceso) {
        this.pagProceso = pagProceso;
    }

    public int getNumMarco() {
        return NumMarco;
    }

    public void setNumMarco(int NumMarco) {
        this.NumMarco = NumMarco;
    }

    public String getNombreMarco() {
        return NombreMarco;
    }

    public void setNombreMarco(String NombreMarco) {
        this.NombreMarco = NombreMarco;
    }
    
}
