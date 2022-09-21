/**
 * StartSolvingMaze.java
 * 
 * StartSolvingMaze is the class should tell your MazeSolverinstance to.solve(), and this will contain a main method that will start the whole program.
 * 
 * Ayoub Rammo 
 * 
 * CSC205
 * 
 */
import java.util.Scanner;

//solves the maze by creating a new one with number of rows and columns
//specified by the user
public class StartSolvingMaze {
    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);

        System.out.print("Enter the number of rows: ");
        int numberOfRows = jin.nextInt();

        System.out.print("Enter the number of columns: ");
        int numberOfColumns = jin.nextInt();

        MazeSolver mazeSolver = new MazeSolver(numberOfRows, numberOfColumns);
        mazeSolver.solve();
    }
}
