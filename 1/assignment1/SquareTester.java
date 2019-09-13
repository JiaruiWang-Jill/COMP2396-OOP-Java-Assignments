/**
 *SquareTest class is a class to test whether the Square class work well.
 *We can test the translate(), getX(), getY(), rotate() and setVertices() 
 * 
 * @author jrwang
 *
 */
public class SquareTester {

	/**
	 * Creates an instance of the SquareTester class 
	 * @param args
	 * 		not being used in this application.
	 * */
	public static void main(String[] args) {

//create a Square
		Square square = new Square();
		square.theta = -Math.PI / 2;
		System.out.println("theta is "+square.theta);
		square.xc = 0;
		square.yc = 0;
//test setServices
		square.setVertices(50);
		System.out.println("We set the vertices by 50");
		for(int a=0;a<square.xLocal.length;a++){
			System.out.println((a+1)+" vertice is"+"("+square.xLocal[a]+","+square.yLocal[a]+")");
		}
		
//test translate 
		System.out.println("the local coordinate of the center:"+square.xc+square.yc);
		square.translate(400,  100);
		System.out.println("Translate the center by (400,100)");
		System.out.println("the center now is:"+"("+square.xc+","+square.yc+")");

// test rotate and getX, getY
		square.rotate(Math.PI/2);
		System.out.println("After rotate pi/2:");
		int array[]=square.getX();
		int array1[]=square.getY();
		for(int a=0;a<square.xLocal.length;a++){
			System.out.println((a+1)+" vertice is"+"("+array[a] +","+array1[a] +")");
		}		
		

	}

}
