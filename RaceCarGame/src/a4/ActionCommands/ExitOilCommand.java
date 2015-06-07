package a4.ActionCommands;

import a4.GameWorld;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class ExitOilCommand extends AbstractAction{

    private static ExitOilCommand instance;
    private static GameWorld target;    
    
    private ExitOilCommand()
    {
        super("Exit Oil Slick");
    }    
    
    public static void setTarget(GameWorld gw)
    {
        target = gw;
    }
    
    //Make Singleton
    public static ExitOilCommand getInstance() {
        if (instance == null)
            instance = new ExitOilCommand();
        return (ExitOilCommand)instance;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (target != null)
            target.exitSlick();
        target.notifyObservers();
    }
    
}
