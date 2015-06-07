package a4.ActionCommands;

import a4.Game;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class SaveCommand  extends AbstractAction{
    private static Game target; 
    private static SaveCommand instance;
           
    private SaveCommand()
    {
        super("Save");
    }
    
    public static void setTarget(Game g)
    {
        target = g;
    }
    
    //Make Singleton
    public static SaveCommand getInstance() {
        if (instance == null)
            instance = new SaveCommand();
        return instance;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (target != null)
            JOptionPane.showMessageDialog(target, "Save Button Pushed");
    }
}
