package parchisshrek;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author eduar
 */
public class TableroVista extends JLabel{

    /**
     * constructor TableroVista
     */
    public TableroVista() {
        URL url= this.getClass().getResource("/assets/tablero.png");
        ImageIcon imagen= new ImageIcon(url);
        this.setIcon(imagen);
    }
    public void changeTablero(int turno){
        URL url;
        ImageIcon imagen;
        switch(turno){
            case 0:
                url=this.getClass().getResource("/assets/tablero.png");
                imagen= new ImageIcon(url);
                this.setIcon(imagen);
                break;
            case 1:
                url=this.getClass().getResource("/assets/tablero1.png");
                imagen= new ImageIcon(url);
                this.setIcon(imagen);
                break;
            case 2:
                url=this.getClass().getResource("/assets/tablero2.png");
                imagen= new ImageIcon(url);
                this.setIcon(imagen);
                break;
            case 3:
                url=this.getClass().getResource("/assets/tablero3.png");
                imagen= new ImageIcon(url);
                this.setIcon(imagen);
                break;
            default:
                break;
        }
    }
}