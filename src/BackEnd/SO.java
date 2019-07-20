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
public class SO {

    private int ubicadorProcesos;
    private ArrayList<proceso> listaProcesos;
    private ram RAM;
    private disco disco;
    private procesador intel;
    private int apuntadorSwap;

    public SO(int num) {
        intel = new procesador();
        ubicadorProcesos = 0;
        apuntadorSwap = 0;
        listaProcesos = new ArrayList<>();
        RAM = new ram(num);
        disco = new disco();
    }

    public void createProcess(int paginas) {
        proceso proceso = new proceso(paginas, ubicadorProcesos);
        listaProcesos.add(proceso);
        ubicadorProcesos = ubicadorProcesos + 1;
        for (int i = paginas; i > 0; i--) {
            if (RAM.isFull()) {
                disco.getListaPaginas().add(proceso.getListaDePaginas().get(paginas - i));
            } else {
                for (int j = RAM.getCantMarcos(); j > 0; j--) {
                    if (RAM.getListaMarcos().get(RAM.getCantMarcos() - j).getPagProceso() == null) {
                        RAM.getListaMarcos().get(RAM.getCantMarcos() - j).setPagProceso(proceso.getListaDePaginas().get(paginas - i));
                        j = 0; //salida
                    }
                }
            }
        }
    }

    public int[] datosProceso(int numProceso) {
        proceso proceso = listaProcesos.get(numProceso);
        int[] datos = new int[4];
        datos[0] = proceso.getListaDePaginas().size(); // Total de paginas
        int tamanoRAM = RAM.getListaMarcos().size();
        datos[1] = 0; // paginas en RAM
        for (int i = tamanoRAM; i > 0; i--) {
            if (RAM.getListaMarcos().get(tamanoRAM - i).getPagProceso() != null) {
                if (RAM.getListaMarcos().get(tamanoRAM - i).getPagProceso().getNumeroProceso() == numProceso) {
                    datos[1]++;
                }
            }
        }//Paginas en Memoria Principal
        int tamanoDisco = disco.getListaPaginas().size();
        datos[2] = 0;
        for (int i = tamanoDisco; i > 0; i--) {
            if (disco.getListaPaginas().get(tamanoDisco - i) != null) {
                if (disco.getListaPaginas().get(tamanoDisco - i).getNumeroProceso() == numProceso) {
                    datos[2]++;
                }
            }
        } //Paginas en Memoria Virttual
        if (proceso.isEnEjecucion()) {
            datos[3] = 1;
        } else {
            datos[3] = 0;
        } //Estado del proceso
        return datos;
    }

    public void terminateProcess(int selected) {
        int tamanoRAM = RAM.getListaMarcos().size();
        for (int i = tamanoRAM; i > 0; i--) {
            if (RAM.getListaMarcos().get(i - 1).getPagProceso() != null) {
                if (RAM.getListaMarcos().get(i - 1).getPagProceso().getNumeroProceso() == selected) {
                    RAM.getListaMarcos().get(i - 1).setPagProceso(null);
                }
            }
        }//Paginas en Memoria Principal
        int tamanoDisco = disco.getListaPaginas().size();
        for (int i = tamanoDisco; i > 0; i--) {
            if (disco.getListaPaginas().get(i - 1) != null) {
                if (disco.getListaPaginas().get(i - 1).getNumeroProceso() == selected) {
                    disco.getListaPaginas().remove(i - 1);
                }
            }
        }
        listaProcesos.set(selected, null);
    }

    public void ejecutarProceso(int numProceso, int pag) {
        proceso ejecutado = listaProcesos.get(numProceso);
        if (intel.getEjecutando() != null) {
            intel.getEjecutando().setEnEjecucion(false);
        }
        pagina aBuscar = paginaEnMM(ejecutado, pag);
        if (aBuscar != null) {
            swapping(aBuscar);
        }
        ejecutado.setEnEjecucion(true);
        intel.setEjecutando(ejecutado);
        intel.setPaginaEjecutando(ejecutado.getListaDePaginas().get(ejecutado.getRunOrder().get(pag)));
        intel.setSecuencia(0);
    }

    public void swapping(pagina entraMM) {
        //llevar a disco a saleMM
        pagina saleMM = RAM.getListaMarcos().get(apuntadorSwap).getPagProceso();
        if (saleMM != null) {
            disco.getListaPaginas().add(saleMM);
        }// sacar de disco a entraMM
        int tamanoDisco = disco.getListaPaginas().size();
        for (int i = tamanoDisco; i > 0; i--) {
            pagina pagDisco = disco.getListaPaginas().get(tamanoDisco - i);
            if (pagDisco == entraMM) {
                disco.getListaPaginas().remove(tamanoDisco - i);
                i = 0;
            }
        }
        //meter entraMM
        RAM.getListaMarcos().get(apuntadorSwap).setPagProceso(entraMM);
        intel.setPaginaEjecutando(entraMM);
        apuntadorSwap += 1;
        apuntadorSwap = apuntadorSwap % RAM.getCantMarcos();
    }

    public pagina paginaEnMM(proceso ejecutado, int pagina) {
        pagina primerPagProceso = ejecutado.getListaDePaginas().get(ejecutado.getRunOrder().get(pagina));
        for (int i = RAM.getCantMarcos(); i > 0; i--) {
            pagina enRam = RAM.getListaMarcos().get(RAM.getCantMarcos() - i).getPagProceso();
            if (enRam == primerPagProceso) {
                return null;
            }
        }
        return primerPagProceso;
    }

    public boolean siguientePagina() {
        proceso ejecutando = intel.getEjecutando();
        if (ejecutando != null) {
            int pag = intel.getSecuencia();
            pag += 1;
            intel.setSecuencia(pag);
            if (pag < ejecutando.getRunOrder().size()) {
                pagina nueva = paginaEnMM(ejecutando, ejecutando.getRunOrder().get(pag));
                if (nueva != null) {
                    swapping(nueva);
                } else if (nueva == null) {
                    nueva = ejecutando.getListaDePaginas().get(ejecutando.getRunOrder().get(pag));
                }
                intel.setPaginaEjecutando(nueva);
                return false;
            } else {
                terminateProcess((Integer.parseInt(ejecutando.getNombre().substring(8))));
                intel.setEjecutando(null);
                intel.setPaginaEjecutando(null);
                intel.setSecuencia(0);
                return true;
            }
        }
        return false;
    }

// Setter y Getters
    public ArrayList<proceso> getListaProcesos() {
        return listaProcesos;
    }

    public void setListaProcesos(ArrayList<proceso> listaProcesos) {
        this.listaProcesos = listaProcesos;
    }

    public ram getRAM() {
        return RAM;
    }

    public void setRAM(ram RAM) {
        this.RAM = RAM;
    }

    public disco getDisco() {
        return disco;
    }

    public void setDisco(disco disco) {
        this.disco = disco;
    }

    public int getUbicadorProcesos() {
        return ubicadorProcesos;
    }

    public void setUbicadorProcesos(int ubicadorProcesos) {
        this.ubicadorProcesos = ubicadorProcesos;
    }

    public procesador getIntel() {
        return intel;
    }

    public void setIntel(procesador intel) {
        this.intel = intel;
    }

}
