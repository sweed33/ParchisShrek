package parchisshrek;

/**
 *
 * @author eduar
 */
public class Dado {

    /**
     * metodo numero aleatorio dado
     * @return 
     */
    public int tirar() {
        return (int)Math.floor(Math.random()*6);
    }
}
