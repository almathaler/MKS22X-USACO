import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
public class USACO{
  public static void main(String[] args){
  try{
    System.out.println(bronze("makelake.1.in"));
  }catch(FileNotFoundException e){
    System.out.println("file not here");
  }
  }
  public static int bronze(String filename) throws FileNotFoundException{
    //read in the file
    File f = new File(filename);
    Scanner in = new Scanner(f);
    int rows, cols, elevFinal, numInstructions;
    int[][] instructions;
    int[][] terrain;
    rows = in.nextInt();
    cols = in.nextInt();
    elevFinal = in.nextInt();
    numInstructions = in.nextInt();
    instructions = new int[numInstructions][3]; //
    terrain = new int[rows][cols]; //
    for (int r = 0; r< rows; r++){
      for (int c = 0; c<cols; c++){
        terrain[r][c] = in.nextInt();
      }
    }
    for (int i = 0; i<numInstructions; i++){

      for (int c = 0; c<3; c++){
        instructions[i][c] = in.nextInt();
      }
    }

    //do the steps
    for (int[] instruction : instructions){
      int row_S = instruction[0] - 1;
      System.out.println("row_S: " + row_S);
      int col_S = instruction[1] - 1;
      System.out.println("col_S: " + col_S);
      int stompAmount = instruction[2];
      System.out.println("stompAmount: " + stompAmount);
      int highestNum = 0;
      //scan thru numbers to find highest one
      for (int rIncrease = 0; rIncrease<3; rIncrease++){
        for (int cIncrease = 0; cIncrease<3; cIncrease++){
          System.out.println("seeing if terrain" + (row_S + rIncrease) + ", " + (col_S+cIncrease)+ " is higher than " + highestNum);
          if (terrain[row_S + rIncrease][col_S+cIncrease] > highestNum){
            highestNum = terrain[row_S + rIncrease][col_S+cIncrease];
          }
        }
      }
      //now calculate what the highestnum - stompAmount is,
      int stomped = highestNum - stompAmount;
      //now go thru the 3x3 grid and modify everything higher than stomped to stomped
      for (int rIncrease = 0; rIncrease<3; rIncrease++){
        for (int cIncrease = 0; cIncrease<3; cIncrease++){
          if (terrain[row_S + rIncrease][col_S+cIncrease] > stomped){
            terrain[row_S + rIncrease][col_S+cIncrease] = stomped;
          }
        }
      }
      for (int lineOfTerrain = 0; lineOfTerrain < rows; lineOfTerrain++){
        System.out.println(Arrays.toString(terrain[lineOfTerrain]));
      }
    }
    //now find aggregated depths
    int aggDepth = 0;
    for (int row_ = 0; row_<rows; row_++){
      for (int col_ = 0; col_<cols; col_++){
        if (terrain[row_][col_] < elevFinal){
          aggDepth += terrain[row_][col_] - elevFinal;
        }
      }
    }
    return aggDepth * 72 * 72 * -1; //bc aggDepth is positive
  }
}
