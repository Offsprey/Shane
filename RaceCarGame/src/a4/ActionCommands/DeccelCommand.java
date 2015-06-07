package a4.ActionCommands;

import a4.GameWorld;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class DeccelCommand  extends AbstractAction{

    private static DeccelCommand instance;
    private static GameWorld target;
    
            
    private DeccelCommand()
    {
        super("Deccelerate");
    }
    
    //Make Singleton
    public static DeccelCommand getInstance() {
        if (instance == null)
            instance = new DeccelCommand();
        return instance;
    }
    
    public static void setTarget(GameWorld gw)
    {
        DeccelCommand.target = gw;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (target != null)
        {
            target.decel();
            target.notifyObservers();
        }
    }
    
}
