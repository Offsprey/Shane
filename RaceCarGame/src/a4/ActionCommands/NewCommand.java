package a4.ActionCommands;

import a4.Game;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class NewCommand  extends AbstractAction{
    private static Game target; 
    private static NewCommand instance;
           
    private NewCommand()
    {
        super("New");
    }
    
    public static void setTarget(Game g)
    {
        target = g;
    }
    
    //Make Singleton
    public static NewCommand getInstance() {
        if (instance == null)
            instance = new NewCommand();
        return instance;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (target != null)
            JOptionPane.showMessageDialog(target, "New Button Pushed");
    }
}
