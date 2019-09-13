
/**
 * The tester classes are used to verify the correctness of your implementation of the above 2 classes. 
 * @author Jerry
 *
 */
public class Tester {

	/**  Creates an instance of the Tester class
	 * 
	 * @param args
	 *            not being used in this application.
	 */
	public static void main(String[] args) {
//create one RegularPolygon
		RegularPolygon s = new RegularPolygon();
		s.setNumOfSides(3);
		s.setRadius(1.0);
		s.setTheta(0);
		s.setXc(0);
		s.setYc(0);
	
		System.out.println("This shape has "+s.getNumOfSides()+" sides");
		System.out.println("Radius is "+s.getRadius() );
//set the vertices and print it
		System.out.println("This is the x-coordinate of shape in local system");
		for(double i : s.getXLocal()){
			System.out.println(i);
		}
		
		System.out.println("This is the y-coordinate of shape in local system");
		for(double i : s.getYLocal()){
			System.out.println(i);
		}
//get the screen coordinates
		int[] xarray = new int[s.getNumOfSides()];
		xarray = s.getX();
		System.out.println("This is the x-coordinate of shape in screen system");
		for (int i=0;i< xarray.length;i++) 
		{
			System.out.println(xarray[i]);
		}
	
		int[] yarray = new int[s.getNumOfSides()];
		yarray = s.getY();

		System.out.println("This is the y-coordinate of shape in screen system");
		for (int i=0;i< yarray.length;i++) 
		{
			System.out.println(yarray[i]);
		}
		
//check whether in the shape
		System.out.println(s.contains(0, -1));
		

	}

}
