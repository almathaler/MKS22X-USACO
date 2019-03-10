import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
public class USACO{

  public static void main(String[] args){
    try{
      System.out.println(silver("ctravel.1.in"));
    }catch(FileNotFoundException e){
      //System.out.println("file not here");
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
      //System.out.println("row_S: " + row_S);
      int col_S = instruction[1] - 1;
      //System.out.println("col_S: " + col_S);
      int stompAmount = instruction[2];
      //System.out.println("stompAmount: " + stompAmount);
      int highestNum = 0;
      //scan thru numbers to find highest one
      for (int rIncrease = 0; rIncrease<3; rIncrease++){
        for (int cIncrease = 0; cIncrease<3; cIncrease++){
          //System.out.println("seeing if terrain" + (row_S + rIncrease) + ", " + (col_S+cIncrease)+ " is higher than " + highestNum);
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
        //System.out.println(Arrays.toString(terrain[lineOfTerrain]));
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
  public static int silver(String filename) throws FileNotFoundException{
    File f = new File(filename);
    Scanner in = new Scanner(f);
    int rows = in.nextInt();
    int cols = in.nextInt();
    int time = in.nextInt();
    in.nextLine();//idk why but need to move cursor down
    char[][] boardChars = new char[rows][cols];
    boolean[][] canUpdate = new boolean[rows][cols];
    for (int rr = 0; rr<rows; rr++){
      String process = in.nextLine();
      //System.out.println(process);
      boardChars[rr] = process.toCharArray();
      for (int cc = 0; cc<cols; cc++){ //setting up canUpdate to mean only non-trees can update
        if (boardChars[rr][cc] != '*'){
          canUpdate[rr][cc] = true;
        }else{
          canUpdate[rr][cc] = false;
        }
      }
    }
    int rowStart = in.nextInt() - 1;
    int colStart = in.nextInt() - 1;
    int rowEnd = in.nextInt() - 1;
    int colEnd = in.nextInt() - 1;
    int[][] boardA = new int[rows][cols];
    int[][] boardB = new int[rows][cols];
    for (int row = 0; row<rows; row++){
      for (int col = 0; col<cols; col++){
        boardA[row][col] = 0;
        boardB[row][col] = 0;
        if (row == rowStart && col == colStart){
          boardA[row][col] = 1;
        }
      }
    }//initialized boards
    int toReturn = boardA[rowEnd][colEnd]; // this is the val of toReturn
    for (int curTime = 1; curTime <= time; curTime++){//idt it makes a difference to count back, if time is 0 won't enter loop otherwise makes no diff
      if (curTime%2 == 0){ //modify boardA
        System.out.println("\n" + curTime + "printing boardA");
        //System.out.println(Arrays.toString(boardA));
        for (int r = 0; r<rows; r++){
          System.out.println(Arrays.toString(boardA[r]));
          for (int c = 0; c<cols; c++){
            if (boardB[r][c] == 0 && canUpdate[r][c]){ //means can be modified in other board
              try{
                boardA[r][c] += boardB[r+1][c];
              }catch (IndexOutOfBoundsException e){

              }try{
                boardA[r][c] += boardB[r-1][c];
              }catch (IndexOutOfBoundsException e){

              }try{
                boardA[r][c] += boardB[r][c+1];
              }catch (IndexOutOfBoundsException e){

              }try{
                boardA[r][c] += boardB[r][c-1];
              }catch (IndexOutOfBoundsException e){

              }
              /* shouldn't be like this, will do diagonals, do 4 seperate try catchs
              for (int rC = 0; rC<2; rC++){
                for (int cC = 0; cC<2; cC++){
                  try{
                    boardA[r][c]+=boardB[r+rC][c+cC];
                  }catch (IndexOutOfBoundsException e){
                    //don't do anything, means at edge
                  }
                }
              }
              */
            }
          }
        }
        toReturn = boardA[rowEnd][colEnd];
      }else{ //modify boardB
        System.out.println("\n" + curTime + "printing boardB");
        //System.out.println(Arrays.toString(boardB));
        for (int r = 0; r<rows; r++){
          System.out.println(Arrays.toString(boardB[r]));
          for (int c = 0; c<cols; c++){
            if (boardA[r][c] == 0 && canUpdate[r][c]){ //means can be modified in other board
              try{
                boardB[r][c] += boardA[r+1][c];
              }catch (IndexOutOfBoundsException e){

              }try{
                boardB[r][c] += boardA[r-1][c];
              }catch (IndexOutOfBoundsException e){

              }try{
                boardB[r][c] += boardA[r][c+1];
              }catch (IndexOutOfBoundsException e){

              }try{
                boardB[r][c] += boardA[r][c-1];
              }catch (IndexOutOfBoundsException e){

              }
            }
          }
        }
        toReturn = boardB[rowEnd][colEnd];
      }
    }
    return toReturn;
  }
}
