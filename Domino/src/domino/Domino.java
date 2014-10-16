/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino;

import estructuras.Ficha;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Brayan
 */
public class Domino {

    private ArrayList<Ficha> listaFicha;
    private ArrayList<Ficha> listaFichaHumano;
    private ArrayList<Ficha> listaFichaPc;
    private ArrayList<Ficha> listaTablero;
    private int[] fichasBarajadas;
    
    /**
     * @autor Brayan Restrepo Crea todo el domino en la lista de Fichas
     * "listaFicha"
     * @vercion 1.0 13/10/2014
     */
    public Domino() {

        this.listaFicha = new ArrayList<Ficha>();
        this.listaFichaHumano = new ArrayList<Ficha>();
        this.listaFichaPc = new ArrayList<Ficha>();
        this.listaTablero = new ArrayList<Ficha>();
        this.fichasBarajadas = new int[28];
        
        //relleno el vector con -1 para evitar futuros problemas al barajar
        for (int i = 0; i < this.fichasBarajadas.length; i++) {
            this.fichasBarajadas[i] = -1;
        }
            
        //Crea todo el domino en la lista de Fichas "listaFicha"
        for (int i = 0; i < 7; i++) {
            for (int j = i; j < 7; j++) {
                this.listaFicha.add(new Ficha(i, j));
            }
        }
    }

    public ArrayList<Ficha> getListaFicha() {
        return listaFicha;
    }

    public ArrayList<Ficha> getListaFichaHumano() {
        return listaFichaHumano;
    }

    public ArrayList<Ficha> getListaFichaPc() {
        return listaFichaPc;
    }

    public ArrayList<Ficha> getListaTablero() {
        return listaTablero;
    }
    
    /**
     * @autor Brayan Restrepo
     * Imprime los datos Cabeza y Cola de una lista tipo Ficha
     * @param lista 
     */
    public void imprimirLista(ArrayList<Ficha> lista) {
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).getCabeza() + " - " + lista.get(i).getCola());
        }
    }
    
    /**
     * @autor Brayan Restrepo
     * Barajar las fichas
     * @vercion 1.1 15/10/2014
     */
    public void barajarFicha(){
        Random r = new Random();
        int i = 0;
        do {
            int n = (int)Math.floor(Math.random()*28);
            if(n<28){
                if(this.probarRepeticion(n)){
                    this.fichasBarajadas[i] = n;
                    System.out.println(i+" ----> "+this.fichasBarajadas[i]);
                    i++;
                }
            }
        }while(i<28);
        
    }
    
    /**
     * @autor Brayan Restrepo
     * @param n es el numero aleatorio para comprovar si la existe en el vector
     * @vercion 1.0 13/10/2014
     */
    public boolean  probarRepeticion(int n){
        
        for (int i = 0; i < this.fichasBarajadas.length; i++) {
            if(n==this.fichasBarajadas[i]){
                return false;
            }
        }
        return true;
    }
    
    /**
     * @autor Brayan Restrepo
     * Repartir las fichas a pc y humano
     * @vercion 1.0 15/10/2014
     */
    public void repartirFichas(){
        for (int i = 0; i < 14; i+=2) {
            this.listaFichaPc.add(this.listaFicha.get(this.fichasBarajadas[i]));
            this.listaFichaHumano.add(this.listaFicha.get(this.fichasBarajadas[i+1]));
        }
        //Eliminar las fichas repartidas
        for (int i = 0; i < 14; i++) {
            this.listaFicha.remove(i);
        }
    }
    
    /**
     * @autor Brayan Restrepo
     * @nomber main: metodo temporal solo para hacer pruebas
     * @param args 
     * @vercion 1.0 14/10/2014
     */
    public static void main(String[] args) {
        Domino d = new Domino();
        d.barajarFicha();
        d.imprimirLista(d.getListaFicha());
        d.repartirFichas();
        System.out.println("Humano");
        d.imprimirLista(d.getListaFichaHumano());
        System.out.println("PC");
        d.imprimirLista(d.getListaFichaPc());
        System.out.println("Fichas Maso");
        d.imprimirLista(d.getListaFicha());
    }

}
