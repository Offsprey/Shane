/*
 */
package a4;

/**
 *
 * @author Shane
 */
public interface ITransformable {
    public void translate(int x, int y);
    public void scale(int xScale, int yScale);
    public void rotate(int heading);
}
