/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino;

import interfaz.MiSistema;
import javax.swing.JOptionPane;

/**
 *
 * @author Brayan
 */
public class Reglas {

    public MiSistema mst = new MiSistema();

    public void jugar() {
        System.out.println("Jugar turno " + MiSistema.turno);
        if (MiSistema.turno == 0) {
            if (Domino.listaTablero.size() == 0) {//De de tirar un par
                this.empezarDoble();
            } else {
                this.ponerprimerFicha();
            }

        }
        if (Domino.listaTablero.size() != 0) {
            mst.colocarFichaTablero();
        }
        MiSistema.turno = 1;
        System.out.println("Salida Jugar " + MiSistema.turno);
    }

    /**
     * @autor Brayan Restrepo SE EJECUTA UNA VEZ, para poner ficha PAR
     * @version 1.0 02/11/2014
     */
    public void empezarDoble() {
        for (int i = 0; i < Domino.listaFichaPc.size(); i++) {
            if (Domino.listaFichaPc.get(i).getCabeza() == MiSistema.parSalida && Domino.listaFichaPc.get(i).getCola() == MiSistema.parSalida) {
                Domino.listaFichaPc = mst.addFichaTablero(i, true, Domino.listaFichaPc);//Añade Ficha al listaTablero
                System.out.println("Poniendo Ficha par...");
                break;
            }
        }
    }

    /**
     * @autor Brayan Restrepo pone la primera ficha de la lista en el tablero en
     * el tablero
     * @version 1.0 01/11/2014
     */
    public void ponerprimerFicha() {
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
            if(tlt==Domino.listaTablero.size()){
                Domino.robarFicha(false);
            }else{
                break;
            }
        }
        if (Domino.listaFichaPc.size()==0) {
            JOptionPane.showMessageDialog(null, "Gano PC");
        }
    }

    public void ponerFichaAdelante() {

    }

    public void ponerFichaAtras() {

    }

}
