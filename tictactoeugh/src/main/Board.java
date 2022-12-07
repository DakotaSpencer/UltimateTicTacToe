package main;

import java.util.Random;

public class Board {
    private int Dimensions;
    private String[][] Grid;

    public Board(int dimensions) {
        setDimensions(dimensions);
    }

    public static int rng(int bound){
        Random rng = new Random();
        int num1 = rng.nextInt(bound);
        System.out.println(num1);
        return num1;
    }

    public void printBoard() {
//        System.out.println("-------------");
        for (int i = 0; i < Dimensions; i++) {
            System.out.print("| ");

            for (int j = 0; j < Dimensions; j++) {
                if (Grid[i][j] == null){
                    System.out.print("-" + " | ");
                } else {
                    System.out.print(Grid[i][j] + " | ");
                }
            }
            //System.out.println("\n-------------");
            System.out.println("\n");
        }
    }
//    public boolean checkRows() {
//        for(int i=0; i<Dimensions; i++)
//        {
////            System.out.println("CheckRows Checks:");
////            System.out.println(i +",0" +  " : "+Grid[i][0]);
////            System.out.println(i +",1" +  " : "+Grid[i][1]);
////            System.out.println(i +",2" +  " : "+Grid[i][2]);
//            if( (Grid[i][0]==Grid[i][1]) && (Grid[i][1]==Grid[i][2]) && Grid[i][0] !=null)
//                return true;
//        }
//        return false;
//    }
//
//    public boolean checkCols() {
//        for(int i=0; i<Dimensions; i++)
//        {
////            System.out.println("CheckCols Checks:");
////            System.out.println("0," + i + " : "+Grid[0][i]);
////            System.out.println("1," + i + " : "+Grid[1][i]);
////            System.out.println("2," + i + " : "+Grid[2][i]);
//            if( (Grid[0][i]==Grid[1][i]) && (Grid[1][i]==Grid[2][i])&& Grid[0][i] !=null)
//                return true;
//        }
//        return false;
//    }
//
//    public boolean checkDiags(){
////        System.out.println("CheckDiags Checks:");
////        System.out.println("0,0: " + Grid[0][0]);
////        System.out.println("1,1: " + Grid[1][1]);
////        System.out.println("2,2: " + Grid[2][2]);
//
//        if( (Grid[0][0]==Grid[1][1]) && (Grid[1][1]==Grid[2][2]) && Grid[0][0] !=null)
//            return true;
//        else if ((Grid[0][2]==Grid[1][1]) && (Grid[1][1]==Grid[2][0]) && Grid[1][1] !=null)
//            return true;
//        else
//            return false;
//    }

    //NEW METHOD
//    public boolean checkHit(String symbol) {
//        if ((Grid[0][0] == symbol && Grid [0][1] == symbol && Grid [0][2] == symbol) ||
//                (Grid[1][0] == symbol && Grid [1][1] == symbol && Grid [1][2] == symbol) ||
//                (Grid[2][0] == symbol && Grid [2][1] == symbol && Grid [2][2] == symbol) ||
//
//                (Grid[0][0] == symbol && Grid [1][0] == symbol && Grid [2][0] == symbol) ||
//                (Grid[0][1] == symbol && Grid [1][1] == symbol && Grid [2][1] == symbol) ||
//                (Grid[0][2] == symbol && Grid [1][2] == symbol && Grid [2][2] == symbol) ||
//
//                (Grid[0][0] == symbol && Grid [1][1] == symbol && Grid [2][2] == symbol) ||
//                (Grid[0][2] == symbol && Grid [1][1] == symbol && Grid [2][0] == symbol) ) {
//            System.out.println("Player " + symbol + " wins!");
//            return true;
//        }else{
//            return false;
//        }
//    }

    public boolean checkRows(int row, String target) {
        for(int i = 0; i < Dimensions; i++) {
            if(Grid[row][i] == null || !Grid[row][i].equals(target)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkCols(int col, String target) {
        for(int i = 0; i < Dimensions; i++) {
            if(Grid[i][col] == null || !Grid[i][col].equals(target)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkDiags(String target) {
        int x = 0;
        boolean thisTrue = true;
        for(int i = 0; i < Dimensions; i++) {
            if(Grid[i][x] == null || !Grid[i][x].equals(target)) {
                thisTrue = false;
            }
            x++;
        }

        if(thisTrue) {
            return true;
        }

        thisTrue = true;
        x = Dimensions-1;
        for(int i = 0; i < Dimensions; i++) {
            if(Grid[i][x] == null || !Grid[i][x].equals(target)) {
                return false;
            }
            x--;
        }

        return true;
    }

    //OLD METHOD
    public boolean checkHit(int row, int col) {
        String target = Grid[row][col];

        if(checkRows(row, target) || checkCols(col, target) || checkDiags(target)) {
            return true;
        }
        return false;
//        if(checkRows() || checkCols() || checkDiags())
//            return true;
//        else
//            return false;
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

    public int getDims() {
        return Dimensions;
    }

    public String[][] getGrid() {
        return Grid;
    }

    public void setGridSquare(int row, int col, String set) {
        Grid[row][col] = set;
    }

    public void blockRandomSpace() {
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";
        int empty = 0;
        
        for(int a = 0; a < Dimensions; a++) {
            for(int b = 0; b < Dimensions; b++) {
                if(Grid[a][b] == null) empty++;
            }
        }
        
        int empty2 = 0;
        int block = rng(empty);
        for(int a = 0; a < Dimensions; a++) {
            for(int b = 0; b < Dimensions; b++) {
                if(Grid[a][b] == null) empty2++;

                if(empty2 == block && isFree(a, b)) setGridSquare(a, b, ANSI_RED + "!" + ANSI_RESET);
            }
        }
    }

    public void removeBlock() {
        for(int a = 0; a < Dimensions; a++) {
            for(int b = 0; b < Dimensions; b++) {
                if(Grid[a][b] == "!") {
                    setGridSquare(a, b, null);
                }
            }
        }
    }
}
