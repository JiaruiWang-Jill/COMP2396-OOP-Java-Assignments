
/**
 * The Circle class is used to model circles.
 *instance variables inherited from Shape:
 *		color, filled, theta, xc, yc, xLocal, yLocal
 *methods inherited from Shape:
 *		translate, rotate
 *overriding methods:
 *		setVertices(double d), getX(), getY()
 *
 * @author jrwang
 *
 */
public class Circle extends Shape{
	/** 
	 * 
	 * – a method for setting the local coordinates of the upper left and lower right vertices of an axis-aligned bounding box of a standard circle. 
	 * A standard circle is a circle having its center located at (0, 0) of its local coordinate system. 
	 * @param d
	 * 		specifies the radius of the circle.
	 * @see Shape#setVertices(double)
	 */
	public void setVertices(double d){
	xLocal = new double[]{-d,d};
	yLocal = new double[]{-d,d};

	}
	
	/**
	 * – a method for retrieving the x-coordinates of the upper left and lower right vertices of an axis-aligned bounding box of the circle in the screen coordinate system (rounded to nearest integers).
	 * 
	 * return an array of x-coordinates of the upper left and lower right vertices of an axis-aligned bounding box of the circle in the screen coordinate system (rounded to nearest integers).
	 * @see Shape#getX()
	 */
	public int[] getX(){
		int[] xScreen=new int[2];
		for (int a=0;a<2;a++){
			double s = xLocal[a]+xc;
			xScreen[a] = (int) Math.round(s);
		}
		return xScreen;
	}
	
	
	/** 
	 * 
	 * A method for retrieving the y-coordinates of the upper left and lower right vertices of an axis-aligned bounding box of the circle in the screen coordinate system (rounded to nearest integers).
	 * 
	 * return an array of y-coordinates of the upper left and lower right vertices of an axis-aligned bounding box of the circle in the screen coordinate system (rounded to nearest integers).
	 * @see Shape#getY()
	 */
	public int[] getY(){
		int[] yScreen=new int[2];
		for (int a=0;a<2;a++){
			double s = yLocal[a]+yc;
			yScreen[a] = (int) Math.round(s);
		}
		return yScreen;
	}

}
