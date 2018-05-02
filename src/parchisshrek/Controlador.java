package parchisshrek;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;
/**
 *
 * @author eduar
 */
public class Controlador implements ActionListener{
    public final Vista v;
    private ArrayList<FichaVista> fv;
    private final Dado dado;
    private DadoVista dv;
    public Tablero tablero;
    private int res,nuevaPos,posini,lado,ficha,contadorseis=0;
    private boolean tocaMover=false,acabaDeComer=false,acabaDeLlegar=false,moving=false,acabaPasarAUno=false,haPasadoEnMovimiento,haEntradoColor,primeraEntrada;
    private String nombreFicha="";
    /*
    constructor controlador
    */
    public Controlador(){
        v=new Vista(this);
        dado=new Dado();
        tablero=new Tablero(this);
        Timer timer=new Timer(100,this);
        timer.start();
    }
    
    //por ahora no hace falta
    @Override
    public void actionPerformed(ActionEvent ae) {
        animar();
    }
    
    /*
    metodo tirar dado
    */
    public void tirarDado()  {
        v.pararDado();
        if(!juegoTerminado()){
            res=dado.tirar();
            dv.changeCara(res);
            
            if(res==5)contadorseis++;
            else contadorseis=0;
            
            tocaMover=true;
            v.setAccion("Elegir Ficha y Mover");
            
            if(res==5){//si sale 6
                //si no hay fichas en nuestra casa pasa a valer 7 la tirada del dado
                switch(tablero.getTurno()){
                    case 0:
                        if(tablero.getS()==0)res=6;
                        if(tablero.getS()==4){
                            v.setAccion("Tira otra vez");
                            tocaMover=false;
                            v.agitarDado();
                        }
                        break;
                    case 1:
                        if(tablero.getF()==0)res=6;
                        if(tablero.getF()==4){
                            v.setAccion("Tira otra vez");
                            tocaMover=false;
                            v.agitarDado();
                        }
                        break;
                    case 2:
                        if(tablero.getG()==0)res=6;
                        if(tablero.getG()==4){
                            v.setAccion("Tira otra vez");
                            tocaMover=false;
                            v.agitarDado();
                        }
                        break;
                    case 3:
                        if(tablero.getA()==0)res=6;
                        if(tablero.getA()==4){
                            v.setAccion("Tira otra vez");
                            tocaMover=false;
                            v.agitarDado();
                        }
                        break;
                    default:
                        break;
                }
            }
            
            boolean haPodidoSalir=false;
            if(res==4) haPodidoSalir=sacarFichaSiNoHaSalido(tablero.getTurno());
            if(haPodidoSalir){//si ha sacado un 5 y ha podido salir
                if(!acabaDeComer){
                    tablero.nuevoTurno();
                    v.setTurno(tablero.getTurno());
                    v.setTableroVista(tablero.getTurno());
                    tocaMover=false;
                    v.agitarDado();
                    v.setAccion("Tirar dado");
                }else if(acabaDeComer){
                    res=19;
                }
            }else if(contadorseis==0){
                switch(tablero.getTurno()){
                    case 0:
                        if(tablero.getS()==4){
                            tablero.nuevoTurno();
                            v.setTurno(tablero.getTurno());
                            v.setTableroVista(tablero.getTurno());
                            tocaMover=false;
                            v.agitarDado();
                            v.setAccion("Tirar dado");
                        }
                        break;
                    case 1:
                        if(tablero.getF()==4){
                            tablero.nuevoTurno();
                            v.setTurno(tablero.getTurno());
                            v.setTableroVista(tablero.getTurno());
                            tocaMover=false;
                            v.agitarDado();
                            v.setAccion("Tirar dado");
                        }
                        break;
                    case 2:
                        if(tablero.getG()==4){
                            tablero.nuevoTurno();
                            v.setTurno(tablero.getTurno());
                            v.setTableroVista(tablero.getTurno());
                            tocaMover=false;
                            v.agitarDado();
                            v.setAccion("Tirar dado");
                        }
                        break;
                    case 3:
                        if(tablero.getA()==4){
                            tablero.nuevoTurno();
                            v.setTurno(tablero.getTurno());
                            v.setTableroVista(tablero.getTurno());
                            tocaMover=false;
                            v.agitarDado();
                            v.setAccion("Tirar dado");
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    /**
     *animacion  mover fichas
     */
    private void animar() {
        if(moving){
            if(lado==0){
                fv.get(ficha).setX(tablero.getTab()[posini][0]);
                fv.get(ficha).setY(tablero.getTab()[posini][1]);
            }else{
                fv.get(ficha).setX(tablero.getTab()[posini][2]);
                fv.get(ficha).setY(tablero.getTab()[posini][3]);
            }
            fv.get(ficha).setBounds(fv.get(ficha).getX(),fv.get(ficha).getY(),30,30);
            if(!acabaPasarAUno&&!haEntradoColor){
                posini++;
                if(posini>nuevaPos)moving=false;
            }else if(haEntradoColor){
                posini++;
                switch(fv.get(ficha).getColor()){
                    case 0:
                        if(posini>50&&primeraEntrada){
                            posini=92;
                            primeraEntrada=false;
                        }else if(posini>nuevaPos){
                            moving=false;
                        }
                        break;
                    case 2:
                        if(posini>16&&primeraEntrada){
                            posini=76;
                            primeraEntrada=false;
                        }else if(posini>nuevaPos){
                            moving=false;
                        }
                        break;
                    case 3:
                        if(posini>33&&primeraEntrada){
                            posini=84;
                            primeraEntrada=false;
                        }else if(posini>nuevaPos){
                            moving=false;
                        }
                        break;
                    default:
                        break;
                }
            }else{
                posini++;
                if(posini>67&&!haPasadoEnMovimiento){
                    posini=0;
                    haPasadoEnMovimiento=true;
                }else if(haPasadoEnMovimiento&&posini>nuevaPos){
                    moving=false;
                }
            }
        }
    }
    
    /**
     * metodo intentar mover ficha
     * @param ficha 
     */
    public void intentarMover(int ficha){
        this.ficha=ficha;
        switch(tablero.getTurno()){
            case 0:
                if(fv.get(ficha).getId().contains("s")&&tablero.getS()<4&&fv.get(ficha).isHaSalido()&&!fv.get(ficha).isHaLlegado())mover(ficha,res);
                break;
            case 1:
                if(fv.get(ficha).getId().contains("f")&&tablero.getF()<4&&fv.get(ficha).isHaSalido()&&!fv.get(ficha).isHaLlegado())mover(ficha,res);
                break;
            case 2:
                if(fv.get(ficha).getId().contains("g")&&tablero.getG()<4&&fv.get(ficha).isHaSalido()&&!fv.get(ficha).isHaLlegado())mover(ficha,res);
                break;
            case 3:
                if(fv.get(ficha).getId().contains("a")&&tablero.getA()<4&&fv.get(ficha).isHaSalido()&&!fv.get(ficha).isHaLlegado())mover(ficha,res);
                break;
            default:
                break;
        }
    }
    
    /*
    metodo mover ficha
    */
    private void mover(int ficha,int num){
        acabaPasarAUno=false;
        haPasadoEnMovimiento=false;
        haEntradoColor=false;
        if(acabaDeComer){
            num=19;
            acabaDeComer=false;
        }
        if(acabaDeLlegar){
            num=9;
            acabaDeLlegar=false;
        }
        posini=fv.get(ficha).getPosicion()-1;
        nuevaPos=fv.get(ficha).getPosicion()+num;
        boolean sePasa=false;
        sePasa=comprobarSiSaleDelCircuito(ficha,num);
        if(!sePasa){
            boolean haLlegado=false;
            haLlegado=comprobarLlegada(ficha,num);
            if(!haLlegado){
                boolean estaEnCaminoColor=false;
                //si shrek entra 
                if(fv.get(ficha).getColor()==0&&fv.get(ficha).isPuedeVuelta()&&nuevaPos>50&&!fv.get(ficha).isHaEntradoColor()){
                    fv.get(ficha).setHaEntradoColor(true);
                    nuevaPos=nuevaPos-51+92;
                    estaEnCaminoColor=true;
                    haEntradoColor=true;
                    primeraEntrada=true;
                }
                //si gato entra 
                if(fv.get(ficha).getColor()==2&&fv.get(ficha).isPuedeVuelta()&&nuevaPos>16&&!fv.get(ficha).isHaEntradoColor()){
                    fv.get(ficha).setHaEntradoColor(true);
                    nuevaPos=nuevaPos-17+76;
                    estaEnCaminoColor=true;
                    haEntradoColor=true;
                    primeraEntrada=true;
                }
                //si asno entra 
                if(fv.get(ficha).getColor()==3&&fv.get(ficha).isPuedeVuelta()&&nuevaPos>33&&!fv.get(ficha).isHaEntradoColor()){
                    fv.get(ficha).setHaEntradoColor(true);
                    nuevaPos=nuevaPos-34+84;
                    estaEnCaminoColor=true;
                    haEntradoColor=true;
                    primeraEntrada=true;
                }
                
                //si pasan de la posicion 68 los ponemos a partir del 1
                if(nuevaPos>67&&fv.get(ficha).getColor()!=1&&!fv.get(ficha).isPuedeVuelta()){
                    System.out.println("pasa del 68");
                    nuevaPos=nuevaPos-68;
                    fv.get(ficha).setPuedeVuelta(true);
                    acabaPasarAUno=true;
                }
                System.out.println("Dado:"+(num+1)+" NuevaPos:"+(nuevaPos+1));
                //miro si hay alguna casilla por la que no pueda pasar
                boolean puedePasar;
                if(!estaEnCaminoColor){
                    puedePasar=puedePasarPorEsteRecorrido(ficha,nuevaPos);
                }else{
                    puedePasar=mirarRecorridoColor(ficha,nuevaPos);
                }
                
                if(puedePasar){
                    if(hayFicha(nuevaPos,0)&&!fichaDeTuColor(nuevaPos,fv.get(ficha).getColor(),0)){
                        if(!esPosSegura(nuevaPos)){
                            System.out.println("comer derecha");
                            for(int i=0;i<fv.size();i++){
                                if(fv.get(i).getId().equals(nombreFicha)){
                                    volverACasa(fv.get(i));
                                    break;
                                }
                            }
                            lado=0;
                        }else{
                            lado=1;
                        }
                        moving=true;
                        fv.get(ficha).setPosicion(nuevaPos+1);
                    }else if(hayFicha(nuevaPos,1)&&!fichaDeTuColor(nuevaPos,fv.get(ficha).getColor(),1)){
                        if(!esPosSegura(nuevaPos)){
                            System.out.println("comer izquierda");
                            for(int i=0;i<fv.size();i++){
                                if(fv.get(i).getId().equals(nombreFicha)){
                                    volverACasa(fv.get(i));
                                    break;
                                }
                            }
                            lado=1;
                        }else{
                            lado=0;
                        }
                        moving=true;
                        fv.get(ficha).setPosicion(nuevaPos+1);
                    }else if(hayFicha(nuevaPos,0)&&fichaDeTuColor(nuevaPos,fv.get(ficha).getColor(),0)){
                        System.out.println("hay ficha de tu color en derecha");
                        moving=true;
                        lado=1;
                        fv.get(ficha).setPosicion(nuevaPos+1);
                    }else if(hayFicha(nuevaPos,1)&&fichaDeTuColor(nuevaPos,fv.get(ficha).getColor(),1)){
                        System.out.println("hay ficha de tu color en izquierda");
                        moving=true;
                        lado=0;
                        fv.get(ficha).setPosicion(nuevaPos+1);
                    }else{
                        System.out.println("no habia ficha");
                        moving=true;
                        lado=0;
                        fv.get(ficha).setPosicion(nuevaPos+1);
                    }
                    if(!acabaDeComer&&contadorseis==0){
                        tablero.nuevoTurno();
                        v.setTurno(tablero.getTurno());
                        v.setTableroVista(tablero.getTurno());
                        tocaMover=false;
                        v.agitarDado();
                        v.setAccion("Tirar dado");
                    }else if(!acabaDeComer&&contadorseis>0){
                        tocaMover=false;
                        v.agitarDado();
                        v.setAccion("Tira otra vez");
                    }else{
                        v.setAccion("Elige ficha y mueve 20");
                    }
                }else{
                    System.out.println("no puede pasar");
                }
            }else{
                System.out.println("ficha llego al fin");
                colocarFichasFin(ficha,nuevaPos);
                if(juegoTerminado())v.setAccion("HAS GANADO");
                else{
                    acabaDeLlegar=true;
                    v.setAccion("Elige ficha y mueve 10");
                }
            }
        }else{
            System.out.println("se pasa del circuito");
            v.setAccion("No puede pasar");
        }
    }
    
    /**
     * sacar ficha de su casa
     * @param turno
     * @return 
     */
    private boolean sacarFichaSiNoHaSalido(int turno){
        boolean haSalido=false;
        switch(turno){
            case 0:
                if(!fv.get(2).isHaSalido())haSalido=salirVerde(2);
                if(!fv.get(3).isHaSalido()&&!haSalido)haSalido=salirVerde(3);
                if(!fv.get(6).isHaSalido()&&!haSalido)haSalido=salirVerde(6);
                if(!fv.get(7).isHaSalido()&&!haSalido) haSalido=salirVerde(7);
                return haSalido;
            case 1:
                if(!fv.get(10).isHaSalido())haSalido=salirAmarillo(10);
                else if(!fv.get(11).isHaSalido()&&!haSalido)haSalido=salirAmarillo(11);
                else if(!fv.get(14).isHaSalido()&&!haSalido)haSalido=salirAmarillo(14);
                else if(!fv.get(15).isHaSalido()&&!haSalido) haSalido=salirAmarillo(15);
                return haSalido;
            case 2:
                if(!fv.get(8).isHaSalido())haSalido=salirAzul(8);
                else if(!fv.get(9).isHaSalido()&&!haSalido)haSalido=salirAzul(9);
                else if(!fv.get(12).isHaSalido()&&!haSalido)haSalido=salirAzul(12);
                else if(!fv.get(13).isHaSalido()&&!haSalido)haSalido=salirAzul(13);
                return haSalido;
            case 3:
                if(!fv.get(0).isHaSalido())haSalido=salirRojo(0);
                else if(!fv.get(1).isHaSalido()&&!haSalido)haSalido=salirRojo(1);
                else if(!fv.get(4).isHaSalido()&&!haSalido)haSalido=salirRojo(4);
                else if(!fv.get(5).isHaSalido()&&!haSalido)haSalido=salirRojo(5);
                return haSalido;
        }
        return haSalido;
    }
   
    /**
     * retorna si hay ficha en xy
     * @param x
     * @param y
     * @return 
     */
    private boolean hayFichaSalida(int x,int y){
        boolean hayFicha=false;
        for(int i=0;i<fv.size();i++){
            if(fv.get(i).getX()==x&&fv.get(i).getY()==y){
                hayFicha=true;
                break;
            }
        }
        return hayFicha;
    }
    
    /**
     * retorna si hay ficha en una posicion segun el lado
     * @param pos
     * @param lado
     * @return 
     */
    private boolean hayFicha(int pos,int lado){
        boolean hayFicha=false;
        switch(lado){
            case 0://lado derecho
                for(int i=0;i<fv.size();i++){
                    if(fv.get(i).getX()==tablero.getTab()[pos][0]&&fv.get(i).getY()==tablero.getTab()[pos][1]){
                        hayFicha=true;
                        nombreFicha=fv.get(i).getId();
                        break;
                    }
                }
                break;
            case 1:
                for(int i=0;i<fv.size();i++){
                    if(fv.get(i).getX()==tablero.getTab()[pos][2]&&fv.get(i).getY()==tablero.getTab()[pos][3]){
                        hayFicha=true;
                        nombreFicha=fv.get(i).getId();
                        break;
                    }
                }
                break;
        }
        return hayFicha;
    }
    
    /**
     * retorna si hay ficha en una posicion del color de la que se va a mover segun el lado
     * @param pos
     * @param color
     * @param lado
     * @return 
     */
    private boolean fichaDeTuColor(int pos,int color,int lado){
        boolean deTuColor=false;
        switch(lado){
            case 0:
                for(int i=0;i<fv.size();i++){
                    if(fv.get(i).getX()==tablero.getTab()[pos][0]&&fv.get(i).getY()==tablero.getTab()[pos][1]&&fv.get(i).getColor()==color){
                        deTuColor=true;
                        break;
                    }
                }
                break;
            case 1:
                for(int i=0;i<fv.size();i++){
                    if(fv.get(i).getX()==tablero.getTab()[pos][2]&&fv.get(i).getY()==tablero.getTab()[pos][3]&&fv.get(i).getColor()==color){
                        deTuColor=true;
                        break;
                    }
                }
                break;
        }
        return deTuColor;
    }
    
    /**
     * retorna si puede pasar por una casilla o no
     * @param pos
     * @return 
     */
    private boolean puedePasar(int pos){
        boolean derecha=false;
        boolean izquierda=false;
        for(int i=0;i<fv.size();i++){
            if(fv.get(i).getX()==tablero.getTab()[pos][0]&&fv.get(i).getY()==tablero.getTab()[pos][1]){
                derecha=true;
                break;
            }
        }
        for(int i=0;i<fv.size();i++){
            if(fv.get(i).getX()==tablero.getTab()[pos][2]&&fv.get(i).getY()==tablero.getTab()[pos][3]){
                izquierda=true;
                break;
            }
        }
        if(izquierda&&derecha){
            System.out.println(pos+" por aqui no puede");
            return false;
            
        }
        else return true;
    }
    
    /**
     * retorna si es una posicion donde no se puede comer
     * @param pos
     * @return 
     */
    private boolean esPosSegura(int pos){
        boolean esSegura=false;
        int[] seguras=tablero.getCasillasSeguras();
        for(int i=0;i<seguras.length;i++){
            if(seguras[i]==pos){
                esSegura=true;
                break;
            }
        }
        return esSegura;
    }
    
    /*
    sacar ficha verde
    */
    private boolean salirVerde(int i) {
        if(!hayFichaSalida(140,420)){
            fv.get(i).setX(140);
            fv.get(i).setY(420);
            fv.get(i).setHaSalido(true);
            fv.get(i).setPosicion(56);
            tablero.setS(tablero.getS()-1);
            fv.get(i).setBounds(fv.get(i).getX(),fv.get(i).getY(),22,22);
            return true;
        }
        if(!hayFichaSalida(140,390)){
            fv.get(i).setX(140);
            fv.get(i).setY(390);
            fv.get(i).setHaSalido(true);
            fv.get(i).setPosicion(56);
            tablero.setS(tablero.getS()-1);
            fv.get(i).setBounds(fv.get(i).getX(),fv.get(i).getY(),22,22);
            return true;
        }
        if(fichaDeTuColor(55,0,0)&&!fichaDeTuColor(55,0,1)){
            System.out.println("comer ficha en tu casa");
            hayFicha(55,1);
            for(int j=0;j<fv.size();j++){
                if(fv.get(j).getId().equals(nombreFicha)){
                    volverACasa(fv.get(j));
                    break;
                }
            }
            fv.get(i).setX(tablero.getTab()[55][2]);
            fv.get(i).setY(tablero.getTab()[55][3]);
            fv.get(i).setHaSalido(true);
            fv.get(i).setPosicion(56);
            tablero.setS(tablero.getS()-1);
            fv.get(i).setBounds(fv.get(i).getX(),fv.get(i).getY(),22,22);
            return true;
        }
        if(fichaDeTuColor(55,0,1)&&!fichaDeTuColor(55,0,0)){
            System.out.println("comer ficha en tu casa");
            hayFicha(55,0);
            for(int j=0;j<fv.size();j++){
                if(fv.get(j).getId().equals(nombreFicha)){
                    volverACasa(fv.get(j));
                    break;
                }
            }
            fv.get(i).setX(tablero.getTab()[55][0]);
            fv.get(i).setY(tablero.getTab()[55][1]);
            fv.get(i).setHaSalido(true);
            fv.get(i).setPosicion(56);
            tablero.setS(tablero.getS()-1);
            fv.get(i).setBounds(fv.get(i).getX(),fv.get(i).getY(),22,22);
            return true;
        }
        System.out.println("No puede salir ficha verde");
        return false;
    }
    
     /*
    sacar ficha amarilla
    */
    private boolean salirAmarillo(int i) {
        if(!hayFichaSalida(420,510)){
            fv.get(i).setX(420);
            fv.get(i).setY(510);
            fv.get(i).setHaSalido(true);
            fv.get(i).setPosicion(5);
            tablero.setF(tablero.getF()-1);
            fv.get(i).setBounds(fv.get(i).getX(),fv.get(i).getY(),22,22);
            return true;
        }
        if(!hayFichaSalida(390,510)){
            fv.get(i).setX(390);
            fv.get(i).setY(510);
            fv.get(i).setHaSalido(true);
            fv.get(i).setPosicion(5);
            tablero.setF(tablero.getF()-1);
            fv.get(i).setBounds(fv.get(i).getX(),fv.get(i).getY(),22,22);
            return true;
        }
        if(fichaDeTuColor(4,1,0)&&!fichaDeTuColor(4,1,1)){
            System.out.println("comer ficha en tu casa");
            hayFicha(4,1);
            for(int j=0;j<fv.size();j++){
                if(fv.get(j).getId().equals(nombreFicha)){
                    volverACasa(fv.get(j));
                    break;
                }
            }
            fv.get(i).setX(tablero.getTab()[4][2]);
            fv.get(i).setY(tablero.getTab()[4][3]);
            fv.get(i).setHaSalido(true);
            fv.get(i).setPosicion(5);
            tablero.setF(tablero.getF()-1);
            fv.get(i).setBounds(fv.get(i).getX(),fv.get(i).getY(),22,22);
            return true;
        }
        if(fichaDeTuColor(4,1,1)&&!fichaDeTuColor(4,1,0)){
            System.out.println("comer ficha en tu casa");
            hayFicha(4,0);
            for(int j=0;j<fv.size();j++){
                if(fv.get(j).getId().equals(nombreFicha)){
                    volverACasa(fv.get(j));
                    break;
                }
            }
            fv.get(i).setX(tablero.getTab()[4][0]);
            fv.get(i).setY(tablero.getTab()[4][1]);
            fv.get(i).setHaSalido(true);
            fv.get(i).setPosicion(5);
            tablero.setF(tablero.getF()-1);
            fv.get(i).setBounds(fv.get(i).getX(),fv.get(i).getY(),22,22);
            return true;
        }
        System.out.println("no puede salir ficha amarilla");
        return false;
    }
    
    /*
    sacar ficha azul
    */
    private boolean salirAzul(int i) {
        if(!hayFichaSalida(510,230)){
            fv.get(i).setX(510);
            fv.get(i).setY(230);
            fv.get(i).setHaSalido(true);
            fv.get(i).setPosicion(22);
            tablero.setG(tablero.getG()-1);
            fv.get(i).setBounds(fv.get(i).getX(),fv.get(i).getY(),22,22);
            return true;
        }
        if(!hayFichaSalida(510,260)){
            fv.get(i).setX(510);
            fv.get(i).setY(260);
            fv.get(i).setHaSalido(true);
            fv.get(i).setPosicion(22);
            tablero.setG(tablero.getG()-1);
            fv.get(i).setBounds(fv.get(i).getX(),fv.get(i).getY(),22,22);
            return true;
        }
        if(fichaDeTuColor(21,2,0)&&!fichaDeTuColor(21,2,1)){
            System.out.println("comer ficha en tu casa");
            hayFicha(21,1);
            for(int j=0;j<fv.size();j++){
                if(fv.get(j).getId().equals(nombreFicha)){
                    volverACasa(fv.get(j));
                    break;
                }
            }
            fv.get(i).setX(tablero.getTab()[21][2]);
            fv.get(i).setY(tablero.getTab()[21][3]);
            fv.get(i).setHaSalido(true);
            fv.get(i).setPosicion(22);
            tablero.setG(tablero.getG()-1);
            fv.get(i).setBounds(fv.get(i).getX(),fv.get(i).getY(),22,22);
            return true;
        }
        if(fichaDeTuColor(21,2,1)&&!fichaDeTuColor(21,2,0)){
            System.out.println("comer ficha en tu casa");
            hayFicha(21,0);
            for(int j=0;j<fv.size();j++){
                if(fv.get(j).getId().equals(nombreFicha)){
                    volverACasa(fv.get(j));
                    break;
                }
            }
            fv.get(i).setX(tablero.getTab()[21][0]);
            fv.get(i).setY(tablero.getTab()[21][1]);
            fv.get(i).setHaSalido(true);
            fv.get(i).setPosicion(22);
            tablero.setG(tablero.getG()-1);
            fv.get(i).setBounds(fv.get(i).getX(),fv.get(i).getY(),22,22);
            return true;
        }
        System.out.println("no puede salir ficha azul");
        return false;
        
    }
    
    /*
    sacar ficha roja
    */
    private boolean salirRojo(int i) {
        if(!hayFichaSalida(230,140)){
            fv.get(i).setX(230);
            fv.get(i).setY(140);
            fv.get(i).setHaSalido(true);
            fv.get(i).setPosicion(39);
            tablero.setA(tablero.getA()-1);
            fv.get(i).setBounds(fv.get(i).getX(),fv.get(i).getY(),22,22);
            return true;
        }
        if(!hayFichaSalida(260,140)){
            fv.get(i).setX(260);
            fv.get(i).setY(140);
            fv.get(i).setHaSalido(true);
            fv.get(i).setPosicion(39);
            tablero.setA(tablero.getA()-1);
            fv.get(i).setBounds(fv.get(i).getX(),fv.get(i).getY(),22,22);
            return true;
        }
        if(fichaDeTuColor(38,3,0)&&!fichaDeTuColor(38,3,1)){
            System.out.println("comer ficha en tu casa");
            hayFicha(38,1);
            for(int j=0;j<fv.size();j++){
                if(fv.get(j).getId().equals(nombreFicha)){
                    volverACasa(fv.get(j));
                    break;
                }
            }
            fv.get(i).setX(tablero.getTab()[38][2]);
            fv.get(i).setY(tablero.getTab()[38][3]);
            fv.get(i).setHaSalido(true);
            fv.get(i).setPosicion(39);
            tablero.setA(tablero.getA()-1);
            fv.get(i).setBounds(fv.get(i).getX(),fv.get(i).getY(),22,22);
            return true;
        }
        if(fichaDeTuColor(38,3,1)&&!fichaDeTuColor(38,3,0)){
            System.out.println("comer ficha en tu casa");
            hayFicha(38,0);
            for(int j=0;j<fv.size();j++){
                if(fv.get(j).getId().equals(nombreFicha)){
                    volverACasa(fv.get(j));
                    break;
                }
            }
            fv.get(i).setX(tablero.getTab()[38][0]);
            fv.get(i).setY(tablero.getTab()[38][1]);
            fv.get(i).setHaSalido(true);
            fv.get(i).setPosicion(39);
            tablero.setA(tablero.getA()-1);
            fv.get(i).setBounds(fv.get(i).getX(),fv.get(i).getY(),22,22);
            return true;
        }
        System.out.println("No puede salir ficha roja");
        return false;
    }
   

    private void volverACasa(FichaVista ficha) {
        acabaDeComer=true;
        ficha.setHaSalido(false);
        ficha.setPuedeVuelta(false);
        ficha.setPosicion(-1);
        switch(ficha.getId()){
            case "s1":
                ficha.setX(12);
                ficha.setY(450);
                tablero.setS(tablero.getS()+1);
                ficha.setBounds(ficha.getX(),ficha.getY(),22,22);
                break;
            case "s2":
                ficha.setX(200);
                ficha.setY(450);
                tablero.setS(tablero.getS()+1);
                ficha.setBounds(ficha.getX(),ficha.getY(),22,22);
                break;
            case "s3":
                ficha.setX(12);
                ficha.setY(635);
                tablero.setS(tablero.getS()+1);
                ficha.setBounds(ficha.getX(),ficha.getY(),22,22);
                break;
            case "s4":
                ficha.setX(200);
                ficha.setY(635);
                tablero.setS(tablero.getS()+1);
                ficha.setBounds(ficha.getX(),ficha.getY(),22,22);
                break;
            case "f1":
                ficha.setX(450);
                ficha.setY(450);
                tablero.setF(tablero.getF()+1);
                ficha.setBounds(ficha.getX(),ficha.getY(),22,22);
                break;    
            case "f2":
                ficha.setX(638);
                ficha.setY(450);
                tablero.setF(tablero.getF()+1);
                ficha.setBounds(ficha.getX(),ficha.getY(),22,22);
                break;
            case "f3":
                ficha.setX(450);
                ficha.setY(635);
                tablero.setF(tablero.getF()+1);
                ficha.setBounds(ficha.getX(),ficha.getY(),22,22);
                break;
            case "f4":
                ficha.setX(638);
                ficha.setY(635);
                tablero.setF(tablero.getF()+1);
                ficha.setBounds(ficha.getX(),ficha.getY(),22,22);
                break;
            case "g1":
                ficha.setX(450);
                ficha.setY(16);
                tablero.setG(tablero.getG()+1);
                ficha.setBounds(ficha.getX(),ficha.getY(),22,22);
                break;
            case "g2":
                ficha.setX(638);
                ficha.setY(16);
                tablero.setG(tablero.getG()+1);
                ficha.setBounds(ficha.getX(),ficha.getY(),22,22);
                break;
            case "g3":
                ficha.setX(450);
                ficha.setY(200);
                tablero.setG(tablero.getG()+1);
                ficha.setBounds(ficha.getX(),ficha.getY(),22,22);
                break;
            case "g4":
                ficha.setX(638);
                ficha.setY(200);
                tablero.setG(tablero.getG()+1);
                ficha.setBounds(ficha.getX(),ficha.getY(),22,22);
                break;
            case "a1":
                ficha.setX(12);
                ficha.setY(16);
                tablero.setA(tablero.getA()+1);
                ficha.setBounds(ficha.getX(),ficha.getY(),22,22);
                break;
            case "a2":
                ficha.setX(200);
                ficha.setY(16);
                tablero.setA(tablero.getA()+1);
                ficha.setBounds(ficha.getX(),ficha.getY(),22,22);
                break;
            case "a3":
                ficha.setX(12);
                ficha.setY(200);
                tablero.setA(tablero.getA()+1);
                ficha.setBounds(ficha.getX(),ficha.getY(),22,22);
                break;
            case "a4":
                ficha.setX(200);
                ficha.setY(200);
                tablero.setA(tablero.getA()+1);
                ficha.setBounds(ficha.getX(),ficha.getY(),22,22);
                break;
            default:
                break;
        }
    }
    
    /**
     * comprobar que la ficha ha llegado al final
     * @param ficha
     * @param num 
     */
    private boolean comprobarLlegada(int ficha, int num) {
       if(fv.get(ficha).getColor()==0&&fv.get(ficha).getPosicion()+num==99){
            System.out.println("llegoficha");
            fv.get(ficha).setHaLlegado(true);
            tablero.setHaLlegado(0);
            return true;
        }
        if(fv.get(ficha).getColor()==1&&fv.get(ficha).getPosicion()+num==75){
            System.out.println("llegoficha");
            fv.get(ficha).setHaLlegado(true);
            tablero.setHaLlegado(1);
            return true;
        }
        if(fv.get(ficha).getColor()==2&&fv.get(ficha).getPosicion()+num==83){
            System.out.println("llegoficha");
            fv.get(ficha).setHaLlegado(true);
            tablero.setHaLlegado(2);
            return true;
        }
        if(fv.get(ficha).getColor()==3&&fv.get(ficha).getPosicion()+num==91){
            System.out.println("llegoficha");
            fv.get(ficha).setHaLlegado(true);
            tablero.setHaLlegado(3);
            return true;
        }
        return false;
    }
    
    /**
     * comprobar si se pasa de las casillas disponibles
     * @param ficha
     * @param num
     * @return 
     */
    private boolean comprobarSiSaleDelCircuito(int ficha, int num) {
        if(fv.get(ficha).getColor()==0&&fv.get(ficha).getPosicion()+num>=100){
            return true;
        }
        if(fv.get(ficha).getColor()==1&&fv.get(ficha).getPosicion()+num>=76){
            return true;
        }
        if(fv.get(ficha).getColor()==2&&fv.get(ficha).getPosicion()+num>=84){
            return true;
        }
        if(fv.get(ficha).getColor()==3&&fv.get(ficha).getPosicion()+num>=92){
            return true;
        }
        return false;
    }
    
    /**
     * comprobar si puede pasar una ficha desde su posicion a otra
     * @param ficha
     * @param nuevaPos
     * @return 
     */
    private boolean puedePasarPorEsteRecorrido(int ficha, int nuevaPos) {
        if(nuevaPos>fv.get(ficha).getPosicion()-1){
            for(int i=fv.get(ficha).getPosicion();i<=nuevaPos;i++)
                if(!puedePasar(i))return false;
        }
        else{
            for(int i=fv.get(ficha).getPosicion();i<=67;i++)if(!puedePasar(i))return false;
            for(int i=0;i<=nuevaPos;i++)if(!puedePasar(i))return false;
        }
        return true;
    }
    
    /**
     * comprobar si puede pasar una ficha desde su posicion a otra en su camino de color
     * @param ficha
     * @param nuevaPos
     * @return 
     */
    private boolean mirarRecorridoColor(int ficha, int nuevaPos) {
        switch(fv.get(ficha).getColor()){
            case 0:
                for(int i=fv.get(ficha).getPosicion();i<=50;i++)
                    if(!puedePasar(i))return false;
                for(int i=93;i<=nuevaPos;i++)
                    if(!puedePasar(i))return false;
                break;
            case 1:
                for(int i=fv.get(ficha).getPosicion();i<=67;i++)
                    if(!puedePasar(i))return false;
                for(int i=68;i<=nuevaPos;i++)
                    if(!puedePasar(i))return false;
                break;
            case 2:
                for(int i=fv.get(ficha).getPosicion();i<=16;i++)
                    if(!puedePasar(i))return false;
                for(int i=77;i<=nuevaPos;i++)
                    if(!puedePasar(i))return false;
                break;
            case 3:
                for(int i=fv.get(ficha).getPosicion();i<=33;i++)
                    if(!puedePasar(i))return false;
                for(int i=85;i<=nuevaPos;i++)
                    if(!puedePasar(i))return false;
                break;
            default:
                break;
        }
        return true;
    }
    
    /**
     * coloco las fichas al final del circuito
     * @param ficha
     * @param nuevaPos 
     */
    private void colocarFichasFin(int ficha, int nuevaPos) {
        int[] hanLlegado=tablero.getHanLlegado();
        switch(fv.get(ficha).getColor()){
            case 0:
                switch(hanLlegado[0]){
                    case 1:
                        fv.get(ficha).setX(tablero.getTab()[nuevaPos][0]);
                        fv.get(ficha).setY(tablero.getTab()[nuevaPos][1]+4);
                        break;
                    case 2:
                        fv.get(ficha).setX(tablero.getTab()[nuevaPos][0]);
                        fv.get(ficha).setY(tablero.getTab()[nuevaPos][1]-22);
                        break;
                    case 3:
                        fv.get(ficha).setX(tablero.getTab()[nuevaPos][0]);
                        fv.get(ficha).setY(tablero.getTab()[nuevaPos][1]-48);
                        break;
                    case 4:
                        fv.get(ficha).setX(tablero.getTab()[nuevaPos][0]+26);
                        fv.get(ficha).setY(tablero.getTab()[nuevaPos][1]-22);
                        break;
                    default:
                        break;
                }
                break;
            case 1:
                switch(hanLlegado[1]){
                    case 1:
                        fv.get(ficha).setX(tablero.getTab()[nuevaPos][0]+4);
                        fv.get(ficha).setY(tablero.getTab()[nuevaPos][1]);
                        break;
                    case 2:
                        fv.get(ficha).setX(tablero.getTab()[nuevaPos][0]-22);
                        fv.get(ficha).setY(tablero.getTab()[nuevaPos][1]);
                        break;
                    case 3:
                        fv.get(ficha).setX(tablero.getTab()[nuevaPos][0]-48);
                        fv.get(ficha).setY(tablero.getTab()[nuevaPos][1]);
                        break;
                    case 4:
                        fv.get(ficha).setX(tablero.getTab()[nuevaPos][0]-22);
                        fv.get(ficha).setY(tablero.getTab()[nuevaPos][1]-26);
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                switch(hanLlegado[2]){
                    case 1:
                        fv.get(ficha).setX(tablero.getTab()[nuevaPos][0]);
                        fv.get(ficha).setY(tablero.getTab()[nuevaPos][1]-4);
                        break;
                    case 2:
                        fv.get(ficha).setX(tablero.getTab()[nuevaPos][0]);
                        fv.get(ficha).setY(tablero.getTab()[nuevaPos][1]+22);
                        break;
                    case 3:
                        fv.get(ficha).setX(tablero.getTab()[nuevaPos][0]);
                        fv.get(ficha).setY(tablero.getTab()[nuevaPos][1]+48);
                        break;
                    case 4:
                        fv.get(ficha).setX(tablero.getTab()[nuevaPos][0]-26);
                        fv.get(ficha).setY(tablero.getTab()[nuevaPos][1]+22);
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                switch(hanLlegado[3]){
                    case 1:
                        fv.get(ficha).setX(tablero.getTab()[nuevaPos][0]-4);
                        fv.get(ficha).setY(tablero.getTab()[nuevaPos][1]);
                        break;
                    case 2:
                        fv.get(ficha).setX(tablero.getTab()[nuevaPos][0]+22);
                        fv.get(ficha).setY(tablero.getTab()[nuevaPos][1]);
                        break;
                    case 3:
                        fv.get(ficha).setX(tablero.getTab()[nuevaPos][0]+48);
                        fv.get(ficha).setY(tablero.getTab()[nuevaPos][1]);
                        break;
                    case 4:
                        fv.get(ficha).setX(tablero.getTab()[nuevaPos][0]+22);
                        fv.get(ficha).setY(tablero.getTab()[nuevaPos][1]+26);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        fv.get(ficha).setBounds(fv.get(ficha).getX(),fv.get(ficha).getY(),22,22);
        fv.get(ficha).setPosicion(nuevaPos+1);
    }
    
    /**
     * devuelve true si alguno de los jugadores ha metido sus 4 fichas
     * @return 
     */
    public boolean juegoTerminado() {
        int[] hanLlegado=tablero.getHanLlegado();
        if(hanLlegado[0]==4||hanLlegado[1]==4||hanLlegado[2]==4||hanLlegado[3]==4)return true;
        return false;
    }
    
    /*
    setters and getters
    */
    public void setFichas(ArrayList<FichaVista> fv) {
        this.fv=fv;
    }
    public void setDadoVista(DadoVista dv){
        this.dv=dv;
    }
    public boolean getTocaMover(){
        return tocaMover;
    }
    public void setTocaMover(boolean b) {
        tocaMover=b;
    }   

    

}
