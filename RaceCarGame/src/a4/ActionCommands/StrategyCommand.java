package a4.ActionCommands;

import a4.GameWorld;
import a4.KillStrategy;
import a4.WinStrategy;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class StrategyCommand extends AbstractAction{
    
    protected static StrategyCommand instance;
    protected static GameWorld target;
    
    private StrategyCommand()
    {
        super("Switch Strategy");
    }
    
    public static void setTarget(GameWorld gw)
    {
        target = gw;
    }
    
    //Make Singleton
    public static StrategyCommand getInstance() {
        if (instance == null)
            instance = new StrategyCommand();
        return (StrategyCommand)instance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (target != null)
        {
            target.switchStrategy();
            target.notifyObservers();
        }
    }    
}
