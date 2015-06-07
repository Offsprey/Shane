/*
 */
package a4;

import a4.GameObjects.GameObject;


/**
 *
 * @author Shane
 */
//Simple Interface to ensure Iterator follows rules
public interface Iterator {
    
    public boolean hasNext();
    
    public GameObject next();
    
}
