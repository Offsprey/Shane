/*
 */
package a4;


/**
 *
 * @author Shane
 */
public interface IObservable {
    
    public void addObserver(IObserver ob);
    public void notifyObservers();
    public void notifyObservers(Object ob);
    
}
