/*
 */
package a4;

import java.awt.Graphics;

/**
 *
 * @author Shane
 */
public interface ISelectable {
    
    public boolean isSelected();
    public void setSelected(boolean selected);
    public boolean contains(double x, double y);
    public void draw(Graphics g, boolean drawSelected);
    public void drawSelected(Graphics g);
    public void drawNormal(Graphics g);
    
}
