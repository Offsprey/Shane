package a4.ActionCommands;

import a4.GameWorld;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class PylonCommand extends AbstractAction{
      
    private static PylonCommand instance;
    private static GameWorld target;    
    
    private PylonCommand()
    {
        super("Pylon Reached");
    }    
    
    public static void setTarget(GameWorld gw)
    {
        target = gw;
    }
    
    //Make Singleton
    public static PylonCommand getInstance() {
        if (instance == null)
            instance = new PylonCommand();
        return (PylonCommand)instance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (target != null)
        {
            int pylonNum = 1;
            try
            {
                String inputString = JOptionPane.showInputDialog("Enter Pylon Number");
                pylonNum = Integer.parseInt(inputString);
            }
            catch (NumberFormatException nfe)
            {
                
            }
            target.pylonReached(pylonNum);
            target.notifyObservers();
        }
        
    }
    
}
