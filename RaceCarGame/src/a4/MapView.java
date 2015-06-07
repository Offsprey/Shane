package a4;

import a4.GameObjects.Bird;
import a4.GameObjects.FuelCan;
import a4.GameObjects.GameObject;
import a4.GameObjects.OilSlick;
import a4.GameObjects.PlayerCar;
import a4.GameObjects.Pylon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class MapView extends JPanel implements IObserver, MouseWheelListener{

    private double windowLeft, windowRight, windowTop, windowBottom;
    private AffineTransform worldToND;
    private AffineTransform ndToScreen;
    private AffineTransform theVTM;
     
    
    private GameWorldProxy gwp;
    //private gameTimerStatus

    public MapView(GameWorldProxy gwp) {
        this.gwp = gwp;
        this.setBorder(new TitledBorder("Game Map"));
        this.setPreferredSize(new Dimension(GameWorld.gameBounds[0],GameWorld.gameBounds[1]));
        
        this.windowBottom = 0;
        this.windowTop = GameWorld.gameBounds[0];
        this.windowLeft = 0;
        this.windowRight = GameWorld.gameBounds[1];
        
        this.setVisible(true);
    }   
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g ;
        
        AffineTransform saveAT = g2d.getTransform();
        worldToND = this.buildWorldToNDXform(windowTop, windowRight, windowLeft, windowBottom);
        ndToScreen = this.buildNDToScreenXform(this.getWidth(), this.getHeight());
        theVTM = (AffineTransform) ndToScreen.clone();
        theVTM.concatenate(worldToND);
        
        g2d.transform(theVTM);
        
        GameObjectIterator ir = gwp.getIterator();
        while (ir.hasNext())
        {
            GameObject gObject = ir.next(); 
            //if (gObject instanceof PlayerCar)
            gObject.draw(g, gwp.isPaused());
        }
        
        g2d.setTransform(saveAT);
    }
    
    @Override
    public void update(IObservable o, Object arg) {
        this.repaint();        
    }    
    
    private AffineTransform buildWorldToNDXform(double winWidth, double winHeight, double windowLeft, double windowBottom) {
        
        AffineTransform at = new AffineTransform();        
        
        at.scale(1.0 / winWidth, 1.0 / winHeight);
        at.translate(-windowLeft, -windowBottom);
        
        return at;
    }

    private AffineTransform buildNDToScreenXform(int width, int height) {
        AffineTransform at = new AffineTransform();        
        
        at.translate(0, height);
        at.scale(width, -height);        
        
        return at;
    }
    
    public void zoomIn() {
        double h = windowTop - windowBottom;
        double w = windowRight - windowLeft;
        windowLeft += w*0.05;
        windowRight -= w*0.05;
        windowTop -= h*0.05;
        windowBottom += h*0.05;
        this.repaint();
    }
    
    public void zoomOut() {
        double h = windowTop - windowBottom;
        double w = windowRight - windowLeft;
        windowLeft -= w*0.05;
        windowRight += w*0.05;
        windowTop += h*0.05;
        windowBottom -= h*0.05;
        this.repaint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        
            if (e.getWheelRotation() > 0)
                zoomIn();
            if (e.getWheelRotation() < 0)
                zoomOut();
            
        
    }
}
