package a4.ActionCommands;

import a4.GameWorld;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class AddFuelCommand extends AbstractAction{

    private static AddFuelCommand instance;
    private static GameWorld target;    
    
    private AddFuelCommand()
    {
        super("Add New Fuel");
    }    
    
    public static void setTarget(GameWorld gw)
    {
        target = gw;
    }
    
    //Make Singleton
    public static AddFuelCommand getInstance() {
        if (instance == null)
            instance = new AddFuelCommand();
        return (AddFuelCommand)instance;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (target != null)
            target.addNewFuel();
        target.notifyObservers();
        
    }
    
}
