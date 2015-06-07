package a4.ActionCommands;

import a4.GameWorld;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class SelectCommand implements MouseListener, MouseMotionListener{

    private static SelectCommand instance;
    private static GameWorld target;
    
//            
//    private SelectCommand()
//    {
//        super("Select Object");
//    }
    
    //Make Singleton
    public static SelectCommand getInstance() {
        if (instance == null)
            instance = new SelectCommand();
        return instance;
    }
    
    public static void setTarget(GameWorld gw)
    {
        SelectCommand.target = gw;
    }
    
    @Override
    public void mouseClicked(MouseEvent e){ 
            
        if (target.isPaused())
        {
            target.selectObjects(e.getX(), e.getY(), e.isControlDown());
            target.notifyObservers();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //do nothing
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //do nothing
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //do nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //do nothing
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (target.isPaused())
        {
            target.moveSelectedObjects(e.getX(), e.getY());
            target.notifyObservers();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //do nothing
    }    
}
