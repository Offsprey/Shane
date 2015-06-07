package a4;

import a4.GameObjects.GameObject;
import java.util.Vector;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class GameObjectCollection{
    
    private Vector<GameObject> gObjects;

    public GameObjectCollection() {
        gObjects = new Vector<GameObject>();
    }
    
    
    
    public GameObjectIterator getIterator()
    {
        return new GameObjectIterator(gObjects);
    }
    
    public void add(GameObject go)
    {
        gObjects.add(go);
    }
    
    public void remove(GameObject go)
    {
        gObjects.remove(go);
    }
}
