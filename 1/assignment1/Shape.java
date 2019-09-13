import java.awt.Color;

/**
 * This is the shape class to model general shapes. 
 * It includes the color, fill0tyoe, orientation, screen coordinates of the center and local coordinates of a shape.
 * There are methods to get the screen coordinate of vertices of a shape.
 * 
 * @author jrwang
 * @ version 1.0
 * @since 2017-9-19
 *
 */
public class Shape {
	/**
	 *A Color object specifying the color of the shape
	 */
	public Color color;
	/**
	 *Boolean value specifying whether the shape is filled or not filled.
	 */
	public boolean filled;
	/**
	 *A double value specifying the orientation (in radians) of the shape in the screen coordinate system
	 */
	public double theta;
	/**
	 * A double value specifying the x-coordinate of the center of the shape in the screen coordinate system
	 */
	public double xc;
	/**
	 *A double value specifying the y-coordinate of the center of the shape in the screen coordinate system.
	 */
	public double yc;
	/**
	 *An array of double values specifying the x-coordinates of the vertices (in counter clock-wise order) of the shape in its local coordinate system.
	 */
	public double[] xLocal;
	/**
	 * An array of double values specifying the y-coordinates of the vertices (in counter clock-wise order) of the shape in its local coordinate system.
	 */
	public double[] yLocal;	


	
	/**
	 * The method setVertices can set the local coordinates of the vertices of a shape.
	 * This override in Square,Circle,Triangle subclasses.
	 * 
	 * @param d
	 * 			d represents different length in each subclass. Please the other subclasses.
	 * 
	 */
	public void  setVertices(double d){
	
	}
	/**
	 * The method translate can translate the center of the shape by dx and dy, respectively, along x ad y direction.
	 * 
	 * 
	 * @param dx
	 * 			dx represents the x direction distance, which equals to the distance center moves.
	 * @param dy
	 * 			dy represents the y direction distance, which equals to the distance center moves.
	 */
	public void translate(double dx, double dy){
		xc += dx;
		yc += dy;	
	}
	
	/**
	 * A method for rotating the shape about its center by an angle  of dt (in radians)
	 * @param dt
	 * 			the rotated angle,(in radians)
	 */
	public void rotate(double dt){
	theta+=dt;
	}


	/**
	 * getX can retrieve the x coordinate of the vertices (in counter-clockwise order) of the shape in the screen coordinate system.
	 * (rounded into the nearest integer)
	 * 
	 * @return
	 * 		return an array with the x coordinates of vertices in screen coordinate system.
	 */			
	public int[] getX(){
		int[] xScreen=new int[xLocal.length];
		for(int a = 0;a<xLocal.length;a++){
			double s = xLocal[a]*Math.cos(theta)-yLocal[a]*Math.sin(theta)+xc;
			xScreen[a] = (int) Math.round(s);
		}
		return xScreen;
	}


	/**
	 *  getY can retrieve the y coordinate of the vertices (in counter-clockwise order) of the shape in the screen coordinate system.
	 * (rounded into the nearest integer)
	 * @return
	 * 		return an array with the y coordinates of vertices in screen coordinate system.
	 */
	public int[] getY(){
		int[] yScreen=new int[yLocal.length];
		for(int a = 0;a<yLocal.length;a++){
			double s = xLocal[a]*Math.sin(theta)+yLocal[a]*Math.cos(theta)+yc;
        	yScreen[a] = (int) Math.round(s);
	
		
		}
		return yScreen;	
	}
	

}
