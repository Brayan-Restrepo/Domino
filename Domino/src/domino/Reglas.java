/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino;

import interfaz.BotonFicha;
import interfaz.MiSistema;
import interfaz.Principal;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Brayan
 */
public class Reglas {

    public MiSistema mst = new MiSistema();

    public void jugar() {
        if (MiSistema.turno == 0) {
            
            
            if (Domino.listaTablero.size() == 0) {//De de tirar un par
                this.empezarDoble();
            } else {
                this.heuristica1();
            }
        }
        if (Domino.listaTablero.size() != 0) {
            mst.colocarFichaTablero();
        }
        MiSistema.turno = 1;
        if(Domino.listaTablero.size() == 0 && MiSistema.turno == 1){
            JOptionPane.showMessageDialog(null, "¿TIENES LA FICHA?", "EMPEZAR",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("F:\\Domino\\Domino\\src\\Imagenes/" + MiSistema.parSalida + "-" + MiSistema.parSalida + "v.png"));
        }
    }

    /**
     * @autor Brayan Restrepo SE EJECUTA UNA VEZ, para poner ficha PAR
     * @version 1.0 02/11/2014
     */
    public void empezarDoble() {
        for (int i = 0; i < Domino.listaFichaPc.size(); i++) {
            if (Domino.listaFichaPc.get(i).getCabeza() == MiSistema.parSalida && Domino.listaFichaPc.get(i).getCola() == MiSistema.parSalida) {
                Domino.listaFichaPc = mst.addFichaTablero(i, true, Domino.listaFichaPc);//Añade Ficha al listaTablero
                break;
            }
        }
    }

    /**
     * @autor Brayan Restrepo Pone la primera ficha de la lista en el tablero en
     * el tablero
     * @version 1.0 01/11/2014
     */
    public void ponerPrimerFicha() {
        int tlt = Domino.listaTablero.size();
        while (true) {
            for (int i = 0; i < Domino.listaFichaPc.size(); i++) {

                if (Domino.listaFichaPc.get(i).getCabeza() == Domino.listaTablero.get(0).getCola()) {
                    Domino.listaFichaPc = mst.addFichaTablero(i, false, Domino.listaFichaPc);//Añade Ficha al listaTablero
                    break;
                } else if (Domino.listaFichaPc.get(i).getCola() == Domino.listaTablero.get(0).getCola()) {
                    int z = Domino.listaFichaPc.get(i).getCabeza();
                    Domino.listaFichaPc.get(i).setCabeza(Domino.listaFichaPc.get(i).getCola());
                    Domino.listaFichaPc.get(i).setCola(z);
                    Domino.listaFichaPc = mst.addFichaTablero(i, false, Domino.listaFichaPc);//Añade Ficha al listaTablero
                    break;
                } else if (Domino.listaFichaPc.get(i).getCabeza() == Domino.listaTablero.get(Domino.listaTablero.size() - 1).getCabeza()) {
                    int z = Domino.listaFichaPc.get(i).getCabeza();
                    Domino.listaFichaPc.get(i).setCabeza(Domino.listaFichaPc.get(i).getCola());
                    Domino.listaFichaPc.get(i).setCola(z);
                    Domino.listaFichaPc = mst.addFichaTablero(i, true, Domino.listaFichaPc);//Añade Ficha al listaTablero
                    break;
                } else if (Domino.listaFichaPc.get(i).getCola() == Domino.listaTablero.get(Domino.listaTablero.size() - 1).getCabeza()) {
                    Domino.listaFichaPc = mst.addFichaTablero(i, true, Domino.listaFichaPc);//Añade Ficha al listaTablero
                    break;
                }
            }
            if (tlt == Domino.listaTablero.size()) {
                Domino.robarFicha(false);
            } else {
                break;
            }
        }
        if (Domino.listaFichaPc.size() == 0) {
            JOptionPane.showMessageDialog(null, "Gano PC");
        }
    }

