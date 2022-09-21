 /**
 * ResumeMazeSolver.java
 * 
 * This class will contain a main method that will read in a serialized MazeSolver (instead of creating a new one).
 * So it should ask the user to enter the name of the filethat contains the serialized class, then read it in and convert it to the existing MazeSolver. Then tell it to solve.
 * 
 * Ayoub Rammo
 * 
 * CSC205
 * 
 */
import java.io.*;
import java.util.Scanner;

//resumes solving of the maze by asking the user for the name of the file
//which stores a previously serialized object
public class ResumeSolvingMaze {

    public static void main(String[] args) {
        System.out.print("Enter the name of the file to deserialize from: ");

        Scanner jin = new Scanner(System.in);
        String fileName = jin.nextLine();

        //"try with resources", that enables automatic stream closing
        //after they are done
        try 
        {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream fin = new ObjectInputStream(fileInputStream);
            MazeSolver mazeSolver = (MazeSolver) fin.readObject();
            System.out.println("The maze was successfully deserialized.");
            mazeSolver.solve();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
