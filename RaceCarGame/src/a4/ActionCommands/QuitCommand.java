package a4.ActionCommands;

import a4.Game;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class QuitCommand extends AbstractAction{
    //System.exit()
    protected static QuitCommand instance;
    protected static Game target;   
    
    private QuitCommand()
    {
        super("Quit");
    }    
    
    public static void setTarget(Game g)
    {
        target = g;
    }
    
    //Make Singleton
    public static QuitCommand getInstance() {
        if (instance == null)
            instance = new QuitCommand();
        return (QuitCommand)instance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (target != null)
        {            
            if (JOptionPane.showConfirmDialog(target, "Quit") == 0)
            {
                System.exit(0);
            }
            
        }    
    }
}