    /**
     * @autor brayan restrepo la prioridad de cada ficha es la cantidad de
     * puntos de la ficha
     * @vercion 1.0 05/11/2014
     */
    public void heuristica1() {
        int tlt = Domino.listaTablero.size();
        while (true) {
            int prioridadMayor = -1;
            int IMayor = -1;
            int cola = Domino.listaTablero.get(0).getCola();
            int cabeza = Domino.listaTablero.get(tlt - 1).getCabeza();
            for (int i = 0; i < Domino.listaFichaPc.size(); i++) {
                if (Domino.listaFichaPc.get(i).getCabeza() == cabeza || Domino.listaFichaPc.get(i).getCola() == cabeza
                        || Domino.listaFichaPc.get(i).getCabeza() == cola || Domino.listaFichaPc.get(i).getCola() == cola) {
                    if (Domino.listaFichaPc.get(i).getCabeza()==Domino.listaFichaPc.get(i).getCola()) {
                        Domino.listaFichaPc.get(i).setPrioridad(Domino.listaFichaPc.get(i).getCabeza() + Domino.listaFichaPc.get(i).getCola()+10);//Si es par tiene una prioridad de +10
                    }else{
                        Domino.listaFichaPc.get(i).setPrioridad(Domino.listaFichaPc.get(i).getCabeza() + Domino.listaFichaPc.get(i).getCola());
                    }
                    if (prioridadMayor < Domino.listaFichaPc.get(i).getPrioridad()) {
                        prioridadMayor = Domino.listaFichaPc.get(i).getPrioridad();
                        IMayor = i;
                    }
                } else {
                    Domino.listaFichaPc.get(i).setPrioridad(-1);
                }
                if (Domino.listaFichaPc.size() - 1 == i) {
                    System.out.println("-------" + prioridadMayor);
                }
            }
            System.out.println("Lista PC");
            Domino.imprimirLista(Domino.listaFichaPc);
            
            if(IMayor == -1){
                Domino.robarFicha(false);
                continue;
            }else if (Domino.listaFichaPc.get(IMayor).getCabeza() == Domino.listaTablero.get(0).getCola()) {
                    Domino.listaFichaPc = mst.addFichaTablero(IMayor, false, Domino.listaFichaPc);//Añade Ficha al listaTablero
                    break;
                } else if (Domino.listaFichaPc.get(IMayor).getCola() == Domino.listaTablero.get(0).getCola()) {
                    int z = Domino.listaFichaPc.get(IMayor).getCabeza();
                    Domino.listaFichaPc.get(IMayor).setCabeza(Domino.listaFichaPc.get(IMayor).getCola());
                    Domino.listaFichaPc.get(IMayor).setCola(z);
                    Domino.listaFichaPc = mst.addFichaTablero(IMayor, false, Domino.listaFichaPc);//Añade Ficha al listaTablero
                    break;
                } else if (Domino.listaFichaPc.get(IMayor).getCabeza() == Domino.listaTablero.get(Domino.listaTablero.size() - 1).getCabeza()) {
                    int z = Domino.listaFichaPc.get(IMayor).getCabeza();
                    Domino.listaFichaPc.get(IMayor).setCabeza(Domino.listaFichaPc.get(IMayor).getCola());
                    Domino.listaFichaPc.get(IMayor).setCola(z);
                    Domino.listaFichaPc = mst.addFichaTablero(IMayor, true, Domino.listaFichaPc);//Añade Ficha al listaTablero
                    break;
                } else if (Domino.listaFichaPc.get(IMayor).getCola() == Domino.listaTablero.get(Domino.listaTablero.size() - 1).getCabeza()) {
                    Domino.listaFichaPc = mst.addFichaTablero(IMayor, true, Domino.listaFichaPc);//Añade Ficha al listaTablero
                    break;
                }
            
            
            
        }
    }

    public void ponerFichaAdelante() {

    }

    public void ponerFichaAtras() {

    }

}
