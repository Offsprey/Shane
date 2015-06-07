package a4.ActionCommands;

import a4.GameWorld;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class CollideNpcCommand extends AbstractAction{

    private static CollideNpcCommand instance;
    private static GameWorld target;    
    
    private CollideNpcCommand()
    {
        super("Collide With NPC");
    }    
    
    public static void setTarget(GameWorld gw)
    {
        target = gw;
    }
    
    //Make Singleton
    public static CollideNpcCommand getInstance() {
        if (instance == null)
            instance = new CollideNpcCommand();
        return (CollideNpcCommand)instance;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //collide()
        int npcId = 0;
        if (target != null)
        {
            try
            {
                String inputString = JOptionPane.showInputDialog("Collide with which NPC (1, 2, or 3)");
                npcId = Integer.parseInt(inputString);
            }
            catch (NumberFormatException nfe)
            {
                
            }
            target.collideWithNPC(npcId);            
            target.notifyObservers();
        }
    }    
}
