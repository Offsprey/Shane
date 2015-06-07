package a4.ActionCommands;

import a4.GameWorld;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class TickCommand extends AbstractAction{
      
    protected static TickCommand instance;
    protected static GameWorld target;    
    
    private TickCommand()
    {
        super("Tick");
    }    
    
    public static void setTarget(GameWorld gw)
    {
        target = gw;
    }
    
    //Make Singleton
    public static TickCommand getInstance() {
        if (instance == null)
            instance = new TickCommand();
        return (TickCommand)instance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (target != null)
        {
            target.tick();
            target.notifyObservers();            
        }
            
    }
    
}
