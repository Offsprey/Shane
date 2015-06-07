/*
 */
package a4;

/**
 *
 * @author Shane
 */

//Provide interface for Car to Steer
public interface ISteerable {
    public void setSteerDir(int newSteerDir);
    public void adjustSteerDir(int delta);
    public int getSteerDir();   
}
