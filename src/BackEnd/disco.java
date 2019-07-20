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
public class disco {
    private ArrayList<pagina> listaPaginas;
    
    public  disco(){
        listaPaginas = new ArrayList<>();
    }

    public ArrayList<pagina> getListaPaginas() {
        return listaPaginas;
    }

    public void setListaPaginas(ArrayList<pagina> listaPaginas) {
        this.listaPaginas = listaPaginas;
    }
    
    
}
