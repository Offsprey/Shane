package a4.ActionCommands;

import a4.GameWorld;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class DeleteCommand  extends AbstractAction{

    private static DeleteCommand instance;
    private static GameWorld target;
    
            
    private DeleteCommand()
    {
        super("Delete");
    }
    
    //Make Singleton
    public static DeleteCommand getInstance() {
        if (instance == null)
            instance = new DeleteCommand();
        return instance;
    }
    
    public static void setTarget(GameWorld gw)
    {
        DeleteCommand.target = gw;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (target != null)
        {
            
            target.notifyObservers();
        }
    }
    
}
