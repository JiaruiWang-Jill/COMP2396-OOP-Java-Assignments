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

//test setTheta and getTheta
		shape.setTheta(-Math.PI / 2);
		System.out.println("theta is "+shape.getTheta());
		shape.setXc(0);
		shape.setYc(0);
		
		shape.setXLocal(new double[] {1,2,3});
		shape.setYLocal(new double[]{2,3,4});
//test getXLocal and getYLocal
		for(int a=0;a<shape.getXLocal().length;a++){
			System.out.println((a+1)+" vertice is"+"("+shape.getXLocal()[a]+","+shape.getYLocal()[a]+")");
		}
		
//square.translate(400,  100);
		shape.translate(400, 100);
		System.out.println("Translate the center by (400,100)");
		System.out.println("the center now is:"+"("+shape.getXc()+","+shape.getYc()+")");
		
//test getX and getY
		int array[]=shape.getX();
		int array1[]=shape.getY();
		for(int a=0;a<shape.getXLocal().length;a++){
			System.out.println((a+1)+" vertice is"+"("+array[a]          +","+array1[a]         +")");
		}		
		
	

	}

}
