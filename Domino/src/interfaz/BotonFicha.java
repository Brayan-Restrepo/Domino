/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import domino.Domino;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Brayan
 *
 * @version 1.0 23/10/2014
 */
public class BotonFicha extends JButton implements MouseListener, MouseMotionListener {

    /**
     * Posicion de imagen
     */
    protected Point posicion = new Point(10, 850);
    /**
     * Tamaño de imagen
     */
    private Dimension d = new Dimension(90, 150);
    /**
     * variable que sirve para calcular el movimiento del objeto
     */
    private Point start_loc;
    /**
     * variable que sirve para calcular el movimiento del objeto
     */
    private Point start_drag;
    /**
     * variable que sirve para calcular el movimiento del objeto
     */
    private Point offset;
    /**
     * variables auxiliares para el desplazamiento del objeto
     */
    private int nuevo_X = 1;
    private int nuevo_Y = 1;

    //------------------
    private int x = 10;
    private int y = 850;
    private int cola;
    private int cabeza;
    private String rutaFicha;
    private MiSistema mst = new MiSistema();
    private int nivel;

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    /**
     *
     * @param key
     */
    public BotonFicha(int cabeza, int cola) {
        this.rutaFicha = "/Imagenes/" + cabeza + "-" + cola + "(v).jpg";
        this.nivel = 0;
        this.cola = cola;
        this.cabeza = cabeza;
        //se inician propiedades de objeto
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setSize(d);
        this.setPreferredSize(d);
        this.setIcon(new ImageIcon(getClass().getResource(this.rutaFicha)));
        this.setText("");
        this.setVisible(true);
        this.setLocation(posicion);
        //se agregan los listener
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

    }

    //-----------------
    public void imagenBotonHorizontal(int cola, int cabeza) {
        this.rutaFicha = "/Imagenes/" + cola + "-" + cabeza + "(h).jpg";
        this.setIcon(new ImageIcon(getClass().getResource(this.rutaFicha)));
    }

    public void posiocion(int x, int y) {
        this.x = x;
        this.y = y;
        this.posicion = new Point(x, y);
        super.setLocation(this.posicion);
    }
//    public void posiocion(){
//        this.posicion = new Point(this.x,this.y);
//        this.setLocation( this.posicion );        
//    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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

    //--------------
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.start_drag = getScreenLocation(e);
        this.start_loc = this.getLocation();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        nuevo_X = (this.getLocation().x);
        nuevo_Y = (this.getLocation().y);
        int k = mst.buscarBoton(this.getName(), Domino.listaFichaHumano);
        if (Domino.listaTablero.size() != 0) {

            System.out.println("Lista Cola cabeza :" + Domino.listaTablero.get(0).getCabeza() + " cola " + Domino.listaTablero.get(0).getCola());
        }
        if (MiSistema.turno == 1) {

            if (Domino.listaTablero.size() == 0) {//Si no han puesto Fichas
                if (this.cabeza == MiSistema.parSalida && MiSistema.parSalida == this.cola) {
                    mst.addFichaTableroHumano(k, true);//Añade Ficha al listaTablero
                }
            } else if (nuevo_Y < 800) {//Poner Ficha

                if (nuevo_X < 1000) {//Pone la Ficha cola
                    if (this.cola == Domino.listaTablero.get(0).getCola()) {
                        int z = this.cola;
                        this.cola = this.cabeza;
                        this.cabeza = z;
                        mst.addFichaTableroHumano(k, false);//Añade Ficha al listaTablero
                    } else if (this.cabeza == Domino.listaTablero.get(0).getCola()) {
                        mst.addFichaTableroHumano(k, false);//Añade Ficha al listaTablero
                    }
                } else {//Pone la ficha en Cabeza 
                    if (this.cola == Domino.listaTablero.get(Domino.listaTablero.size() - 1).getCabeza()) {

                        mst.addFichaTableroHumano(k, true);//Añade Ficha al listaTablero
                    } else if (this.cabeza == Domino.listaTablero.get(Domino.listaTablero.size() - 1).getCabeza()) {
                        int z= this.cabeza;
                        this.cabeza = this.cola;
                        this.cola = z;
                        mst.addFichaTableroHumano(k, true);//Añade Ficha al listaTablero
                    }
                }
            }
        }
        mst.colocarFichaHumano();
        if (Domino.listaTablero.size() != 0) {
            mst.colocarFichaTablero();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setBorder(BorderFactory.createLineBorder(new java.awt.Color(204, 0, 51), 1));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setBorder(null);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point current = this.getScreenLocation(e);
        offset = new Point((int) current.getX() - (int) start_drag.getX(), (int) current.getY() - (int) start_drag.getY());

        this.x = (int) (this.start_loc.getX() + offset.getX());
        this.y = (int) (this.start_loc.getY() + offset.getY());
        this.posiocion(x, y);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    /**
     * metodo para obtener la posicion del frame en la pantalla
     *
     * @param MouseEvent evt
     */
    private Point getScreenLocation(MouseEvent evt) {
        Point cursor = evt.getPoint();
        Point target_location = this.getLocationOnScreen();
        return new Point((int) (target_location.getX() + cursor.getX()),
                (int) (target_location.getY() + cursor.getY()));
    }

}
