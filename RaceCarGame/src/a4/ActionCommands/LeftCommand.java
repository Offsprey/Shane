package a4.ActionCommands;

import a4.GameWorld;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class LeftCommand  extends AbstractAction{

    private static LeftCommand instance;
    private static GameWorld target;
    
            
    private LeftCommand()
    {
        super("Turn Left");
    }
    
    //Make Singleton
    public static LeftCommand getInstance() {
        if (instance == null)
            instance = new LeftCommand();
        return instance;
    }
    
    public static void setTarget(GameWorld gw)
    {
        LeftCommand.target = gw;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (target != null)
        {
            target.left();
            target.notifyObservers();
        }
    }
    
}
