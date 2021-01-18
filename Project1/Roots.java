//written by lei00007

public class Roots{
	public float a;
	public float b;
	public float c;
	public Complex4 RootA;
	public Complex4 RootB;
	
	public Roots(float a, float b, float c) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.RootA= RootA();//will be used to call Complex4's toString() method. 
		this.RootB =RootB();
	}
	
	public Complex4 RootA(){//since there are 2 roots, have two methods to find them. Since it's plus and minus the square root of the discriminant, this is when it is plus.
		float discriminantA = (float)(b*b) - (4*a*c);
		if (discriminantA<0){//when the discriminant is negative, use this to get the imaginary numbers.
			float absdiscriminantA = (float)Math.sqrt(discriminantA*(-1));//multiply by -1 to find the absolute value and square to discriminant. used for the imaginary part of Complex4. 
			float realNumberA = (float)(-1*b)/(2*a);//this is to get the real part of Complex 4. 
			Complex4 imagRootA = new Complex4(realNumberA, absdiscriminantA);
			return imagRootA;
		}
		else{//discriminant does not cause imaginary numbers.
			float answerA= (float)Math.sqrt(discriminantA);//didn't want to write the whole formula in one line. Would look messy, so broke it up into parts. 
			float answerA1 = (float)(-1*b)+answerA;
			float answerA2 = (float)answerA1/(2*a);
			Complex4 realRootA = new Complex4(answerA2, 0);//imaginary part equals 0, no imaginary numbers.
			return realRootA;

		}
	}
	
	public Complex4 RootB(){//exact same idea as RootA(), but it's when we minus the discriminant. 
		float discriminantB = (float)(b*b) - (4*a*c);
		if (discriminantB<0){
			float absdiscriminantB = (float)Math.sqrt(discriminantB*(-1));
			float realNumberB = (float)(-1*b)/(2*a);
			float absdiscriminantB_ = (float)(-1*absdiscriminantB);//mutliply by -1 since we are subtracting the discriminant in the quadratic formula. 
			Complex4 imagRootB = new Complex4(realNumberB, absdiscriminantB_);
			return imagRootB;

		}
		else{
			float answerB = (float)Math.sqrt(discriminantB);
			float answerB1 = (float)(-1*b)-answerB;
			float answerB2 = (float)answerB1/(2*a);
			Complex4 realRootB = new Complex4(answerB2, 0);
			return realRootB;


		}
	}
	public String toString(){//only want to see the non-imaginary/imaginary numbers, so I used Complex4's toString() method to see roots. 
		String p = "root1 = " + RootA.toString() + "\n"+"root2 =" + RootB.toString();
		return p; 
	}
	

}
