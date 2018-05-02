package parchisshrek;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author eduar
 */
public class FichaVista extends JLabel implements MouseListener{

    private int x;
    private int y;
    private final int color;
    private int posicion;
    private final String id;
    private boolean haSalido,haLlegado,puedeVuelta,haEntradoColor;
    Vista v;

    /**
     * constructor fichavista
     * @param id
     * @param color
     * @param x
     * @param y
     * @param v 
     */
    public FichaVista(String id,int color,int x,int y,Vista v) {
        this.id=id;
        this.color=color;
        this.x=x;
        this.y=y;
        this.v=v;
        this.posicion=-1;
        this.haSalido=false;
        this.haLlegado=false;
        aniadirListener();
        setNoSeleccionada();
    }
    
    /**
     * aniadir Listener
     */
    private void aniadirListener(){
        this.addMouseListener(this);
    }
    
    /*
    seleccionar ficha
    */
    public void setSeleccionada(){
        URL url;
        ImageIcon imagen;
        switch(color){
            case 0:
                url = this.getClass().getResource("/assets/verde2.png");
                imagen = new ImageIcon(url);
                this.setIcon(imagen);
                this.setBounds(this.x,this.y,30,30);
                break;
            case 1:
                url = this.getClass().getResource("/assets/amarillo2.png");
                imagen = new ImageIcon(url);
                this.setIcon(imagen);
                this.setBounds(this.x,this.y,30,30);
                break;
            case 2:
                url = this.getClass().getResource("/assets/azul2.png");
                imagen = new ImageIcon(url);
                this.setIcon(imagen);
                this.setBounds(this.x,this.y,30,30);
                break;
            case 3:
                url = this.getClass().getResource("/assets/rojo2.png");
                imagen = new ImageIcon(url);
                this.setIcon(imagen);
                this.setBounds(this.x,this.y,30,30);
                break;
            default:
                break;
        }
    }
    
    /*
    deseleccionar ficha
    */
    public final void setNoSeleccionada(){
        URL url;
        ImageIcon imagen;
        switch(color){
            case 0:
                url = this.getClass().getResource("/assets/verde.png");
                imagen = new ImageIcon(url);
                this.setIcon(imagen);
                this.setBounds(this.x,this.y,22,22);
                break;
            case 1:
                url = this.getClass().getResource("/assets/amarillo.png");
                imagen = new ImageIcon(url);
                this.setIcon(imagen);
                this.setBounds(this.x,this.y,22,22);
                break;
            case 2:
                url = this.getClass().getResource("/assets/azul.png");
                imagen = new ImageIcon(url);
                this.setIcon(imagen);
                this.setBounds(this.x,this.y,22,22);
                break;
            case 3:
                url = this.getClass().getResource("/assets/rojo.png");
                imagen = new ImageIcon(url);
                this.setIcon(imagen);
                this.setBounds(this.x,this.y,22,22);
                break;
            default:
                break;
        }
    }
    
    /**
     * seleccionar al pulsar ficha
     * @param me 
     */
    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getClickCount() == 2 && !me.isConsumed()) {
            me.consume();
            v.setPulsada(id);
            v.moverFicha();
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
    
    /*
     * Getters and setters
     */
    
    public boolean isHaSalido() {
        return haSalido;
    }
    
    public void setHaSalido(boolean haSalido) {
        this.haSalido = haSalido;
    }
    
    public boolean isHaLlegado() {
        return haLlegado;
    }

    public void setHaLlegado(boolean haLlegado) {
        this.removeMouseListener(this);
        this.haLlegado = haLlegado;
    }
    
    public void setPuedeVuelta(boolean puedeVuelta) {
        this.puedeVuelta = puedeVuelta;
    }

    public boolean isPuedeVuelta() {
        return puedeVuelta;
    }
    
    public void setHaEntradoColor(boolean haEntradoColor) {
        this.haEntradoColor = haEntradoColor;
    }

    public boolean isHaEntradoColor() {
        return haEntradoColor;
    }
    
    public void setX(int x) {
        this.x=x;
    }
    
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getX(){
        return x;
    }
    
    @Override
    public int getY(){
        return y;
    }
    
    public void setPosicion(int posicion) {
        this.posicion =posicion;
    }
    
    public int getPosicion(){
        return posicion;
    }
    
    public int getColor(){
        return color;
    }
    
    public String getId(){
        return id;
    }
}