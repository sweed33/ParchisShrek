package parchisshrek;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author eduar
 */
public class DadoVista extends JLabel implements MouseListener,ActionListener{
    private final Vista v;
    private int cont;

    public boolean isSacudir() {
        return sacudir;
    }
    private boolean sacudir=true;
    /**
     * constructor DadoVista
     * @param v 
     */
    public DadoVista(Vista v){
        this.v=v;
        URL url= this.getClass().getResource("/assets/1.png");
        ImageIcon imagen= new ImageIcon(url);
        this.setIcon(imagen);
        aniadirListener();
        new Timer(100,this).start();
    }

    
    
    /**
     * aniadir Listener
     */
    private void aniadirListener(){
        this.addMouseListener(this);
    }
    
    /**
     * si en el turno toca mover, tira dado
     * @param me 
     */
    @Override
    public void mouseClicked(MouseEvent me) {
        if(!v.c.getTocaMover())
            v.c.tirarDado();
    }
    
    /**
     * cambiar cara dado según numero aleatorio
     * @param cara 
     */
    public void changeCara(int cara){
        URL url;
        ImageIcon imagen;
        switch(cara){
            case 0:
                url= this.getClass().getResource("/assets/1.png");
                imagen= new ImageIcon(url);
                this.setIcon(imagen);
                break;
            case 1:
                url= this.getClass().getResource("/assets/2.png");
                imagen= new ImageIcon(url);
                this.setIcon(imagen);
                break;
            case 2:
                url= this.getClass().getResource("/assets/3.png");
                imagen= new ImageIcon(url);
                this.setIcon(imagen);
                break;
            case 3:
                url= this.getClass().getResource("/assets/4.png");
                imagen= new ImageIcon(url);
                this.setIcon(imagen);
                break;
            case 4:
                url= this.getClass().getResource("/assets/5.png");
                imagen= new ImageIcon(url);
                this.setIcon(imagen);
                break;
            case 5:
                url= this.getClass().getResource("/assets/6.png");
                imagen= new ImageIcon(url);
                this.setIcon(imagen);
                break;
            default:
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {}
    @Override
    public void mouseReleased(MouseEvent me) {}
    @Override
    public void mouseEntered(MouseEvent me) {}
    @Override
    public void mouseExited(MouseEvent me) {}

    @Override
    public void actionPerformed(ActionEvent ae) {
        cont++;
        if(sacudir&&cont>10){
            changeCara((int)Math.floor(Math.random()*6));
        }
    }
    /**
     * setters
     * @param sacudir 
     */
    public void setSacudir(boolean sacudir) {
        this.sacudir = sacudir;
    }

    public void setCont(int i) {
        cont=i;
    }
}
