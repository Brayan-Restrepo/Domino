/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

import javax.swing.Icon;

/**
 *
 * @author Brayan
 */
public class Ficha {
    
    private  int cola;
    private  int cabeza;
    private  String rutaFicha;

    /**
     * @autor Brayan Restrepo
     * @param cola
     * @param cabeza 
     * @vercion 1.0
     */
    public Ficha(int cabeza, int cola) {
        this.cola = cola;
        this.cabeza = cabeza;
        if(cola>cabeza){
            this.rutaFicha = "/Imagenes/"+this.cabeza+"-"+this.cola+".jpg";
        }else{
            this.rutaFicha = "/Imagenes/"+this.cola+"-"+this.cabeza+".jpg";
        }
    }

    public int getCola() {
        return cola;
    }

    public void setCola(int cola) {
        this.cola = cola;
    }

    public int getCabeza() {
        return cabeza;
    }

    public void setCabeza(int cabeza) {
        this.cabeza = cabeza;
    }

    public String getRutaFicha() {
        return rutaFicha;
    }

    public void setRutaFicha(String rutaFicha) {
        this.rutaFicha = rutaFicha;
    }
    
    
    
}