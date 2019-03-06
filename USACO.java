import java.util.Scanner;
import java.io.File;
public class USACO{
  public static int bronze(String filename) throws FileNotFoundException{
    File f = new File(filename);
    Scanner in = new Scanner(f);
    int rows, cols, elevFinal, numInstructions;
    int[][] instructions;
    int[][] terrain;
    String reading = in.nextLine();
    rows = reading.next();
    cols = reading.next();
    elevFinal = reading.next();
    numInstructions = reading.next();
    instructions = new int[numInstructions][3];
    terrain = new int[rows][cols];
    for (int r = 0; r< rows; r++){
      reading = in.nextLine()
      for (int c = 0; c<cols; c++){
        terrain[r][c] = Integer.parseInt(reading.next());
      }
    }
  }
}
