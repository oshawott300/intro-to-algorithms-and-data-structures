//Written by lei00007 and gebre109
//Submitted by lei00007
import java.util.Scanner;
public class BattleBoatsBoard{
	public int[][] battleBoatsArray;//the board that will contain the boats
	public int[][] visualBoard;//will represent what the user sees on the screen
	public int[][] droneBoard;//only meant for the drone function
	public int shots;
	public int turns;
	public int shipsRemain;
	public int size;//represents the size of the board
	public int numShips;
	public int count5;//use counts for when a boat sinks
	public int count4;
	public int count3;
	public int count2;
	public int missileCount;
	public int droneCount;
	public BattleBoatsBoard(int size, int numShips, int missileCount, int droneCount){//when declared, depending on if you're in standard mode or expert mode, will make the size of the board, hold the number of ships, and the amount of times you can use the powers.

		this.shots = 0;
		this.turns = 0;
		this.shipsRemain = numShips;
		this.numShips = numShips;
		this.size = size;
		battleBoatsArray = new int[size][size];//makes the board with a size depending on if it's in standard or expert mode. 
		visualBoard = new int[size][size];
		droneBoard = new int[size][size]; 
		this.count5 = 10;// Again, counts used for when boat sinks. Count value is determined by the max amount of hits a boat can take in Expert. So since there are two boats for size 5, then the max amount of hits is 10. See fire function for more details. 
		this.count4 = 8;
		this.count3 = 12;
		this.count2 = 4;
		this.missileCount = missileCount;
		this.droneCount = droneCount;
		
		}
	
	public boolean checkIfOpen(int x, int y, int boatSize, double orientation){//helper function to see if a boat can be placed in the given coordinates.

		for(int i = 0; i<boatSize; i++){
			if(orientation < 0.5){//if placed vertically
				if(y >= battleBoatsArray.length || battleBoatsArray[y][x] != 0){//if there is already a boat there or the boat is outside the board, returns false.
					return false;
				}
				y++;
			}
			if(orientation >= 0.5){//if placed horizontally
				if(x >= battleBoatsArray.length || battleBoatsArray[y][x] != 0){
					return false;
				}
				x++;
			}
		}
		return true; //if it passes all if statements, then the boat can be placed.
	}
	public void placeBoats(int boatSize){
		int xPosition = (int)Math.floor(Math.random()*size);//declares a random x and y coordinate, as well as random number for orientation of the boats(placed vertically or horizontally)
		int yPosition = (int)Math.floor(Math.random()*size);
		double orientation = Math.random();

		while (true){
			if(checkIfOpen(xPosition, yPosition, boatSize, orientation)==true){// if the helper function has it where the boat can be positioned in all spots.
				for(int i = 0; i<boatSize; i++){
					battleBoatsArray[yPosition][xPosition] = boatSize;//will then place the boat where each spot of the boat equals the value of the size of the boat
					droneBoard[yPosition][xPosition] = boatSize;//will be the exact same for the board when we use the drone function.
					if(orientation<0.5){
						yPosition++;
					}
					if(orientation>=0.5){
						xPosition++;
					}
				}
				break;
			}
			else{
				xPosition = (int)Math.floor(Math.random()*size);//if helper function returns false, reset the coordinates and try again. 
				yPosition = (int)Math.floor(Math.random()*size);
			}
		}
	}
	

