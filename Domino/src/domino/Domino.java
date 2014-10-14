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
    private int[] fichaRepartidas;
    
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
        this.fichaRepartidas = new int[28];
            
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
     * 
     * @vercion 1.0 13/10/2014
     */
    public void repartirFicha(){
        Random r = new Random();
        for (int i = 0; i < 28; ) {
            double n =  r.nextDouble()*100;
            if(n>=0&&n<28){
                System.out.println(n);
                i++;
            }
        }   
    }
    
    /**
     * 
     * @param n 
     * @vercion 1.0 13/10/2014
     */
    public void probarRepeticion(int n){
        
    }
    public static void main(String[] args) {
        Domino d = new Domino();
        
        d.imprimirLista(d.getListaFicha());
    }

}
