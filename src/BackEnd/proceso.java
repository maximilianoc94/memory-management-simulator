/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import java.util.ArrayList;

/**
 * a 3 i 3 0
 * a 3 i 2 1
 * a 3 i 1 2
 *
 *
 * @author Administrador
 */
public class proceso {

    private String nombre;
    private ArrayList<pagina> listaDePaginas;
    private boolean enEjecucion;
    private ArrayList<Integer> runOrder;

    public proceso(int cantidadPaginas, int numProceso) {
        nombre = "Proceso." + numProceso;
        runOrder = new ArrayList<>();
        listaDePaginas = new ArrayList<>();
        enEjecucion = false;
       for(int i = cantidadPaginas; i > 0; i--){
            listaDePaginas.add(new pagina(cantidadPaginas-i, numProceso));
        }
    }

    public void defineOrder(ArrayList<Integer> orden) { //Con orden
        for (int i = orden.size(); i > 0; i--) {
            runOrder = orden;
        }
    }
        
    public void defineOrder() {//Sin Orden
        for (int i = listaDePaginas.size(); i > 0; i--) {
            runOrder.add(listaDePaginas.size() - i);
        }
    }

    public void setListaDePaginas(ArrayList<pagina> listaDePaginas) {
        this.listaDePaginas = listaDePaginas;
    }

    public void setEnEjecucion(boolean enEjecucion) {
        this.enEjecucion = enEjecucion;
    }

    public ArrayList<pagina> getListaDePaginas() {
        return listaDePaginas;
    }

    public boolean isEnEjecucion() {
        return enEjecucion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Integer> getRunOrder() {
        return runOrder;
    }

    public void setRunOrder(ArrayList<Integer> runOrder) {
        this.runOrder = runOrder;
    }

}
