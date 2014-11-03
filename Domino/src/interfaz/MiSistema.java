package interfaz;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import domino.*;
import java.util.ArrayList;
/**
 * @autor Jonathan Acosta
 */
public class MiSistema {

    /**
     * @autor Brayan Restrepo
     * Remueve todos los objetos del panel tablero y añade las fichas del 
     * Humano al Jpanel Tablero
     * 
     * @vercion 1.0 27/10/2014
     */
    public static int parSalida = 6;
    public static int turno=1;

    
    
    
    /**
     * @autor Brayan Restrepo
     * Colocar las Fichas Del Humano en el maso (Siempre se debe estar llamando)
     * @vercion 1.1
     */
    public void colocarFichaHumano(){
        Principal.tablero.removeAll();
        Principal.jSeparator1.setLocation(0, 800);
        Principal.tablero.add(Principal.jSeparator1);
        int k=10;
        for (int i = 0; i < Domino.listaFichaHumano.size(); i++) {
            Domino.listaFichaHumano.get(i).posiocion(k, 850);
            Principal.tablero.add(Domino.listaFichaHumano.get(i));
            k+=90;
        }        
        //actualiza graficos
        Principal.tablero.repaint();
    }
    
    
    /**
     * Busca el Boton Por su nombre en la lista 
     * @param nambre Nombre 
     * @param lista 
     */
    public int buscarBoton(String nambre, ArrayList<BotonFicha> lista){
        int i=-1; 
        do {
            i++;
        } while (!lista.get(i).getName().equals(nambre));
        return i;
    }
    
    /**
     * @autor Brayan REstrepo
     * GENERICO
     * Añande una ficha a la lista tablero en la pocicion N 
     * lo elimina del tablero (JPanel), y finalmente lo elimina de listaHumano
     * @param n la posicion de la ficha Humano en la lista
     * @param fin true añande la ficha al final ......*
     * @Vercion 2.1 31/10/2014
     */
    public ArrayList<BotonFicha> addFichaTablero(int n,boolean fin,ArrayList<BotonFicha> lista){
        MiSistema.turno = 0;
        if(fin){//Añade ficha en la ultima pocicion
            int k;
            if(Domino.listaTablero.size()==0){
                k=0;
            }else{
                k= Domino.listaTablero.get(Domino.listaTablero.size()-1).getNivel()+1;
            }
            Domino.listaTablero.add(lista.get(n));
            Domino.listaTablero.get(Domino.listaTablero.size()-1).setNivel(k);
        }else{//Añade ficha en la primera posicion.
            int k;
            if(Domino.listaTablero.size()==0){
                k=0;
            }else{
                k = Domino.listaTablero.get(0).getNivel()-1;
            }
            Domino.listaTablero.add(0,lista.get(n));
            Domino.listaTablero.get(0).setNivel(k);
        } 
        lista.remove(n);
        Principal.tablero.repaint();
        return lista;
    }
    
    
    /**
     * Coloca todas las fichas de la listaTablero en el Jpanel tablero o 
     * mapa de Juego
     */
    public void colocarFichaTablero(){
        int centro = 1000+(145*Domino.listaTablero.get(0).getNivel());
        for (int i = 0; i < Domino.listaTablero.size(); i++) {
            Principal.tablero.remove(Domino.listaTablero.get(i));
            int cabeza = Domino.listaTablero.get(i).getCabeza();
            int cola = Domino.listaTablero.get(i).getCola();
            Domino.listaTablero.get(i).imagenBotonHorizontal(cola,cabeza);
            Domino.listaTablero.get(i).setSize(145,85);
            Domino.listaTablero.get(i).posiocion(centro, 500);
            Principal.tablero.add(Domino.listaTablero.get(i));
            centro+=145;
        }
    }
    
}//->fin de clase
