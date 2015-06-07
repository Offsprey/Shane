package a4;

import a4.GameObjects.GameObject;
import java.util.Vector;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class GameObjectIterator implements Iterator{

    private Vector<GameObject> gObjects;

    private int currentIndex;
    
    public GameObjectIterator(Vector<GameObject> gObjects) {
        this.gObjects = gObjects;
        currentIndex = -1;
    }       
    
    @Override
    public boolean hasNext() {
        return (currentIndex + 1 < gObjects.size());
    }

    @Override
    public GameObject next() {
        currentIndex++;
        if (currentIndex >= gObjects.size())
            currentIndex = 0;
        return gObjects.elementAt(currentIndex);
    }

    
}
