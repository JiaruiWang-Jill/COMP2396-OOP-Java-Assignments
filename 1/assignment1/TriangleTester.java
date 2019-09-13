/**
 *TriangleTester class is a class to test whether the Triangle class work well.
 *We can test the translate(), getX(), getY(), rotate() and setVertices() 
 * 
 * @author jrwang
 *
 */
public class TriangleTester {

	/**
	 * Creates an instance of the TriangleTester class 
	 * @param 
	 * 		args not being used in this application.
	 */
	public static void main(String[] args) {
//create a triangle

		Triangle triangle = new Triangle();
		triangle.theta = -Math.PI / 2;
		System.out.println("theta is "+triangle.theta);
		triangle.xc = 0;
		triangle.yc = 0;
//test setVertices
		triangle.setVertices(50);
		System.out.println("We set the vertices by 50");
		for(int a=0;a<triangle.xLocal.length;a++){
			System.out.println((a+1)+" vertice is"+"("+triangle.xLocal[a]+","+triangle.yLocal[a]+")");
		}
//test translate	
		System.out.println("the local coordinate of the center:"+triangle.xc+triangle.yc);
		triangle.translate(400,  100);
		System.out.println("Translate the center by (400,100)");
		System.out.println("the center now is:"+"("+triangle.xc+","+triangle.yc+")");

//test rotate and getX, getY
		triangle.rotate(Math.PI/2);
		System.out.println("After rotate pi/2:");
		int array[]=triangle.getX();
		int array1[]=triangle.getY();
		for(int a=0;a<triangle.xLocal.length;a++){
			System.out.println((a+1)+" vertice is"+"("+array[a]          +","+array1[a]         +")");
		}		
		

	}

}
