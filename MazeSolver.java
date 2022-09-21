/**
 * MazeSolver.java
 * 
 * It is a class data that storesa Maze and also storesthe data structures you will use to solve it, and useaStack and also a data structure to keep track of which cells in the Maze.
 *
 * 
 * 
 * CSC205
 */
import java.io.*;
import java.util.Scanner;

//Builds, solves and displays the process of solving a maze.
//Also, lets the user serialize the current state of the maze.
public class MazeSolver implements Serializable {

    //static variables that represent the menu commands
    public static final String QUIT_COMMAND = "q";
    public static final String SAVE_COMMAND = "s";
    //public static final char MOVE_COMMAND = "\n".charAt(0);

    //messages to display to the user
    public static final String QUIT_MESSAGE = "Program ended without solving.";
    public static final String GOAL_REACHED_MESSAGE = "Maze is solved.";

    //delay values
    public static final int BUILD_DELAY = 1;
    public static final int ANIMATION_DELAY = 1;

    private Maze maze;
    private boolean[][] visitedCells;
    private Stack<Maze.Direction> moveTraces;

    //the constructor; takes the number of rows and columns for the maze to be created
    public MazeSolver(int rows, int columns) {
        maze = new Maze(rows, columns);
        visitedCells = new boolean[rows][columns];
        moveTraces = new Stack();

        maze.buildMaze(BUILD_DELAY);
        maze.setSolveAnimationDelay(ANIMATION_DELAY);
    }

    //asks the user for commands available and solves the maze
    public void solve() {
        MazeDisplay mazeDisplay = new MazeDisplay(maze);

        //marking the starting position as "visited"
        visitedCells[maze.getCurrentRow()][maze.getCurrentCol()] = true;

        Scanner jin = new Scanner(System.in);

        System.out.println("Enter Q to quit, S to save to a file, or just ENTER to move.");

        //iterate untill the goal is reached
        do {
            //get the command from the console and convert it to lower case
            String command = jin.nextLine().toLowerCase();

            //choose the action to perform
            if(command.equals(QUIT_COMMAND))
            {
                System.out.println(QUIT_MESSAGE);
                break;
            }
            else if(command.equals(SAVE_COMMAND))
            {
                serialize(jin);
            }
            else
            {
                carryOnMoving();
            }

        } 
        while (!maze.goalReached());
        if (maze.goalReached())
        {
            System.out.println(GOAL_REACHED_MESSAGE);
        }

    }

    //serializes the object asking the user to enter the name of the file
    //to serialize to
    //takes a Scanner object as the parameter to recieve the console input
    private void serialize(Scanner jin) {
        System.out.print("Enter the name of the file to serialize to: ");
        String fileName = jin.nextLine();

        //java 8 feature that automatically closes IO streams after they are done
        try 
        {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName); 
            ObjectOutputStream fout = new ObjectOutputStream(fileOutputStream);
            fout.writeObject(this);
            System.out.println("The maze was successfully serialized.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //tries to move in each of the 4 directions
    //in case it is impossible, backtracks
    private void carryOnMoving() {
        //try to move UP
        if (!moveMaze(Maze.Direction.UP, false)) {
            //if cannot move UP, try to move RIGHT
            if (!moveMaze(Maze.Direction.RIGHT, false)) {
                //if cannot move RIGHT, try to move DOWN
                if (!moveMaze(Maze.Direction.DOWN, false)) {
                    //if cannot move DOWN, try to move LEFT
                    if (!moveMaze(Maze.Direction.LEFT, false)) {
                        //pops an entry from the stack
                        //and moves in the opposite direction
                        Maze.Direction oppositeDirection = 
                            getOppositeDirection(moveTraces.pop());
                        moveMaze(oppositeDirection, true);
                    }
                }
            }
        }
    }

    //tries to move the maze in the specified direction
    //returns boolean which indicates whether the maze has actually moved
    //if ignoreVisited is set to false, it will not move the maze to visited sells
    //otherwise, move to the cell even if it is marked as visited
    //this flag is required in order to be able to apply the same algorithm to move
    //and backtrack
    private boolean moveMaze(Maze.Direction direction, boolean ignoreVisited) {
        boolean mazeHasMoved = false;

        //if the maze faces the wall, just skip and return false
        if (maze.isOpen(direction)) {
            boolean isNextCellVisited = false;
            int row = maze.getCurrentRow();
            int column = maze.getCurrentCol();

            //alter the value of the current row or column depending on the direction
            // and determine if the cell to move the maze to was visited before
            switch (direction) {
                case UP:
                isNextCellVisited = visitedCells[--row][column];
                break;
                case RIGHT:
                isNextCellVisited = visitedCells[row][++column];
                break;
                case DOWN:
                isNextCellVisited = visitedCells[++row][column];
                break;
                case LEFT:
                isNextCellVisited = visitedCells[row][--column];
                break;
            }

            //changes various instance variables to reflect the change in state
            //and moves the maze
            //if ignoreVisited is set to true, move the maze regardless of whether
            //the next cell was visited
            //otherwise, move only if the next cell is free
            if (!isNextCellVisited || ignoreVisited) {
                //don't push onto the stack if backtracking
                if (!ignoreVisited) {
                    moveTraces.push(direction);
                }
                visitedCells[row][column] = true;
                maze.move(direction);
                mazeHasMoved = true;
            }
        }

        return mazeHasMoved;
    }

    //returns the opposite direction to the one passed as the argument
    private Maze.Direction getOppositeDirection(Maze.Direction direction) {
        switch (direction) {
            case UP:
            return Maze.Direction.DOWN;
            case RIGHT:
            return Maze.Direction.LEFT;
            case DOWN:
            return Maze.Direction.UP;
            case LEFT:
            return Maze.Direction.RIGHT;
        }

        return null;
    }
}
