package main;

import java.util.Random;
import java.util.Scanner;

public class CompVComp {
    public CompVComp() {}
    private static final String ANSI_RESET = "\u001B[0m";

    public static int rng(int bound){
        Random rng = new Random();
        int num1 = rng.nextInt(bound);
        System.out.println(num1);
        return num1;
    }
    public static void main() {
        Board board = new Board(Main.dims);
        Player[] players = new Player[2];

        players[0] = new Player("X"); // change later
        players[1] = new Player("O"); // change later

        int foundWinner = 0;

        colorMenu(players);

        board.printBoard();

        int i=0;
        while(i<board.getDims()*board.getDims())
        //while(i<9)
        {
            if(i%2==0) //Player 1
            {
                if(getWinner("Player " +players[0].getColour() + "1" + ANSI_RESET + " turn", board, players[0]))
                {
                    foundWinner=1;
                    board.printBoard();
                    System.out.println("Player " +players[0].getColour() + "1" + ANSI_RESET + " WINS!");
                    System.exit(1);
                    break;
                }
                board.printBoard();
                System.out.println();
            }
            else //Player 2
            {
                if(getWinner("Player " +players[1].getColour() + "2" + ANSI_RESET + " turn", board, players[1]))
                {
                    foundWinner=1;
                    board.printBoard();
                    System.out.println("Player " +players[1].getColour() + "2" + ANSI_RESET + " WINS!");
                    System.exit(1);
                    break;
                }
                board.printBoard();
                System.out.println();
            }
            i++;
        }

        if(foundWinner == 0)
            System.out.println("It's a draw!");
            System.exit(1);
    }

    public static void colorMenu(Player [] players) {
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_BLUE = "\u001B[34m";
        System.out.println(ANSI_BLUE + "COMPUTER VS COMPUTER" + ANSI_RESET);


        players[0].setColour(ANSI_RED);
        players[1].setColour(ANSI_BLUE);
    }

    public static int getValidInt(String prompt, int max, int min) {
        while(true)
        {
            System.out.print(prompt);

            String input = String.valueOf(rng(max));

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


    public static boolean getWinner(String turnPrompt, Board board, Player player) {
        System.out.println(turnPrompt);
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
}
