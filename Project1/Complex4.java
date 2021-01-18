//written by lei00007
//From Canvas on Dovolis's page
//Used to get Complex numbers for the roots. 

public class Complex4{
	private double realPart = 0;
	private double imaginaryPart = 0;

	public Complex4(double a, double b){
		realPart = a;
		imaginaryPart = b;

	}
	public Complex4(){}
	//acessor methods
	public void setRealPart(double value){
		realPart = value;
	}
	public void setImaginaryPart(double value){
		imaginaryPart = value;
	}

	public double getRealPart(){
		return realPart;
	}
	public double getImaginaryPart(){
		return imaginaryPart;
	}
	// operators
	public void addComplex(Complex4 c){
		realPart = realPart +c.getRealPart();
		imaginaryPart = imaginaryPart + c.getImaginaryPart();
	}
	public void subtractComplex(Complex4 c){
		realPart = realPart - c.getRealPart();
		imaginaryPart = imaginaryPart-c.getImaginaryPart();
	}
	public String toString(){
		return ""+ "(" + realPart + ") " + "+" + " (" + imaginaryPart + ")i";
	}
	public boolean equals(Object anotherObject){
		Complex4 temp;
		boolean same = false;
		if (anotherObject instanceof Complex4){
			temp = (Complex4) anotherObject;
			if (realPart == temp.getRealPart() && imaginaryPart == temp.getImaginaryPart())
				same = true;
		}
		return same;
	}
} //Complex4 class