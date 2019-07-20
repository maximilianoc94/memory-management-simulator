/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

public class procesador {
    private proceso Ejecutando;
    private pagina paginaEjecutando;
    private int secuencia;
    
    public procesador(){
    Ejecutando = null;
    }

    public proceso getEjecutando() {
        return Ejecutando;
    }

    public void setEjecutando(proceso Ejecutando) {
        this.Ejecutando = Ejecutando;
    }

    public pagina getPaginaEjecutando() {
        return paginaEjecutando;
    }

    public void setPaginaEjecutando(pagina paginaEjecutando) {
        this.paginaEjecutando = paginaEjecutando;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }


}


