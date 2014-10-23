/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino;

import estructuras.Ficha;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Brayan
 */
public class Domino {

    public static ArrayList<Ficha> listaFicha = new ArrayList<Ficha>();
    public static ArrayList<Ficha> listaFichaHumano = new ArrayList<Ficha>();
    public static ArrayList<Ficha> listaFichaPc = new ArrayList<Ficha>();
    public static ArrayList<Ficha> listaTablero = new ArrayList<Ficha>();
    
    private int[] fichasBarajadas;

    /**
     * @autor Brayan Restrepo Crea todo el domino en la lista de Fichas
     * "listaFicha"
     * @vercion 1.0 13/10/2014
     */
    public Domino() {
        /*
        this.listaFicha = new ArrayList<Ficha>();
        this.listaFichaHumano = new ArrayList<Ficha>();
        this.listaFichaPc = new ArrayList<Ficha>();
        this.listaTablero = new ArrayList<Ficha>();
        */
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

    /**
     * @autor Brayan Restrepo Imprime los datos Cabeza y Cola de una lista tipo
     * Ficha
     * @param lista
     */
    public void imprimirLista(ArrayList<Ficha> lista) {
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(i+")  "+lista.get(i).getCabeza() + " - " + lista.get(i).getCola());
        }
    }

    /**
     * @autor Brayan Restrepo Barajar las fichas este metodo se encarga de
     * repartir las fichas al humano y a la maquina aleatoriamente, y deja de a
     * 14 fichas en el tablero.
     * @vercion 1.1 15/10/2014
     * @vercion 1.0 13/10/2014
     */
    public void barajarFicha() {
        int i = 0;
        do {
            //Genera un numero aleatorio entre 0 y 27
            int n = (int) Math.floor(Math.random() * 28);
            if (n < 28) {
                if (this.probarRepeticion(n)) {
                    this.fichasBarajadas[i] = n;
                    System.out.println(i + " ----> " + this.fichasBarajadas[i]);
                    i++;
                }
            }
        } while (i < 28);
    }

    /**
     * @autor Brayan Restrepo
     * @param n es el numero aleatorio para comprovar si la existe en el vector
     * @vercion 1.0 13/10/2014
     */

    public boolean probarRepeticion(int n) {

        for (int i = 0; i < this.fichasBarajadas.length; i++) {
            if (n == this.fichasBarajadas[i]) {
                return false;
            }
        }
        return true;
    }

     /**
     * @autor Brayan Restrepo
     * Repartir las fichas a pc y humano
     * @vercion 2.0 21/10/2014
     */
    public void repartirFichas(){
        for (int i = 0; i < 14; i+=2) {
            System.out.println(this.fichasBarajadas[i]+"\n"+this.fichasBarajadas[i+1]);
            this.listaFichaPc.add(this.listaFicha.get(this.fichasBarajadas[i]));
            this.listaFichaHumano.add(this.listaFicha.get(this.fichasBarajadas[i+1]));
        }
        
        //Areglo auxiliar
        int aux[] = new int[14];
        for (int i = 0; i < 14; i++) {
            aux[i] = this.fichasBarajadas[i];
        }
        
        //ordenar el arreglo en forma desendente
        aux = this.ordenarDesc(aux);
        
        //Eliminar las fichas repartidas
        System.out.println("Fichas a eliminar");
        for (int i = 0; i < 14; i++) {
            System.out.println(aux[i]);
            this.listaFicha.remove(aux[i]);
        }
    }
    
    /**
     * @autor Jonathan Acosta
     * ordena un vector de forma desendete
     * @param arreglo
     * @return arreglo
     * @vercion 1.0 21/10/2014
     */
    public int[] ordenarDesc(int[] arreglo) {
        //iteramos sobre los elementos del arreglo
        for (int i = 0 ; i < arreglo.length - 1 ; i++) {
            int max = i;
            //buscamos el mayor número
            for (int j = i + 1 ; j < arreglo.length ; j++) {
                if (arreglo[j] > arreglo[max]) {
                    max = j;    //encontramos el mayor número
                }
            }
            if (i != max) {
                //permutamos los valores
                int aux = arreglo[i];
                arreglo[i] = arreglo[max];
                arreglo[max] = aux;
            }
        }
        return arreglo;
    }
    
    /**
     * @autor brayan Andres Restrepo
     * roba una ficha para humano si el parametro es true y para la maquina en 
     * caso contrario
     * @param humano 
     * @vercion 1.0 21/10/2014
     */
    public void robarFicha(boolean humano){
        if (this.listaFicha.size()!=0){
            int n = (int) Math.floor(Math.random() * this.listaFicha.size());
            int cabeza = this.listaFicha.get(n).getCabeza();
            int cola = this.listaFicha.get(n).getCola();
            this.listaFicha.remove(n);
            if(humano){
                this.listaFichaHumano.add(new Ficha(cabeza,cola));
            }else{
                this.listaFichaPc.add(new Ficha(cabeza, cola));
            }
        }else{
            System.out.println("No hay fichas para robar");
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
        Reglas r = new Reglas();
        Scanner s = new Scanner(System.in);
        d.barajarFicha();
        d.repartirFichas();
        do {
            System.out.println("Humano presione el numero de ficha que desea Poner: ");
            d.imprimirLista(Domino.listaFichaHumano);
            int ficha = s.nextInt();
            Domino.listaTablero.add(new Ficha(Domino.listaFichaHumano.get(ficha).getCabeza(),Domino.listaFichaHumano.get(ficha).getCola()));
            Domino.listaFichaHumano.remove(ficha);
            System.out.println("Lista Tablero");
            d.imprimirLista(Domino.listaTablero);
            
            System.out.println("PC presione el numero de ficha que desea Poner: ");
            d.imprimirLista(Domino.listaFichaPc);
            int fichaPC = s.nextInt();
            Domino.listaTablero.add(new Ficha(Domino.listaFichaPc.get(fichaPC).getCabeza(),Domino.listaFichaPc.get(fichaPC).getCola()));
            Domino.listaFichaPc.remove(fichaPC);
            System.out.println("Lista Tablero");
            d.imprimirLista(Domino.listaTablero);
        } while (true);
        
        
        /*
        d.barajarFicha();
        d.imprimirLista(Domino.listaFicha);
        d.repartirFichas();
        System.out.println("Humano");
        d.imprimirLista(Domino.listaFichaHumano);
        System.out.println("PC");
        d.imprimirLista(Domino.listaFichaPc);
        System.out.println("Fichas Maso");
        d.imprimirLista(Domino.listaFicha);
        Scanner s = new Scanner(System.in);
        do {
            System.out.println("Precione 1 para robar");
            int i = s.nextInt();
            if (i == 1){
                d.robarFicha(true);
                System.out.println("FIHA ROBADA HUMANO");
                d.imprimirLista(Domino.listaFichaHumano);
            }
        } while (true);
        */

    }

}
