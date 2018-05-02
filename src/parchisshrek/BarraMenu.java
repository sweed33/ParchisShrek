package parchisshrek;

import java.awt.Toolkit;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 *
 * @author eduar
 */
public class BarraMenu extends JMenuBar {

    private JMenu menu;
    private JMenuItem menuItemSalir,menuItemFoto;
    private final ControladorMenu cm;
    /**
     * Constructor MenuBar
     * @param v
     */
    public BarraMenu(Vista v) {
        cm=new ControladorMenu(v);
        crearMenu();
    }
    
    /**
     * crear menu
     */
    public final void crearMenu(){
        menu = new JMenu("File");
        menuItemSalir = new JMenuItem("Exit");
        menuItemSalir.setAccelerator(KeyStroke.getKeyStroke('0', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        menu.add(menuItemSalir);
        menuItemSalir.addActionListener(cm);
        
        menuItemFoto = new JMenuItem("Take Photo");
        menuItemFoto.setAccelerator(KeyStroke.getKeyStroke('1', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        menu.add(menuItemFoto);
        menuItemFoto.addActionListener(cm);
        
        this.add(menu);
    }
}
