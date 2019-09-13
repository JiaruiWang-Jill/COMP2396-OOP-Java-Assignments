
/**
 * class Square is the subclass of Shape
 * instance variable inherited from Shape:color, filled, theta, xc, yc, xLocal, yLocal;
 * methods inherited from Shape: translate, rotate, getX, getY
 * override the method setVertices(double d)
 * @author jrwang
 */
public class Square extends Shape {
	/**
	 *a method for setting the local coordinates of the 4 vertices of a standard square.
	 * a standard square has its center located at (0, 0) and its sides being parallel to the x- and y-axes of its local coordinate system. 
	 * @param d
	 * 			d specifies half-the-length of a side of the square
	 * @see Shape#setVertices(double)
	 */
	public void setVertices(double d){	
		xLocal = new double[]{d,d,-d,-d};
		yLocal = new double[]{d,-d,-d,d};
		
	}

	
	
}
