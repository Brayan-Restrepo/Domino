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
    
    public void buscarBoton(String nambre,ArrayList<BotonFicha> lista){
        int i=-1;
        do {
            i++;
            System.out.println("NOMBRE BOTON "+lista.get(i).getName()   );
        } while (!lista.get(i).getName().equals(nambre));
        Principal.tablero.remove(Domino.listaFichaHumano.get(i));
        Domino.listaFichaHumano.remove(i);
        Principal.tablero.repaint();
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
