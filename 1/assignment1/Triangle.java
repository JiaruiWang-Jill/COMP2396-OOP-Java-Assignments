
/**
 * The Triangle class is used to model triangles. 
 * instance variables inherited from Shape:
 * 		color, filled, theta, xc, yc, xLocal, yLocal
 * methods inherited from Shape:
 * 		translate, rotate, getX, getY
 * 
 * overriding methods:
 * 		setVertices(double d)
 * @author jrwang
 *
 */
public class Triangle extends Shape{
	
	/**
	 * – a method for setting the local coordinates of the 3 vertices of a standard triangle. 
	 * A standard triangle is an equilateral triangle having its center located at (0, 0) and one of its vertex on the positive x-axis of its local  x,y (d, d) (-d, -d) (d, -d) (-d, d) coordinate system. 
	 * @param d 
	 * 		specifies the distance from the center of the triangle to any of its vertices.
	 * @see Shape#setVertices(double)
	 */
	public void setVertices(double d){
		xLocal = new double[3];
		yLocal = new double[3];
		xLocal[0] = d;
		yLocal[0] = 0;
		xLocal[1] = -d*(Math.cos(Math.PI/3));
		yLocal[1] = d*(Math.sin(Math.PI/3));
		xLocal[2]  = -d*(Math.cos(Math.PI/3));
		yLocal[2] = -d*(Math.sin(Math.PI/3));
	}
	
	
	
	
	
	
}
