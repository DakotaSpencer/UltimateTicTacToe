package main;

import java.util.Scanner;

public class ProgGame {
    private static final String ANSI_RESET = "\u001B[0m";
    public static String playerSym = null;
    public static void main(Board board, Player[] players) {
        board.setDimensions(3);
        int foundWinner = 0;

        board.printBoard();

        int i=0;
        while(i<board.getDims()*board.getDims() && foundWinner == 0)
        //while(i<9)
        {
            if(i%2==0) //Player 1
            {
                playerSym="X";
                if(runTurn("Player " +players[0].getColour() + "1" + ANSI_RESET + " turn", board, players[0]) == true) {
                    foundWinner = 1;
                    board.printBoard();
                    System.out.println("Player " +players[0].getColour() + "1" + ANSI_RESET + " WINS!");
                    System.exit(1);
                    break;
                }
//                if(getWinner("Player 1 turn", board, players[0]))
//                {
//                    foundWinner=1;
//                    System.out.println("Player 1 WINS!");
//                    break;
//                }
                board.printBoard();
                System.out.println();
            }
            else //Player 2
            {

                if (board.getDims()<5){
                    String[][] tempGrid = board.getGrid();
                    board.setDimensions(board.getDims()+1);

                    for(int k = 0; k < tempGrid.length; k++) {
                        for(int j = 0; j < tempGrid.length; j++) {
                            board.setGridSquare(k, j, tempGrid[k][j]);
                        }
                    }
                }

                playerSym="O";
                if(runTurn("Player " +players[1].getColour() + "2" + ANSI_RESET + " turn", board, players[1]) == true) {
                    foundWinner = 1;
                    board.printBoard();
                    System.out.println("Player " +players[1].getColour() + "2" + ANSI_RESET + " WINS!");
                    System.exit(1);
                    break;
                }
//                if(getWinner("Player 2 turn", board, players[1]))
//                {
//                    foundWinner=1;
//                    System.out.println("Player 2 WINS!");
//                    break;
//                }
                board.printBoard();
                System.out.println();
            }
            i++;
        }

        if(foundWinner == 0)
            System.out.println("It's a draw!");
            System.exit(1);

    }

    public static boolean runTurn(String prompt, Board board, Player player) {
        System.out.println(prompt);

        int row=0, col=0;
        while(true)
        {
            row = getValidInt("Enter row: ", board.getDims(), 0);
            col = getValidInt("Enter col: ", board.getDims(), 0);
            if(board.isFree(row,col))
            {
                break;
            }
            System.out.printf("[%d,%d] is already filled!\n",row,col);
        }

        board.setGridSquare(row,col,player.getColour() + player.getMarker() + ANSI_RESET);
        //return board.checkHit(player.getMarker());
        return board.checkHit(row, col);
    }

    public static void chooseColour(Player[] players, int times) {
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_WHITE = "\u001B[37m";

        int firstChoice = 0;

        for (int k = 0; k < times; k++) {
            boolean finished = false;
            while (!finished) {
                System.out.println("Player " + (k + 1) + ": Choose your colour.");
                System.out.println("1 - " + ANSI_RED + "Red" + ANSI_RESET);
                System.out.println("2 - " + ANSI_GREEN + "Green" + ANSI_RESET);
                System.out.println("3 - " + ANSI_YELLOW + "Yellow" + ANSI_RESET);
                System.out.println("4 - " + ANSI_BLUE + "Blue" + ANSI_RESET);
                System.out.println("5 - " + ANSI_PURPLE + "Purple" + ANSI_RESET);
                System.out.println("6 - " + ANSI_CYAN + "Cyan" + ANSI_RESET);
                System.out.println("7 - " + ANSI_WHITE + "White" + ANSI_RESET);

                Scanner scanner = new Scanner(System.in);
                System.out.println("Color:");
                firstChoice = scanner.nextInt();
                if (firstChoice > 0 && firstChoice < 9) {
                    switch (firstChoice) {
                        case 1:
                            players[k].setColour(ANSI_RED);
                            break;
                        case 2:
                            players[k].setColour(ANSI_GREEN);
                            break;
                        case 3:
                            players[k].setColour(ANSI_YELLOW);
                            break;
                        case 4:
                            players[k].setColour(ANSI_BLUE);
                            break;
                        case 5:
                            players[k].setColour(ANSI_PURPLE);
                            break;
                        case 6:
                            players[k].setColour(ANSI_CYAN);
                            break;
                        case 7:
                            players[k].setColour(ANSI_WHITE);
                            break;
                    }
                    finished = true;
                }
            }
        }

        if (times < 2) {
            boolean chosen = false;
            while (!chosen) {
                int rand = (int) Math.floor(Math.random() * (7) + 1);
                if (rand != firstChoice) {
                    chosen = true;
                    switch (rand) {
                        case 1:
                            players[1].setColour(ANSI_RED);
                            break;
                        case 2:
                            players[1].setColour(ANSI_GREEN);
                            break;
                        case 3:
                            players[1].setColour(ANSI_YELLOW);
                            break;
                        case 4:
                            players[1].setColour(ANSI_BLUE);
                            break;
                        case 5:
                            players[1].setColour(ANSI_PURPLE);
                            break;
                        case 6:
                            players[1].setColour(ANSI_CYAN);
                            break;
                        case 7:
                            players[1].setColour(ANSI_WHITE);
                            break;
                    }
                }
            }
        }
    }

    public static int getValidInt(String prompt, int max, int min) {

        Scanner in = new Scanner(System.in);
        while(true)
        {
            System.out.print(prompt);
            String input = in.nextLine();
            int num=0;
            try
            {
                num = Integer.parseInt(input);
            }
            catch(Exception e)
            {
                System.out.println("Invalid integer!");
                continue;
            }
            if(num<min || num>max)
            {
                System.out.println("Integer must be between " + min + " and " + max + ".");
                continue;
            }
            return num;
        }
    }
}
