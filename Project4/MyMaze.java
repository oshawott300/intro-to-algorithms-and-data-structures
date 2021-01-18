// Names: Khoi Nguyen and John Lei
// x500s: NGUY3969

import java.util.Queue;
import java.util.Random;

public class MyMaze{
    Cell[][] maze;
    String[][] visual; //visual representation of board

    public MyMaze(int rows, int cols) {
        maze = new Cell[rows][cols];
        for(int i = 0; i<maze.length; i++){
            for(int j=0; j<maze[0].length; j++){
                maze[i][j] = new Cell();
            }
        }
    }

    public boolean inBounds(int x, int y){//helper function to check if the cell we are at is in bounds or not.
        return y>=0 && y<maze.length && x>=0 && x<maze[0].length;
    }

    public boolean allVisited(int x, int y){//helper function function to check all the neighbors and see if they are visited.
        if(inBounds(x-1,y) && !maze[y][x-1].getVisited()) {
            return false;
        }
        if(inBounds(x+1,y) && !maze[y][x+1].getVisited()){
            return false;
        }
        if(inBounds(x,y-1) && !maze[y-1][x].getVisited()){
            return false;
        }
        if(inBounds(x,y+1) && !maze[y+1][x].getVisited()){
            return false;
        }
        return true;
    }

    /* TODO: Create a new maze using the algorithm found in the writeup. */
    public static MyMaze makeMaze(int rows, int cols) {
        MyMaze newMaze = new MyMaze(rows, cols);
        Stack1Gen<int []> stack = new Stack1Gen();
        int y = 0;
        int x = 0;
        int[] indexArray = new int[2];//this is to push the indices to the stack
        indexArray[0] = 0;
        indexArray[1] = 0;
        newMaze.maze[y][x].setVisited(true);
        stack.push(indexArray);//pushing 0,0 into array
        while(!stack.isEmpty()){
            indexArray = stack.top();
            x = indexArray[0];
            y = indexArray[1];
            double position = Math.random();//needed to get random neighbor
            if(newMaze.allVisited(x,y)){
                stack.pop();
            }
            if (position >= 0 && position < 0.25) {
                if (newMaze.inBounds(x-1,y)) {//check if it goes out of bounds
                    if (newMaze.maze[y][x - 1].getVisited() == false) {//get the neighbor from the left of the cell
                        newMaze.maze[y][x - 1].setVisited(true);
                        newMaze.maze[y][x - 1].setRight(false);//get rid of left border
                        stack.push(new int[]{x-1,y});//push that neighbor's index to the stack
                    }
                }
            }
            if (position >= 0.25 && position < 0.5) {
                if (newMaze.inBounds(x,y-1)) {//check if it goes out of bounds
                    if (newMaze.maze[y - 1][x].getVisited() == false) {//get the neighbor to the top of the cell
                        newMaze.maze[y - 1][x].setVisited(true);
                        newMaze.maze[y - 1][x].setBottom(false);//get rid of top border
                        stack.push(new int[]{x,y-1});//push that neighbor's index to the stack
                    }
                }
            }
            if (position >= 0.5 && position < 0.75) {
                if (newMaze.inBounds(x,y+1)) {//check if it goes out of bounds
                    if (newMaze.maze[y + 1][x].getVisited() == false) {//get the neighbor to the bottom of the cell
                        newMaze.maze[y + 1][x].setVisited(true);
                        newMaze.maze[y][x].setBottom(false);//get rid of bottom border
                        stack.push(new int[]{x,y+1});//push that neighbor's index to the stack
                    }
                }
            }
            if (position >= 0.75 && position <= 1) {
                if (newMaze.inBounds(x+1,y)) {//check if it goes out of bounds
                    if (newMaze.maze[y][x + 1].getVisited() == false) {//get the neighbor right of the cell
                        newMaze.maze[y][x + 1].setVisited(true);
                        newMaze.maze[y][x].setRight(false);//get rid of bottom border
                        stack.push(new int[]{x+1,y});//push that neighbor's index to the stack
                    }
                }
            }
        }
        for(int i = 0; i<newMaze.maze.length; i++){//just sets everything to false
            for(int j = 0; j<newMaze.maze[0].length; j++){
                newMaze.maze[i][j].setVisited(false);
            }
        }
        return newMaze;
    }

