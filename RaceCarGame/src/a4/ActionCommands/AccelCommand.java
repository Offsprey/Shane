package a4.ActionCommands;

import a4.GameWorld;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class AccelCommand extends AbstractAction{

    private static AccelCommand instance;
    private static GameWorld target;
    
            
    private AccelCommand()
    {
        super("Turn Left");
    }
    
    //Make Singleton
    public static AccelCommand getInstance() {
        if (instance == null)
            instance = new AccelCommand();
        return instance;
    }
    
    public static void setTarget(GameWorld gw)
    {
        AccelCommand.target = gw;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (target != null)
        {
            target.accel();
            target.notifyObservers();
        }
    }    
}
