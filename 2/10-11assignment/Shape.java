import java.awt.Color;

/**The Shape class is used to model general shapes. It has private instance variables for storing
 *color, fill-type, orientation, screen coordinates of the center, and the local coordinates of the
 *vertices of a shape. It has getters and setters for accessing its private instance variables. It
 *also has methods for translating and rotating a shape, and for getting the screen coordinates
 *of the vertices of a shape. 
 * @author Jerry
 */
public class Shape {
	private Color color;
	private boolean filled;
	private double theta;
	private double xc;
	private double yc;
	private double[] xLocal;
	private double[] yLocal;
	
	
	/**
	 * a method for retrieving the color of the shape.
	 * @return
	 * 			return color
	 */
	public Color getColor() {
		return this.color;
	}
	
	/**
	 * a method for retrieving the fill-type of the shape
	 * @return
	 * 		return the filled-type of the shape
	 */
	public boolean getFilled() {
		return this.filled;
	}
	
	/**
	 * a method for retrieving the orientation (in radians) of the shape in
	 * the screen coordinate system
	 * @return
	 * 		return theta(in radians)of the shape in the screen coordinate system
	 */
	public double getTheta() {
		return this.theta;
	}
	
	/**
	 * a method for retrieving the x-coordinate of the center of the shape in
	 * the screen coordinate system.
	 * 
	 * @return
	 * 		return the x-coordinate of the center of the shape in the screen coordinate system.
	 */
	public double getXc() {
		return this.xc;
	}
	
	/**
	 * a method for retrieving the y-coordinate of the center of the shape in
	 * the screen coordinate system.
	 * @return
	 * 		return the y-coordinate of the center of the shape in the screen coordinate system.
	 * 
	 */
	public double getYc() {
		return this.yc;
	}
	
	/**
	 * 每 a method for retrieving the x-coordinates of the vertices (in
	 *  counter clock-wise order) of the shape in its local coordinate system
	 * @return 
	 *  		return array with the x-coordinates of the vertices (in counter clock-wise order) of the shape in its local coordinate system
	 */
	public double[] getXLocal() {
		return this.xLocal;
	}
	
	/**
	 * a method for retrieving the y-coordinates of the vertices (in
     * counter clock-wise order) of the shape in its local coordinate system.
	 * @return
	 * 		 return array with y-coordinates of the vertices (in counter clock-wise order) of the shape in its local coordinate system.
	 */
	public double[] getYLocal() {
		return this.yLocal;
	}
	
	/**
	 * 每 a method for setting the color of the shape
	 * @param color
	 * 			the color to fill
	 */
	public void setColor(Color color) {
		this.color=color;
	}
	
	/**
	 * 每 a method for setting the fill-type of the shape. 
	 * @param filled 
	 * 			boolean type, filled or not
	 */
	public void setFilled(boolean filled) {
		this.filled=filled;
	}
	
	/**
	 * a method for setting the orientation of the shape.
	 * @param theta
	 * 			 orientation of the shape in radians
	 */
	public void setTheta(double theta) {
		this.theta=theta;
	} 
	
	/**
	 * 每 a method for setting the x-coordinate of the center of the
	 * shape in the screen coordinate system.
	 * @param xc
	 * 			 x-coordinate of the center of the shape in the screen coordinate system
	 */
	public void setXc(double xc) {
		this.xc=xc;
	}
	
	/**
	 * a method for setting the y-coordinate of the center of the
	 * shape in the screen coordinate system.
	 * @param yc
	 * 			y-coordinate of the center of the shape in the screen coordinate system
	 */
	public void setYc(double yc) {
		this.yc=yc;
	}
	
	/**
	 * 每 a method for setting the x-coordinates of the
	 * vertices (in counter clock-wise order) of the shape in its local coordinate system.
	 * @param xLocal
	 * 			x-coordinates of the vertices (in counter clock-wise order) of the shape in its local coordinate system
	 */
	public	void setXLocal(double[] xLocal){
		this.xLocal=xLocal;
	}
	
	/**
	 * 每 a method for setting the y-coordinates of the
	 *	vertices (in counter clock-wise order) of the shape in its local coordinate system.
	 * @param yLocal
	 * 			y-coordinates of the vertices (in counter clock-wise order) of the shape in its local coordinate system.
	 */
	public void setYLocal(double[] yLocal){
		this.yLocal=yLocal;
	}

	/**
	 * a method for translating the center of the shape by dx and dy, respectively, along the x and y directions of the screen coordinate
	 * system. 
	 * @param dx   x-coordinate difference to translate
	 * @param dy   y-coordinate difference to translate
	 */
	public void translate(double dx, double dy) 
	{
		xc += dx;
		yc += dy;	
	}
	
	/**
	 * 每 a method for rotating the shape about its center by an angle
	 * of dt (in radians). (i.e., dt should be added to theta.)
	 * @param dt
	 * 			a double value, which is the rotating theta (in radians)
	 */
	public void rotate(double dt)
	{   double fina = getTheta()+dt;
		setTheta(fina);
	}
	
	/**
	 * 每 a method for retrieving the x-coordinates of the vertices (in counter
	 * clock-wise order) of the shape in the screen coordinate system
	 * @return
	 * 		 return an array with x-coordinates of the vertices (in counter clock-wise order) of the shape in the screen coordinate system
	 */
	public int[] getX() 
	{	
		if(getXLocal()==null) 
		{
		return null;
		}
		
		int[] xScreen=new int[this.getXLocal().length];
		for(int a = 0;a<this.getXLocal().length;a++){
			double s = (this.getXLocal()[a]*Math.cos(this.getTheta()))-(this.getYLocal()[a]*Math.sin(this.getTheta()))+this.getXc();
			xScreen[a] = (int) Math.round(s);
		}
		return xScreen;
	}

	/**
	 * a method for retrieving the y-coordinates of the vertices (in counter
	 * clock-wise order) of the shape in the screen coordinate system
	 * @return
	 * 			return an array with y-coordinates of the vertices (in counter clock-wise order) of the shape in the screen coordinate system
	 */
	public int[] getY() 
	{	
		if(getYLocal()==null) 
		{
			return null;
		}
		int[] yScreen=new int[this.getYLocal().length];
		for(int a = 0;a<this.getYLocal().length;a++){
			double s = (this.getXLocal()[a]*Math.sin(this.getTheta()))+(this.getYLocal()[a]*Math.cos(this.getTheta()))+this.getYc();
        	yScreen[a] = (int)Math.round(s);

		}
		return yScreen;	
	}	
}
	
	
	
	
	
	
	
	
	
	
	
	
	