    /* TODO: Print a representation of the maze to the terminal */
    public void printMaze (boolean path) {
        int rowCount = 0;
        String border = "";
        for (int k = 0; k<maze[0].length;k++){//creates the top border
            if(k == maze[0].length-1){
                border+="|---|";
            }else {
                border += "|---";
            }
        }
        border += "\n";
        String string = "";
        string += border;
        if (path == true) {
            while(rowCount!=maze.length){
                for(int i = 0; i<maze[0].length; i++){
                    if(i == 0 && rowCount == 0){//creates opening at the end of board
                        string += " ";
                    } else if(i==0) {//to get left border
                        string += "|";
                    }
                    if(maze[rowCount][i].getVisited()==true){//see if we can put an asterisk there
                        string += " *";
                    }
                    if(maze[rowCount][i].getVisited()==false){
                        string += "  ";
                    }
                    if(maze[rowCount][i].getRight()==true && !(rowCount == maze.length-1 && i == maze[0].length-1)){//see if we can get a right border there
                        string +=" |";
                    }
                    if(maze[rowCount][i].getRight()==false){
                        string +="  ";
                    }
                }
                string += "\n" + "|";//starts a new line so we can get the bottom
                for(int j = 0; j<maze[0].length;j++){
                    if(maze[rowCount][j].getBottom() == true){
                        string += "---|";//see if we can put a bottom border there
                    }
                    else{
                        string += "   |";
                    }
                }
                string += "\n";
                rowCount++;//move on to the next row

            }
            System.out.println(string);
        } else { //path == false, literally same code as path == true, but without checking for getVisited() == true. Just prints out the maze.
            while(rowCount!=maze.length){
                for(int i = 0; i<maze[0].length; i++){
                    if(i==0 && rowCount == 0){
                        string+= " ";
                    } else if(i==0){//to get left border
                        string += "|";
                    } else if(i==maze[0].length-1 && rowCount == maze.length-1){
                        string+= "";
                    }
                    if(maze[rowCount][i].getVisited()==false){
                        string += "  ";
                    }
                    if(maze[rowCount][i].getRight()==true && !(rowCount == maze.length-1 && i == maze[0].length-1)){
                        string +=" |";
                    }
                    if(maze[rowCount][i].getRight()==false){
                        string +="  ";
                    }
                }
                string += "\n" + "|";
                for(int j = 0; j <maze[0].length;j++){ //bottom of maze
                    if(maze[rowCount][j].getBottom() == true){
                        string += "---|";
                    }
                    else{
                        string += "   |";
                    }
                }
                string += "\n";
                rowCount++;

            }
            System.out.println(string);
        }
    }

    /* TODO: Solve the maze using the algorithm found in the writeup. */
    public void solveMaze() {
        Q1Gen <int[]> queue = new Q1Gen<>();
        queue.add(new int[]{0,0});
        maze[0][0].setVisited(true);
        while(!queue.isEmpty()){
            int[] indicies = queue.remove();//remove the first thing in queue.
            int x = indicies[0];
            int y = indicies[1];
            if(x == maze[0].length-1 && y == maze.length-1){//if the cell is at the end of the maze
                break;
            }
            if (inBounds(x-1,y)) {//left, to check if we can enqueue neighbors
                if (maze[y][x - 1].getVisited() == false && maze[y][x-1].getRight() == false) {
                    maze[y][x - 1].setVisited(true);
                    queue.add(new int[]{x-1,y});
                }
            }
            if (inBounds(x,y-1)) {//up
                if (maze[y - 1][x].getVisited() == false && maze[y-1][x].getBottom() == false) {
                    maze[y - 1][x].setVisited(true);
                    queue.add(new int[]{x,y-1});
                }
            }
            if (inBounds(x+1,y)) {//right
                if (maze[y][x + 1].getVisited() == false && maze[y][x].getRight() == false) {
                    maze[y][x + 1].setVisited(true);
                    queue.add(new int[]{x + 1, y});
                }
            }
            if (inBounds(x,y+1)) {//down
                if (maze[y + 1][x].getVisited() == false && maze[y][x].getBottom() == false) {
                    maze[y + 1][x].setVisited(true);
                    queue.add(new int[]{x,y+1});
                }
            }
        }
        printMaze(true);
    }

    public static void main(String[] args){
        /* Any testing can be put in this main function */
        MyMaze maze = makeMaze(5,20);//I think this is really all you need for testing, just a solved maze and the unsolved maze.
        maze.printMaze(false);//empty maze
        maze.solveMaze();//solved maze
    }
}
