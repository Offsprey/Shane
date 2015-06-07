package a4.ActionCommands;

import a4.GameWorld;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class FuelCommand extends AbstractAction{

    private static FuelCommand instance;
    private static GameWorld target;    
    
    private FuelCommand()
    {
        super("Picked Up FuelCan");
    }    
    
    public static void setTarget(GameWorld gw)
    {
        target = gw;
    }
    
    //Make Singleton
    public static FuelCommand getInstance() {
        if (instance == null)
            instance = new FuelCommand();
        return (FuelCommand)instance;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (target != null)
            target.pretendCollideFuel();
        target.notifyObservers();
        
    }
    
}
