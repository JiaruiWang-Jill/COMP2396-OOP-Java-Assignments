/**
 *CircleTester class is a class to test whether the Circle class work well.
 *We can test the translate(), getX(), getY(), rotate() and setVertices() 
 * 
 * @author jrwang
 *
 */
public class CircleTester {

	/**
	 *  Creates an instance of CircleTester class and check the Circle class.
	 * @param args
	 * 			not being used in this application.
	 */
	public static void main(String[] args) {
//create a circle;
		Circle circle = new Circle();
		circle.theta = -Math.PI / 2;
		System.out.println("theta is "+circle.theta);
		circle.xc = 0;
		circle.yc = 0;
//test setVertices
		circle.setVertices(50);
		System.out.println("We set the vertices by 50");
		for(int a=0;a<circle.xLocal.length;a++){
			System.out.println((a+1)+" vertice is"+"("+circle.xLocal[a]+","+circle.yLocal[a]+")");
		}
//test translate
		
		System.out.println("the local coordinate of the center:"+circle.xc+circle.yc);
		circle.translate(400,  100);
		System.out.println("Translate the center by (400,100)");
		System.out.println("the center now is:"+"("+circle.xc+","+circle.yc+")");
//test rotate as well as getX, getY
		
		circle.rotate(Math.PI/2);
		System.out.println("After rotate pi/2:");
		int array[]=circle.getX();
		int array1[]=circle.getY();
		for(int a=0;a<circle.xLocal.length;a++){
			System.out.println((a+1)+" vertice is"+"("+array[a]          +","+array1[a]         +")");
		}		
		

	}

}
