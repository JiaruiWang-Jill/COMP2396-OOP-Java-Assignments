
/**
 * The RegularPolygon tester classes are used to verify the correctness of your implementation of the above 2 classes. 
 * @author Jerry
 *
 */
public class RegularPolygonTester {

	/**  Creates an instance of the RegularPolygonTester class
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
		System.out.println(s.contains(-0.499999998, 0.86602540));
		System.out.println(s.contains(0, 0));
		System.out.println(s.contains(1, 1));
		
		
		
		//create one RegularPolygon (with constructor  RegularPolygon(int n, double r))
		RegularPolygon s1 = new RegularPolygon(4,1.0);
				s1.setTheta(0);
				s1.setXc(0);
				s1.setYc(0);
			
				System.out.println("This shape has "+s1.getNumOfSides()+" sides");
				System.out.println("Radius is "+s1.getRadius() );
		//set the vertices and print it(with constructor  RegularPolygon(int n, double r))
				System.out.println("This is the x-coordinate of shape in local system");
				for(double i : s1.getXLocal()){
					System.out.println(i);
				}
				
				System.out.println("This is the y-coordinate of shape in local system");
				for(double i : s1.getYLocal()){
					System.out.println(i);
				}
		//get the screen coordinates(with constructor  RegularPolygon(int n, double r))
				int[] xarray1 = new int[s1.getNumOfSides()];
				xarray1 = s1.getX();
				System.out.println("This is the x-coordinate of shape in screen system");
				for (int i=0;i< xarray1.length;i++) 
				{
					System.out.println(xarray1[i]);
				}
			
				int[] yarray1 = new int[s1.getNumOfSides()];
				yarray1 = s1.getY();

				System.out.println("This is the y-coordinate of shape in screen system");
				for (int i=0;i< yarray1.length;i++) 
				{
					System.out.println(yarray1[i]);
				}
				
		//check whether in the shape(with constructor  RegularPolygon(int n, double r))
				System.out.println(s1.contains(-0.499999998, 0.86602540));
				System.out.println(s1.contains(0, 0));
				System.out.println(s1.contains(1, 1));
				
				
				//create one RegularPolygon
				RegularPolygon s3 = new RegularPolygon(5);
				s3.setRadius(1.0);
				s3.setTheta(0);
				s3.setXc(0);
				s3.setYc(0);
			
				System.out.println("This shape has "+s3.getNumOfSides()+" sides");
				System.out.println("Radius is "+s3.getRadius() );
		//set the vertices and print it
				System.out.println("This is the x-coordinate of shape in local system");
				for(double i : s3.getXLocal()){
					System.out.println(i);
				}
				
				System.out.println("This is the y-coordinate of shape in local system");
				for(double i : s3.getYLocal()){
					System.out.println(i);
				}
		//get the screen coordinates
				int[] xarray3 = new int[s3.getNumOfSides()];
				xarray3 = s3.getX();
				System.out.println("This is the x-coordinate of shape in screen system");
				for (int i=0;i< xarray3.length;i++) 
				{
					System.out.println(xarray3[i]);
				}
			
				int[] yarray3 = new int[s3.getNumOfSides()];
				yarray3 = s3.getY();

				System.out.println("This is the y-coordinate of shape in screen system");
				for (int i=0;i< yarray3.length;i++) 
				{
					System.out.println(yarray3[i]);
				}
				
		//check whether in the shape
				System.out.println(s3.contains(-0.499999998, 0.86602540));
				System.out.println(s3.contains(0, 0));
				System.out.println(s3.contains(1, 1));
					
				
				
	}

}
