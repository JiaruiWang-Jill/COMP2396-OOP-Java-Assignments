
/**
 * The RegularPolygon class is a subclass of the Shape class, and is used to model regular nsided
 *polygons. Besides the properties it inherited from the Shape class, the RegularPolygon
 *class also declares a number of private instance variables for storing the number of sides and
 *radius of a polygon. It has getters and setters for accessing its private instance variables. It
 *also has methods for setting the local coordinates of the vertices of a polygon and for
 *checking if a point (in the screen coordinate system) is contained by a polygon.
 * @author Jerry
 *
 */
public class RegularPolygon extends Shape{
	/**a constructor for building a regular n-sided polygon with a radius of r
	 * @param n  
	 * 			n is number of sides.
	 * @param r
	 * 			r is radus
	 */
	public RegularPolygon(int n, double r)
	{
		if(n<3) 
		{
			this.numOfSides = 3;
			this.radius =r;
		}
		else if(r<0) 
		{
			this.radius=0;
			this.numOfSides=n;
		}
		else 
		{
		this.numOfSides = n;
		this.radius =r;
	    }

		this.setvertices();
	}
	
	/**
	 * a constructor for building a regular n-sided polygon with a
     * radius of 1.0
	 * @param n
	 * 		number of sides
	 */
	public RegularPolygon(int n){
		this.radius =1.0;
		if(n<3) 
		{
			this.numOfSides =3;
		}
		else 
		{
			this.numOfSides=n;
		}
		this.setvertices(); 
	}
	
	/**
	 * 每 a constructor for building a regular 3-sided polygon with a radius of 1.0.
	 * 
	 */
	public RegularPolygon(){
		this.numOfSides=3;
		this.radius=1.0;
		this.setvertices(); 
	}
	
	private int numOfSides;
	private double radius;
	
	
	/**
	 * 每 a method for retrieving the number of sides of the regular polygon.
	 * @return
	 * 		return number of sides
	 */
	public int getNumOfSides() {
		return this.numOfSides;
	}
	
	/**
	 * 每 a method for retrieving the radius of the regular polygon.
	 * @return
	 * 		radius
	 */
	public double getRadius() {
		return this.radius;
	}
	
	/**
	 * 每 a method for setting the number of sides of the regular
	 *n-sided polygon. 
	 *This method also reset the local coordinates of the vertices of the regular n-sided polygon
	 * @param n
	 * 			number of sides
	 */
	public void setNumOfSides(int n) 
	{
		if (n<3) 
		{
			this.numOfSides=3;
		}
		else
		{
			this.numOfSides=n;
		}
		this.setvertices();
	}
	
	/**
	 * a method for setting the radius of the regular n-sided polygon. 
	 * This method  also reset the local coordinates of the vertices of the regular n-sided polygon.
	 * @param r
	 * 			radius
	 */
	public void setRadius(double r) 
	{
		if(r<0) 
		{
			this.radius=0;
		}
		else
		{
			this.radius=r;
		}
		this.setvertices();
	}
	
	/**
	 * 每 a method for setting the local coordinates of the vertices of the
	 *regular n-sided polygon based on its number of sides and radius (see appendix).
	*/
	public void setvertices() 
	{   double[] test_x = new double[this.getNumOfSides()];
		double[] test_y = new double[this.getNumOfSides()];
		if((this.getNumOfSides() % 2) != 0)
		{
			for(int i =0; i<getNumOfSides() ; i++) 
			{
				test_x[i]= getRadius()*Math.cos(0-(i*(2*Math.PI)/getNumOfSides()));
				test_y[i]= getRadius()*Math.sin(0-(i*(2*Math.PI)/this.getNumOfSides()));
			}	
		}
		else 
		{	
			for( int i =0; i< this.getNumOfSides() ; i++)
			{
				test_x[i]=this.getRadius()*Math.cos((Math.PI/this.getNumOfSides())-(i*(2*Math.PI)/this.getNumOfSides()));
				test_y[i]=this.getRadius()*Math.sin((Math.PI/this.getNumOfSides())-(i*(2*Math.PI)/this.getNumOfSides()));
			}
		}
		this.setXLocal(test_x);
		this.setYLocal(test_y);

	}
	
	/**
	 * 每 a method for determining if a point (x, y) in
	*the screen coordinate system is contained by the regular n-sided polygon. A point is
	*considered to be contained by a polygon if it lies either completely inside the polygon,
	*or on any of the sides or vertices of the polygon
	 * @param x
	 * 			x coordinate of the point in the screen coordinate system
	 * @param y
	 * 			y coordiante of the point in the screen coordinate system
	 * @return
	 */

	public boolean contains(double x, double y) 
	{	
		double temp_x= (x-getXc())*Math.cos(-getTheta())-(y-getYc())*Math.sin(-getTheta());
		double temp_y= (x-getXc())*Math.sin(-getTheta())+(y-getYc())*Math.cos(-getTheta());
		int num = getNumOfSides();
		double least = getXLocal()[0];
		for(int a=0;a<getXLocal().length;a++) {
			if(getXLocal()[a]<least) {
				least = getXLocal()[a];
			}
		}
		if(least -temp_x> 0.00001) 
			{
			return false;
			} 
	
		for(int i=0;i<num;i++) 
			{	
				double s = temp_x*Math.cos(i*2*Math.PI/getNumOfSides())-temp_y*Math.sin(i*2*Math.PI/getNumOfSides());			
				if(least - s > 0.00001 )
				{
				return false;
				} 
			}
	
			return true;
		}

	
	
}
