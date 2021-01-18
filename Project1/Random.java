public class Random{
	private int p1;
	private int p2;
	private int m;
	private int seed; 
	private int[] array;
	public Random(int p1, int p2, int m){
		this.p1 = p1;
		this.p2 = p2;
		this.m = m;
		this.seed = 0;
	}
	public void setSeed(int seed){
		this.seed = seed;
	}
	public int getMaximum(){
		return m ;
	}
	public int random(){
		int randomNumber = ((p1*seed)+p2)%m;
		seed = randomNumber;//the seed will now equal the random number that is generated to be used as the old random number instead of 0.
		return randomNumber;
		
	}

	public int randomInteger(int lower, int upper){
        if (lower>upper){
        	int newlower = upper;//this will switch the bounds if the lower bound is greater than the upper bound. 
        	int newupper = lower;
        	int switchRangedInteger = (random()%(newupper -newlower+1)+newlower);//this generates the random number, mods it by the range so it can fit in the size of the bounds, and add the lower bound so the number is inside the bounds. 
        	return switchRangedInteger;
        }
        else{//if bounds are supposed the way they are, it'll run the same equation. 
        int rangedInteger = (random()%(upper-lower+1)+lower);
        return rangedInteger;
    }
        
	}
	
	public boolean randomBoolean(){
		if(random()%2==0){//shows that if the random number generate is even, then returns true. If not false, so it's a 50-50 chance of getting true or false. 
			return true;
		}
		else{
			return false;
		}
	}
	

	public double randomDouble(double lower, double upper){
		if(lower>upper){// same idea as randomInteger().
			double newlower = upper;
			double newupper = lower;
			double switchRangedDouble = ((((double)random()/m)*(newupper-newlower))+newlower);//divides random number by the max in order to get a double, multiplies it by the range so it fits the size of the bounds, and adds the lower bound so the number is inside the bounds.
		return switchRangedDouble;
	}
		else{
		double rangedDouble = ((((double)random()/m)*(upper-lower))+lower);
		return rangedDouble;
	}
	}
	
	
	
	public static void main(String args[]){
		Random randoNum = new Random(7919, 65537, 102611);
		String thing = "";
		int countTrue = 0;
		int countFalse = 0;
		System.out.println("Random number from 1-10:" + randoNum.randomInteger(1,10));// shows that random numbers in bounds are working. 
		System.out.println("Random number from 1-100:" + randoNum.randomInteger(1,100));
		System.out.println("Random number from 5-1000:" + randoNum.randomInteger(5,1000));
		System.out.println("Random number from 1-100, but bounds are flipped:" + randoNum.randomInteger(100,1));//shows that if flipped, it works. 
		System.out.println("Random double from 5-20:" + randoNum.randomDouble(5,20));
		System.out.println("Random double from 1-100:" + randoNum.randomDouble(1,100));
		System.out.println("List of 100 random ints in range 1-100");//prints 100 random numbers to show that it is actually giving out random numbers. 
		for(int i=0; i<101; i++){
			thing += i + ": " + randoNum.randomInteger(1,100) + "\n" ;
			}
		System.out.println(thing);
		for(int i=0; i<101; i++){ //shows random distribution of true and false. How many times I get true or false.  
			if(randoNum.randomBoolean() == true){
				countTrue ++;
			}
			else{
				countFalse++;
			}
			}
		System.out.println("# of times True and False are obtained when called 100 times");
		System.out.println("True:" + countTrue);
		System.out.println("False:" + countFalse);
		



		
	}
}