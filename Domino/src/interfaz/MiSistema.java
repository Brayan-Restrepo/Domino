package interfaz;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 * @web http://www.jc-mouse.net/
 * @author Mouse
 */
public class MiSistema {

    /** Los objetos se almacenaran en un MAP */
    private Map map = new HashMap();   
    /** JPanel */
    private JPanel contenedor;
    
    /** Constructor de clase 
     * @param JPanel Donde se colocaran los objetos
     * @param DefaultListModel Aqui se almacenaran los key (identificadores) de objetos
     */
    public MiSistema(JPanel jpanel){
        this.contenedor = jpanel;
    }
    
    /**
     * Metodo que crea un nuevo objeto
     */
    public void Nuevo_Objeto()
    {
       
        BotonFicha tmp = new BotonFicha( "1-5");
        //coloca al objeto creado en una posicion aleatoria
        tmp.setLocation( rndNum( this.contenedor.getWidth() - tmp.getWidth() )  , rndNum(this.contenedor.getHeight()-tmp.getHeight() ) );        
        //agrega el objeto en el MAP
        map.put("1+5", tmp );        
        //agrega el objeto en el JPanel
        this.contenedor.add(tmp);
        //actualiza graficos
        this.contenedor.repaint();
    }
    
    /**
     * Metodo que cambia la imagen actual de un objeto por otra imagen aleatoria
     * @param String Key identificador de objeto
     */
    /*
    public void Cambiar_Imagen( String key )
    {
        MiObjeto tmp = (MiObjeto) map.get( key );
        tmp.setIcon( new ImageIcon(getClass().getResource("/org/bolivia/app/res/" + ((int) Math.floor(Math.random()*4+1))+ ".png")) ); 
        tmp.repaint();
    }
    */
     
    /**
     * Metodo que cambia todas las imagenes de todos los objetos que existan en el MAP
     */
    /*public void Cambiar_Imagen_all()
    {
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            Cambiar_Imagen( e.getKey().toString() );            
        }
    }
    */
    /**
     * Retorna un numero entero aleatorio entre 0 y un numero pasado como parametro
     * @param int numero entero
     */
    public int rndNum( int value ){
        int num = (int) Math.floor(Math.random()*value+1);            
        return num;
    }
    
}//->fin de clase
