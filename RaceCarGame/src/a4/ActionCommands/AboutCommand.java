package a4.ActionCommands;

import a4.Game;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class AboutCommand  extends AbstractAction{
    private static Game target; 
    private static AboutCommand instance;
           
    private AboutCommand()
    {
        super("About");
    }
    
    
    public static void setTarget(Game g)
    {
        target = g;
    }
    
    //Make Singleton
    public static AboutCommand getInstance() {
        if (instance == null)
            instance = new AboutCommand();
        return instance;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (target != null)
            JOptionPane.showMessageDialog(target, "Shane Neal\nCSC133\n@SpreSoft");
    }
}
