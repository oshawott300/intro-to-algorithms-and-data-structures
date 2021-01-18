import java.util.Scanner;
import java.util.Arrays;
public class BattleBoatsGame{
	public static void main(String[] args){
		while(true){//used if the user puts an invalid input to enter standard or expert mode. 
		System.out.println("Welcome to BattleBoats! Please enter Standard for standard mode or Expert for expert mode");
		Scanner s = new Scanner(System.in);
		String arrayInput = s.nextLine();
		if(arrayInput.equals("Standard") || arrayInput.equals("standard")){
			BattleBoatsBoard standardBoard = new BattleBoatsBoard(8, 5, 1, 1);//creates the board and things involving things that will be counted.
			standardBoard.placeBoats(5);
			//System.out.println("hello");
			standardBoard.placeBoats(4);
			standardBoard.placeBoats(3);
			standardBoard.placeBoats(3);
			standardBoard.placeBoats(2);
			standardBoard.display();
			System.out.println("There are " + standardBoard.shipsRemain + " boats on the board");
			//standardBoard.displayBattleBoats();//meant for testing
			while(standardBoard.shipsRemain !=0){//if boats still remain, repeats back to the enter command line. 
				System.out.println("Enter a command (fire, print, missile, drone) to use: ");
				Scanner a = new Scanner(System.in);
				String commandInput = a.nextLine();
				if(commandInput.equals("fire")){//if the input the fire, use the fire function. 
					System.out.println("Enter X cooridnate to fire(0-7):");
					Scanner b = new Scanner(System.in);
					int xCoor = b.nextInt();
					System.out.println("Enter Y cooridnate to fire(0-7):");
					Scanner c = new Scanner(System.in);
					int yCoor = c.nextInt();
					standardBoard.fire(xCoor, yCoor);
					standardBoard.display();
					//standardBoard.displayVisualBoard();//meant for testing
				}
				if(commandInput.equals("print")){// input is print, use print function
					standardBoard.print();
				}
				if(commandInput.equals("missile")){//input is missile, use missile function.
					System.out.println("Enter X cooridnate to fire(0-7):");
					Scanner d = new Scanner(System.in);
					int xCoord = d.nextInt();
					System.out.println("Enter Y cooridnate to fire(0-7):");
					Scanner e = new Scanner(System.in);
					int yCoord = e.nextInt();
					standardBoard.missile(xCoord, yCoord);
					standardBoard.display();
				}
				if(commandInput.equals("drone")){//input is drone, use drone function. 
					System.out.println("Enter direction. 1 for row, 2 for column");
					Scanner f = new Scanner(System.in);
					int direction = f.nextInt();
					System.out.println("Enter index(which row or column you want to check)(0,7)");
					Scanner g = new Scanner(System.in);
					int index = g.nextInt();
					standardBoard.drone(direction, index);
					standardBoard.display();
				}

				
			}
			System.out.println("Congrats!! All ships are sunk");
			System.out.println("Turns: " + standardBoard.turns);
			System.out.println("Shots: " + standardBoard.shots);
			break;//ends the game



		}
		else if(arrayInput.equals("Expert") || arrayInput.equals("expert")){
			BattleBoatsBoard expertBoard = new BattleBoatsBoard(12, 10, 2, 2);//exact same format as standard, but with different amount of things to count and the board size. 
			expertBoard.placeBoats(5);
			expertBoard.placeBoats(5);
			//System.out.println("hello");
			expertBoard.placeBoats(4);
			expertBoard.placeBoats(4);
			expertBoard.placeBoats(3);
			expertBoard.placeBoats(3);
			expertBoard.placeBoats(3);
			expertBoard.placeBoats(3);
			expertBoard.placeBoats(2);
			expertBoard.placeBoats(2);
			expertBoard.display();
			System.out.println("There are " + expertBoard.shipsRemain + " boats on the board");
			//expertBoard.displayBattleBoats();//meant for testing
			while(expertBoard.shipsRemain !=0){
				System.out.println("Enter a command (fire, print, missile, drone) to use: ");
				Scanner a = new Scanner(System.in);
				String commandInput = a.nextLine();
				if(commandInput.equals("fire")){
					System.out.println("Enter X cooridnate to fire(0-7):");
					Scanner b = new Scanner(System.in);
					int xCoor = b.nextInt();
					System.out.println("Enter Y cooridnate to fire(0-7):");
					Scanner c = new Scanner(System.in);
					int yCoor = c.nextInt();
					expertBoard.fire(xCoor, yCoor);
					expertBoard.display();
					//expertBoard.displayVisualBoard();//meant for testing
				}
				if(commandInput.equals("print")){
					expertBoard.print();
				}
				if(commandInput.equals("missile")){
					System.out.println("Enter X cooridnate to fire(0-7):");
					Scanner d = new Scanner(System.in);
					int xCoord = d.nextInt();
					System.out.println("Enter Y cooridnate to fire(0-7):");
					Scanner e = new Scanner(System.in);
					int yCoord = e.nextInt();
					expertBoard.missile(xCoord, yCoord);
					expertBoard.display();
				}
				if(commandInput.equals("drone")){
					System.out.println("Enter direction. 1 for row, 2 for column");
					Scanner f = new Scanner(System.in);
					int direction = f.nextInt();
					System.out.println("Enter index(which row or column you want to check)(0-7)");
					Scanner g = new Scanner(System.in);
					int index = g.nextInt();
					expertBoard.drone(direction, index);
					expertBoard.display();
				}

				
			}
			System.out.println("Congrats!! All ships are sunk");
			System.out.println("Turns: " + expertBoard.turns);
			System.out.println("Shots: " + expertBoard.shots);
			break;
		}
		else{
			System.out.println("Invalid input. Try Again.");

		}
	}
		

	}
}