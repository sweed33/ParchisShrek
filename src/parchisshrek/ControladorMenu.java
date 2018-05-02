package parchisshrek;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author eduar
 */
class ControladorMenu implements ActionListener{
    
    private Vista v;
    
    public ControladorMenu(Vista v){
        this.v=v;
    }
    /**
     * controla si hay click en algun menuItem del menuBar
     * @param ae 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        switch(ae.getActionCommand()){
            case "Exit":
                System.exit(0);
                break;
            case "Take Photo":
        {
            try {
                v.takePhoto();
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(ControladorMenu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (LineUnavailableException ex) {
                Logger.getLogger(ControladorMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
            default:
                break;
        }
    }
}
