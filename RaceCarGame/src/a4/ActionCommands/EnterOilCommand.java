package a4.ActionCommands;

import a4.GameWorld;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class EnterOilCommand extends AbstractAction{

    private static EnterOilCommand instance;
    private static GameWorld target;    
    
    private EnterOilCommand()
    {
        super("Enter Oil Slick");
    }    
    
    public static void setTarget(GameWorld gw)
    {
        target = gw;
    }
    
    //Make Singleton
    public static EnterOilCommand getInstance() {
        if (instance == null)
            instance = new EnterOilCommand();
        return (EnterOilCommand)instance;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (target != null)
        {
            target.enterSlick();
            target.notifyObservers();
        }
    }
    
}
