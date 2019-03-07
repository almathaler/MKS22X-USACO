import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
public class USACO{
  public static void main(String[] args){
  try{
    bronze("makelake.1.in");
  }catch(FileNotFoundException e){
    System.out.println("file not here");
  }
  }
  public static int bronze(String filename) throws FileNotFoundException{
    File f = new File(filename);
    Scanner in = new Scanner(f);
    int rows, cols, elevFinal, numInstructions;
    int[][] instructions;
    int[][] terrain;
    //in.nextLine();
    rows = in.nextInt();//SHOULD BE R
    cols = in.nextInt();//SHOULD BE C
    elevFinal = in.nextInt();//SHOULD BE E
    numInstructions = in.nextInt(); //SHOULD BE N
    instructions = new int[numInstructions][3]; //
    terrain = new int[rows][cols]; //
    for (int r = 0; r< rows; r++){
      for (int c = 0; c<cols; c++){
        terrain[r][c] = in.nextInt(); //GO THRU THE NUMBERS
      }
    }
    for (int i = 0; i<numInstructions; i++){
    //  in.nextLine();
      for (int c = 0; c<3; c++){
        instructions[i][c] = in.nextInt();
      } //[i][0] = x coord [i][1] = y coord and [i][2] = num down
    }
    System.out.println("VARIABLES: ");
    System.out.println("rows, cols, elevation, numInstructions: " + rows + ", " +
                        cols + ", " + elevFinal + ", " + numInstructions);
    System.out.println("Terrain: ");
    for (int z = 0; z<rows; z++){
      System.out.println(Arrays.toString(terrain[z]));
    }
    System.out.println("Instructions: ");
    for (int y = 0; y<numInstructions; y++){
      System.out.println(Arrays.toString(instructions[y]));
    }
    //for compile
    return -1;
  }
}