	public void fire(int x, int y){
		int outsideCheck = 0;//to see if the coordinates entered by the user will be outside the board. 
		if(y<0){
			outsideCheck = 1;
		}
		if(y>=visualBoard.length){
			outsideCheck = 1;
		}
		if(x<0){
			outsideCheck = 1;
		}
		if(x>=visualBoard.length){
			outsideCheck = 1;
		}

	if(outsideCheck == 1){// if coordinates are outside the board, then will cause penalties. 
		if(y<0){
			System.out.println("penalty");
			turns+=2;//causes a turn to be skipped. 
			
		}
		if(y>=visualBoard.length){
			System.out.println("penalty");
			turns+=2;
			
		}
		if(x<0){
			System.out.println("penalty");
			turns+=2;
			
		}
		if(x>=visualBoard.length){
			System.out.println("penalty");
			turns+=2;
			
		}
	}
	else{
		if(visualBoard[y][x] != 0){//this means that they are firing at a place where it has already been shot. 
			System.out.println("penalty");
			turns+=2;
			
		}
		if(battleBoatsArray[y][x] == 0){//if board's value is a 0, then it is a miss. 
			System.out.println("miss");
			turns++;
			shots++;
			visualBoard[y][x] = -1;//indicates a miss for the user to see. See print and display functions. 
			}
		if(battleBoatsArray[y][x] != 0){// the board where the boats are placed, if the value doesn't equal 0 (since 0's mean mothing is there), then it's a hit. 
			System.out.println("hit");
			turns++;
			shots++;
			visualBoard[y][x] = battleBoatsArray[y][x];//the board where is boats are and the user's visual board will equal the same number at that same location. See print and display functions.

			if(battleBoatsArray[y][x]==5){// indicates a sink when the counts listed above are divisible by the size. If it is, then it has sunk. Decrements the value everytime the boat has been hit. 
				count5--;
				droneBoard[y][x] = 0;
				if(count5 % 5 == 0){
					System.out.println("sink");
					shipsRemain--;
					System.out.println(shipsRemain + " ships left");
				}
			}
			if(battleBoatsArray[y][x]==4){
				count4--;
				droneBoard[y][x] = 0;
				if(count4 % 4 == 0){
					System.out.println("sink");
					shipsRemain--;
					System.out.println(shipsRemain + " ships left");
				}
			}
			if(battleBoatsArray[y][x]==3){
				count3--;
				droneBoard[y][x] = 0;
				if(count3 % 3 == 0){
					System.out.println("sink");
					shipsRemain--;
					System.out.println(shipsRemain + " ships left");
				}
			}
			if(battleBoatsArray[y][x]==2){
				count2--;
				droneBoard[y][x] = 0;
				if(count2 % 2 == 0){
					System.out.println("sink");
					shipsRemain--;
					System.out.println(shipsRemain + " ships left");
				}
			}
		
		}
	}
	}


	
	public void display(){//with visual board, shows where things have been hit, missed, or not yet been fired at. 
		for(int i =0; i<visualBoard.length; i++){//used a different board so I can change values to indicate these outcomes.
			for(int j=0; j<visualBoard.length; j++){
				if(visualBoard[i][j] == 0){
					System.out.print("-" + " ");
				}
				if(visualBoard[i][j] == -1){
					System.out.print("O" + " ");
				}
				if(visualBoard[i][j] != 0 && visualBoard[i][j]>0){
					System.out.print("X" + " ");
				}
			}
			System.out.println();
		}
	}
	public void print(){
		for(int i =0; i<visualBoard.length; i++){
			for(int j=0; j<visualBoard.length; j++){
				if(visualBoard[i][j] == 0){
					System.out.print("-" + " ");
				}
				if(visualBoard[i][j] == -1){
					System.out.print("O" + " ");
				}
				if(visualBoard[i][j] == 5){
					System.out.print("5" + " ");//same as display function but will print ou tth eboat numbers corresponding to their size.
				}
				if(visualBoard[i][j] == 4){
					System.out.print("4" + " ");
				}
				if(visualBoard[i][j] == 3){
					System.out.print("3" + " ");
				}
				if(visualBoard[i][j] == 2){
					System.out.print("2" + " ");
				}
			}
			System.out.println();
		}
	}
	public void missilefire(int x, int y){//basically the same as the fire function, but will not penalize if the surrounding shots are outside the board or hit a boat that is already hit. 
		int outsideCheck = 0;
		if(y<0){
			outsideCheck = 1;
		}
		if(y>=visualBoard.length){
			outsideCheck = 1;
		}
		if(x<0){
			outsideCheck = 1;
		}
		if(x>=visualBoard.length){
			outsideCheck = 1;
		}
		if(visualBoard[y][x] != 0){
			outsideCheck = 1;
		}
	if(outsideCheck == 1){
		if(y<0){
			System.out.println(x +"," + y +" does not exist");
			}
		if(y>=visualBoard.length){
			System.out.println(x +"," + y +" does not exist");
		}
		if(x<0){
			System.out.println(x +"," + y +" does not exist");
		}
		if(x>=visualBoard.length){
			System.out.println(x +"," + y +" does not exist");
		}
		if(visualBoard[y][x] != 0){
			System.out.println(x + "," + y+ "already has been hit");
	}
}
	else{
		if(battleBoatsArray[y][x] == 0){
			System.out.println(x +"," + y + ": miss");
			visualBoard[y][x] = -1;
		}
		if(battleBoatsArray[y][x] != 0){
			System.out.println(x +"," + y + ": hit");
			visualBoard[y][x] = battleBoatsArray[y][x];
			if(battleBoatsArray[y][x]==5){
				count5--;
				droneBoard[y][x] = 0;
				if(count5 % 5 == 0){
					System.out.println("sink");
					shipsRemain--;
					System.out.println(shipsRemain + " ships left");
				}
			}
		
			if(battleBoatsArray[y][x]==4){
				count4--;
				droneBoard[y][x] = 0;
				if(count4 % 4 == 0){
					System.out.println("sink");
					shipsRemain--;
					System.out.println(shipsRemain + " ships left");
				}
			}
			if(battleBoatsArray[y][x]==3){
				count3--;
				droneBoard[y][x] = 0;
				if(count3 % 3 == 0){
					System.out.println("sink");
					shipsRemain--;
					System.out.println(shipsRemain + " ships left");
				}
			}
			if(battleBoatsArray[y][x]==2){
				count2--;
				droneBoard[y][x] = 0;
				if(count2 % 2 == 0){
					System.out.println("sink");
					shipsRemain--;
					System.out.println(shipsRemain + " ships left");
				}
			}
		}
		}
	}

