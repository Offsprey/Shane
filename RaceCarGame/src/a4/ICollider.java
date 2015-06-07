/*
 */
package a4;

/**
 *
 * @author Shane
 */
public interface ICollider {
    public boolean collidesWith(ICollider otherObject);
    public void handleCollision(ICollider otherObject, GameWorld gw);
    public int getDrawSize();
    
}
