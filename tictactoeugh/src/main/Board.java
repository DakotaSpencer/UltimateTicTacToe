package main;

public class Board {
    private int Dimensions;
    private String[][] Grid;
    public Board(int dimensions) {
        setDimensions(dimensions);
    }

    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < Dimensions; i++) {
            System.out.print("| ");

            for (int j = 0; j < Dimensions; j++) {
                if (Grid[i][j] == null){
                    System.out.print("-" + " | ");
                }else {
                    System.out.print(Grid[i][j] + " | ");
                }
            }
            System.out.println("\n-------------");
        }
    }
    public boolean checkRows() {
        for(int i=0; i<Dimensions; i++)
        {
//            System.out.println("CheckRows Checks:");
//            System.out.println(i +",0" +  " : "+Grid[i][0]);
//            System.out.println(i +",1" +  " : "+Grid[i][1]);
//            System.out.println(i +",2" +  " : "+Grid[i][2]);
            if( (Grid[i][0]==Grid[i][1]) && (Grid[i][1]==Grid[i][2]) && Grid[i][0] !=null)
                return true;
        }
        return false;
    }

    public boolean checkCols() {
        for(int i=0; i<Dimensions; i++)
        {
//            System.out.println("CheckCols Checks:");
//            System.out.println("0," + i + " : "+Grid[0][i]);
//            System.out.println("1," + i + " : "+Grid[1][i]);
//            System.out.println("2," + i + " : "+Grid[2][i]);
            if( (Grid[0][i]==Grid[1][i]) && (Grid[1][i]==Grid[2][i])&& Grid[0][i] !=null)
                return true;
        }
        return false;
    }

    public boolean checkDiags(){
//        System.out.println("CheckDiags Checks:");
//        System.out.println("0,0: " + Grid[0][0]);
//        System.out.println("1,1: " + Grid[1][1]);
//        System.out.println("2,2: " + Grid[2][2]);

        if( (Grid[0][0]==Grid[1][1]) && (Grid[1][1]==Grid[2][2]) && Grid[0][0] !=null)
            return true;
        else if ((Grid[0][2]==Grid[1][1]) && (Grid[1][1]==Grid[2][0]) && Grid[1][1] !=null)
            return true;
        else
            return false;
    }

    public boolean checkHit() {

        if(checkRows() || checkCols() || checkDiags())
            return true;
        else
            return false;
    }

    public boolean isFree (int row, int col) {
        if(Grid[row][col] == null)
            return true;
        else
            return false;
    }

    public void setDimensions(int dimensions) {
        Dimensions = dimensions;
        Grid = new String[dimensions][dimensions];
    }

    public void setGridSquare(int row, int col, String set) {
        Grid[row][col] = set;
    }
}
