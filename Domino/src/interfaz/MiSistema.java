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
 * @web http://www.jc-mouse.net/
 * @author Mouse
 */
public class MiSistema {

    /** Los objetos se almacenaran en un MAP */
//    private Map map = new HashMap();   
    /** JPanel */
    
    /** Constructor de clase 
     * @param JPanel Donde se colocaran los objetos
     * @param DefaultListModel Aqui se almacenaran los key (identificadores) de objetos
     */
    
    
    
    
    /**
     * Metodo que crea un nuevo objeto
     */
    public void colocarFichaHumano(){
        Principal.tablero.removeAll();
        Principal.jSeparator1.setLocation(0, 800);
        Principal.tablero.add(Principal.jSeparator1);
        int k=10;
        for (int i = 0; i < Domino.listaFichaHumano.size(); i++) {
            //coloca al objeto creado en una posicion
//            Domino.listaFichaHumano.get(i).setLocation( k, 850 );
//            k+=90;
            Domino.listaFichaHumano.get(i).posiocion(k, 850);
            Principal.tablero.add(Domino.listaFichaHumano.get(i));
            k+=90;
        }        
        //actualiza graficos
        Principal.tablero.repaint();
    }
    
    
    /**
     * Busca el Boton en la lista 
     * @param nambre
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
     *Añande una ficha a la lista tablero en la pocicion N 
     * lo elimina del tablero (JPanel)
     * la añade a la Lista listaTablero, y finalmente lo elimina de listaHumano
     * @param n 
     */
    public void addFichaTablero(int n,boolean fin){
        Principal.tablero.remove(Domino.listaFichaHumano.get(n));
        if(fin){
            int k;
            if(Domino.listaTablero.size()==0){
                k=0;
            }else{
                k= Domino.listaTablero.get(Domino.listaTablero.size()-1).getNivel()+1;
                System.out.println("------> "+k);
            }
            Domino.listaTablero.add(Domino.listaFichaHumano.get(n));
            Domino.listaTablero.get(Domino.listaTablero.size()-1).setNivel(k);
        }else{
            int k;
            if(Domino.listaTablero.size()==0){
                k=0;
            }else{
                k = Domino.listaTablero.get(0).getNivel()-1;
            }
            Domino.listaTablero.add(0,Domino.listaFichaHumano.get(n));
            Domino.listaTablero.get(0).setNivel(k);
        }
            
        Domino.listaFichaHumano.remove(n);
        Principal.tablero.repaint();
    }
    public void colocarFichaTablero(){
        int centro = 1000+(90*Domino.listaTablero.get(0).getNivel());
        for (int i = 0; i < Domino.listaTablero.size(); i++) {
            Principal.tablero.remove(Domino.listaTablero.get(i));
            int cabeza = Domino.listaTablero.get(i).getCabeza();
            int cola = Domino.listaTablero.get(i).getCola();
            Domino.listaTablero.get(i).imagenBotonHorizontal(cabeza, cola);
            Domino.listaTablero.get(i).posiocion(centro, 500);
            Principal.tablero.add(Domino.listaTablero.get(i));
            centro+=90;
            System.out.println("NIVEL  :   "+Domino.listaTablero.get(i).getNivel());
        }
    }
    
    /**
     * Metodo que cambia la imagen actual de un objeto por otra imagen aleatoria
     * @param String Key identificador de objeto
     */
    /*
    public void Cambiar_Imagen( String key )
    {
        MiObjeto bF = (MiObjeto) map.get( key );
        bF.setIcon( new ImageIcon(getClass().getResource("/org/bolivia/app/res/" + ((int) Math.floor(Math.random()*4+1))+ ".png")) ); 
        bF.repaint();
    }
    */
     
    
}//->fin de clase
