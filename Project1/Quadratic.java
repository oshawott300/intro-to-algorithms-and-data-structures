//written by lei00007

public class Quadratic{
	public float a;
	public float b;
	public float c;


	public Quadratic(float a, float b, float c){
		this.a = a;
		this.b = b;
		this.c = c;

	}
	public void setA(float a){//setters and getters for the coefficients. Use for when i need to add, subtract, and compare the quadratic objects. 
		this.a = a;
	}
	public float getA(){
		return a;
	}
	public void setB(float b){
		this.b = b;
	}
	public float getB(){
		return b;
	}
	public void setC(float c){
		this.c = c;
	}
	public float getC(){
		return c;
	}
	public Quadratic add(Quadratic other){//using the getters to get the what I'm adding. 
		float adda = a + other.getA();
		float addb = b + other.getB();
		float addc = c + other.getC();
		return new Quadratic(adda, addb, addc);
	}
	public Quadratic subtract(Quadratic other){//same idea as add. 
		float suba = a - other.getA();
		float subb = b - other.getB();
		float subc = c - other.getC();
		return new Quadratic(suba, subb, subc);
	}
	public Roots findRoots(){//only makes a Roots object
		Roots equation = new Roots(a, b, c);
		return equation;

	}
	public String toString(){//use of parentheses for if the coefficients are negative. Mathematically makes sense if negative coefficients are in parentheses.
		String p = a + "x^2 + " + "(" + b + "x) + " + "(" + c + ")";
		return p;
	}
	public boolean equals(Object other){//Same format as the Complex4's equals() function on Canvas. 
		Quadratic temp;
		boolean same = false;
		if (other instanceof Quadratic){
			temp = (Quadratic) other;
			if (a == temp.getA() && b == temp.getB() && c== temp.getC())
				same = true;
		}
		return same;
	}

	public static void main(String[] args){
		Quadratic equa1 = new Quadratic(1,2,1);//Quadratic objects show cases where there are no imaginary roots and if there are imaginary roots. 
		System.out.println("First Quadratic:" + equa1.toString());
		System.out.println("First Quadratic Roots:" + "\n" + equa1.findRoots());
		Quadratic equa2 = new Quadratic(9,5,6);
		System.out.println("Second Quadratic:" + equa2.toString());
		System.out.println("Second Quadratic Roots:" + "\n" + equa2.findRoots());
		Quadratic equa3 = new Quadratic(1,2,1);//only used to check if the equals() method works. 
		System.out.println("Third Quadratic:" + equa3.toString());
		System.out.println("Does First Quadratic equal Second Quadratic??" + "\n" + equa1.equals(equa2));
		System.out.println("Does First Quadratic equal Third Quadratic??" + "\n" + equa1.equals(equa3));
		System.out.println("First Quadratic + Second Quadratic =" + equa1.add(equa2));//check if add() and subtract() works. 
		System.out.println("First Quadratic - Second Quadratic =" + equa1.subtract(equa2));
	}
}


