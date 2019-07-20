/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public class ram {

    private ArrayList<marco> listaMarcos;
    private int cantMarcos;

    public ram(int cantMarcos) {
        this.cantMarcos = cantMarcos;
        listaMarcos = new ArrayList<marco>();
        for (int i = cantMarcos; i > 0; i--) {
            listaMarcos.add(new marco(cantMarcos - i));
        }
    }

    public ArrayList<marco> getListaMarcos() {
        return listaMarcos;
    }

    public void setListaMarcos(ArrayList<marco> listaMarcos) {
        this.listaMarcos = listaMarcos;
    }

    public boolean isFull() {
        int contMarcoFull = 0;
        for (int i = listaMarcos.size(); i > 0; i--) {
            if(listaMarcos.get(listaMarcos.size() - i).getPagProceso() != null){
                contMarcoFull++;
            }
        }
        if (contMarcoFull < cantMarcos) {
            return false;
        } else {
            return true;
        }
    }

    public int getCantMarcos() {
        return cantMarcos;
    }

    public void setCantMarcos(int cantMarcos) {
        this.cantMarcos = cantMarcos;
    }

}
