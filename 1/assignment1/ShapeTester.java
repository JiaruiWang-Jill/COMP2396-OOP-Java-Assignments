/**
 *ShapeTester class is a class to test whether the Shape class work well.
 *We can test the translate(), getX(), getY()
 * 
 * @author jrwang
 *
 */
public class ShapeTester {

	/**
	 *  Creates an instance of the SahpeTester class 
	 * @param args
	 *			 not being used in this application.
	 */
	public static void main(String[] args) {

//create a shape;
		Shape shape = new Shape();
		shape.theta = -Math.PI / 2;
		System.out.println("theta is "+shape.theta);
		shape.xc = 0;
		shape.yc = 0;
		
		shape.xLocal = new double[] {1,2,3};
		shape.yLocal = new double[]{2,3,4};

		for(int a=0;a<shape.xLocal.length;a++){
			System.out.println((a+1)+" vertice is"+"("+shape.xLocal[a]+","+shape.yLocal[a]+")");
		}
		
//test translate		
		System.out.println("the local coordinate of the center:"+shape.xc+shape.yc);
		shape.translate(400,  100);
		System.out.println("Translate the center by (400,100)");
		System.out.println("the center now is:"+"("+shape.xc+","+shape.yc+")");
//test getX and getY
		int array[]=shape.getX();
		int array1[]=shape.getY();
		for(int a=0;a<shape.xLocal.length;a++){
			System.out.println((a+1)+" vertice is"+"("+array[a]          +","+array1[a]         +")");
		}		
		

	}

}