	public void missile(int x, int y){
		int validinput = 0;
		int outsideCheck = 0; 
		if(missileCount != 0){//when you can still use the missile
		if(y>=battleBoatsArray.length){//check if input is outside the board
			System.out.println("Invalid Coordinate");
			outsideCheck = 1;
		}
		if(x>=battleBoatsArray.length){
			System.out.println("Invalid Coordinate");
			outsideCheck = 1;
		}
		
		if(y<0){
			System.out.println("Invalid Coordinate");
			outsideCheck = 1;
		}
		if(x<0){
			System.out.println("Invalid Coordinate");
			outsideCheck = 1;
		}
		if(visualBoard[y][x] != 0){
			System.out.println("Already been hit");
			outsideCheck = 1;
		}
		if(outsideCheck == 0){
		turns++;
		shots++;
		missilefire(x, y);//will fire shots at surrounding coordinates
		missilefire(x-1, y);
		missilefire(x, y-1);
		missilefire(x-1, y-1);
		missilefire(x+1, y);
		missilefire(x, y+1);
		missilefire(x+1, y+1);
		missilefire(x-1, y+1);
		missilefire(x+1, y-1);
		missileCount--;
		System.out.println(missileCount + " uses left");
	}
	}
	else{
		System.out.println("Cannot use anymore. Ran out of uses");
	}
}

	public void drone(int direction, int index){
		if(droneCount != 0){//when you can still use the drone
		int rowCount = 0;
		int coluCount = 0;
		int validInput = 0;
		if(direction != 1 && direction != 2){
			validInput = 1;
			System.out.println("Invalid input(s), please enter drone again and enter valid input(s).");//checks for invalid inputs for the direction or the index.
		}
		if(index<0 || index>=battleBoatsArray.length){
			validInput = 1;
			System.out.println("Invalid input(s), please enter drone again and enter valid input(s).");
		}
		if(validInput == 0){
		if(direction == 1){//check the whole row
			turns++;
			droneCount--;
			for(int i=0; i<battleBoatsArray.length; i++){
				if (droneBoard[index][i] != 0){
					rowCount++;
				}
			}
		System.out.println(rowCount + " targets found");
		System.out.println(droneCount + " uses left");
		}
		if(direction == 2){//check the column
			turns++;
			droneCount--;
			for(int i=0; i<battleBoatsArray.length; i++){
				if (droneBoard[i][index] != 0){
					coluCount++;
				}
			}
		System.out.println(coluCount + " targets found");
		System.out.println(droneCount + " uses left");
		}
	
	else{
		System.out.println("Cannot use anymore. Ran out of uses");
	}
}
}




	}

	public void displayBattleBoats(){// helper function to let me see where the boats are for faster testing. 
		for(int i =0; i<battleBoatsArray.length; i++){
			for(int j=0; j<battleBoatsArray.length; j++){
				System.out.print(battleBoatsArray[i][j] + " ");
				}
			
			System.out.println();
		}
		}
	public void displayVisualBoard(){// helper function to let me see what the user will see. Used for testing.
		for(int i =0; i<battleBoatsArray.length; i++){
			for(int j=0; j<battleBoatsArray.length; j++){
				System.out.print(visualBoard[i][j] + " ");
				}
			
			System.out.println();
		}
		}
	

}
	
	
	







