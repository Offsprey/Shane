package a4.ActionCommands;

import a4.GameWorld;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class SoundCommand extends AbstractAction{

    private static SoundCommand instance;
    private static GameWorld target;    
    
    private SoundCommand()
    {
        super("Toggle Sound");
    }    
    
    public static void setTarget(GameWorld gw)
    {
        target = gw;
    }
    
    //Make Singleton
    public static SoundCommand getInstance() {
        if (instance == null)
            instance = new SoundCommand();
        return (SoundCommand)instance;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (target != null)
        {
            target.setSound(!target.isSound());
            target.notifyObservers();
        }
        
        
    }
    
}
