package parchisshrek;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author eduar
 */
public class Vista extends JFrame{
    public Controlador c;
    private TableroVista tv;
    private BarraMenu barraMenu;
    private DadoVista dv;
    private JLabel turno,accion; 
    private final ArrayList<FichaVista> fv=new ArrayList();
    private String pulsada="";
    private JButton moverFicha,saltarTurno;
    
    /*
    constructor vista
    */
    Vista(Controlador c)  {
        this.c=c;
        //propiedades ventana
        this.setBounds(0, 0, 850, 720);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //creamos la interfaz inical
        interfaz();
    }
    
    /**
     * crear interfaz
     */
    public final void interfaz() { 
        //aniado MenuBar
        barraMenu=new BarraMenu(this);
        this.setJMenuBar(barraMenu);
        
        //aniado fichas
        colocarFichas();
        
        //aniado dado
        dv=new DadoVista(this);
        dv.setBounds(700, 40, 93, 93);
        c.setDadoVista(dv);
        this.add(dv);
        
        //aniado turno
        turno=new JLabel();
        turno.setText("Turno Shrek");
        turno.setBounds(710,120,100,50);
        this.add(turno);
        
        //aniado accion
        accion=new JLabel();
        accion.setText("Tirar Dado");
        accion.setBounds(680,150,200,50);
        this.add(accion);
        
        //aniado boton saltar turno
        saltarTurno=new JButton("Saltar Turno");
        saltarTurno.setBounds(680,300,150,50);
        saltarTurno.addActionListener((ActionEvent e) -> {
            if(!c.juegoTerminado()){
                c.tablero.nuevoTurno();
                setTurno(c.tablero.getTurno());
                c.v.setTableroVista(c.tablero.getTurno());
                c.v.agitarDado();
                c.setTocaMover(false);
                setAccion("Tirar dado");
            }
        });
        this.add(saltarTurno);

        //aniado vista tablero
        tv=new TableroVista();
        tv.setBounds(0, 0, 672, 672);
        this.add(tv);
        
        this.setVisible(true);
        this.requestFocus();//devuelve el foco al jframe
    }
    
    /*
    metodo colocar fichas en posicion inicial
    */
    public void colocarFichas(){
        int x=0,y=0,color=0;
        String id="";
        for(int i=0;i<4;i++){
            switch(i){
                case 0:
                    x=12;
                    break;
                case 1:
                    x=200;
                    break;
                case 2:
                    x=450;
                    break;
                case 3:
                    x=638;
                    break;
                default:
                    break;
            }
            for(int j=0;j<4;j++){
                switch(j){
                    case 0:
                        y=16;
                        if(x<=200){
                            color=3;
                            if(x==12)id="a1";
                            else id="a2";
                        }else{
                            color=2;
                            if(x==450)id="g1";
                            else id="g2";
                        }
                        break;
                    case 1:
                        y=200;
                        if(x<=200){
                            color=3;
                            if(x==12)id="a3";
                            else id="a4";
                        }else{
                            color=2;
                            if(x==450)id="g3";
                            else id="g4";
                        }
                        break;
                    case 2:
                        y=450;
                        if(x<=200){
                            color=0;
                            if(x==12)id="s1";
                            else id="s2";
                        }else{
                            color=1;
                            if(x==450)id="f1";
                            else id="f2";
                        }
                        break;
                    case 3:
                        y=635;
                        if(x<=200){
                            color=0;
                            if(x==12)id="s3";
                            else id="s4";
                        }else{
                            color=1;
                            if(x==450)id="f3";
                            else id="f4";
                        }
                        break;
                    default:
                        break;
                }
                fv.add(new FichaVista(id,color,x,y,this));
                fv.get(fv.size()-1).setBounds(fv.get(fv.size()-1).getX(),fv.get(fv.size()-1).getY(),22,22);
                this.add(fv.get(fv.size()-1));
            }
        }   
        c.setFichas(fv);
    }
    
    /**
     * desmarcar todas las fichas
     */
    public void desmarcar(){
        for(int i=0;i<fv.size();i++)fv.get(i).setNoSeleccionada();
    }
    
    /**
     * marcar todas las fichas de un jugador
     * @param turno
     */
    public void marcarColor(int turno){
        for(int i=0;i<fv.size();i++){
            if(fv.get(i).getColor()==turno&&fv.get(i).isHaSalido())
                fv.get(i).setSeleccionada();
        }
    }
    
    /**
     * colocar turno en su label
     * @param n 
     */
    public void setTurno(int n){
        switch(n){
            case 0:
                turno.setText("Turno Shrek");
                break;
            case 1:
                turno.setText("Turno Fiona");
                break;
            case 2:
                turno.setText("Turno Gato");
                break;
            case 3:
                turno.setText("Turno Asno");
                break;
            default:
                break;
        }
    }
    
    /**
     * mover ficha
     */
    public void moverFicha() {
        if(c.getTocaMover()){
            for(int i=0;i<fv.size();i++){
                if(fv.get(i).getId().equals(pulsada)){
                    c.intentarMover(i);
                }
            }
        }
    }
    
    /*
    colocar accion en su label
    */
    public void setAccion(String f){
        accion.setText(f);
    }
    /*
    parar dado
    */
    public void pararDado(){
        dv.setSacudir(false);
    }
    /*
    agitar dado
    */
    public void agitarDado(){
        dv.setSacudir(true);
        dv.setCont(0);
    }
    /*
    set tablero vista
    */
    public void setTableroVista(int turno) {
        tv.changeTablero(turno);
    }
    
    /*
    getters and setters
    */
    public String getPulsada() {
        return pulsada;
    }

    public  void setPulsada(String id) {
        this.pulsada=id;
    }

    public void takePhoto() throws UnsupportedAudioFileException, LineUnavailableException {
        try {
            
            try {
             // Open an audio input stream.           
              File soundFile = new File("camara.wav"); //you could also get the sound file with an URL
              AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);              
             // Get a sound clip resource.
             Clip clip = AudioSystem.getClip();
             // Open audio clip and load samples from the audio input stream.
             clip.open(audioIn);
             clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {}

            BufferedImage img = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
            this.paint(img.getGraphics());
            File outputfile = new File("photo.png");
            ImageIO.write(img, "png", outputfile);
        } catch (IOException ex) {
            Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
