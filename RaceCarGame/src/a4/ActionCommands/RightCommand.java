package a4.ActionCommands;

import a4.GameWorld;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class RightCommand extends AbstractAction{

    private static RightCommand instance;
    private static GameWorld target;    
            
    private RightCommand()
    {
        super("Turn Right");
    }
    
    //Make Singleton
    public static RightCommand getInstance() {
        if (instance == null)
            instance = new RightCommand();
        return instance;
    }
    
    public static void setTarget(GameWorld gw)
    {
        RightCommand.target = gw;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (target != null)
        {
            target.right();
            target.notifyObservers();
        }
    }
    
}
