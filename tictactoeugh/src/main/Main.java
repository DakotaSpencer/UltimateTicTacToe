package main;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {
        Board board = new Board(3);
        Player[] players = new Player[2];

        players[0] = new Player("X"); // change later
        players[1] = new Player("O"); // change later

        int foundWinner = 0;

        mainMenu(players);

        board.printBoard();

        int i=0;
        while(i<9)
        {
            if(i%2==0) //Player 1
            {
                if(getWinner("Player 1 turn", board, players[0]))
                {
                    foundWinner=1;
                    System.out.println("Player 1 WINS!");
                    break;
                }
                board.printBoard();
                System.out.println();
            }
            else //Player 2
            {
                if(getWinner("Player 2 turn", board, players[1]))
                {
                    foundWinner=1;
                    System.out.println("Player 2 WINS!");
                    break;
                }
                board.printBoard();
                System.out.println();
            }
            i++;
        }

        if(foundWinner == 0)
            System.out.println("It's a draw!");

    }

    public static void mainMenu(Player @NotNull [] players) {
        System.out.println("Welcome to Tic Tac Toe.");

        Random rng = new Random();
        int num1 = rng.nextInt(10) + 1;
        System.out.println(num1);

        final String ANSI_BLACK = "\u001B[30m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_WHITE = "\u001B[37m";

        System.out.println("Choose a game mode.");
        System.out.println("1 - Player VS Player");
        System.out.println("2 - Player VS Computer");
        System.out.println("3 - Computer VS Computer");
        Scanner scannyboi = new Scanner(System.in);
        System.out.println("Mode:");
        int modeinput = scannyboi.nextInt();
        if(modeinput > 0 && modeinput < 4) {
            switch (modeinput) {
                case 1:
                    for(int i = 0; i < players.length; i++) {
                        boolean finished = false;
                        while (!finished) {
                            System.out.println("Player " + (i+1) + ": Choose your colour.");
                            System.out.println("1 - " + ANSI_BLACK + "Black" + ANSI_RESET);
                            System.out.println("2 - " + ANSI_RED + "Red" + ANSI_RESET);
                            System.out.println("3 - " + ANSI_GREEN + "Green" + ANSI_RESET);
                            System.out.println("4 - " + ANSI_YELLOW + "Yellow" + ANSI_RESET);
                            System.out.println("5 - " + ANSI_BLUE + "Blue" + ANSI_RESET);
                            System.out.println("6 - " + ANSI_PURPLE + "Purple" + ANSI_RESET);
                            System.out.println("7 - " + ANSI_CYAN + "Cyan" + ANSI_RESET);
                            System.out.println("8 - " + ANSI_WHITE + "White" + ANSI_RESET);

                            Scanner scanner = new Scanner(System.in);
                            System.out.println("Color:");
                            int input = scanner.nextInt();
                            if(input > 0 && input < 9) {
                                switch(input) {
                                    case 1:
                                        players[i].setColour(ANSI_BLACK);
                                        break;
                                    case 2:
                                        players[i].setColour(ANSI_RED);
                                        break;
                                    case 3:
                                        players[i].setColour(ANSI_GREEN);
                                        break;
                                    case 4:
                                        players[i].setColour(ANSI_YELLOW);
                                        break;
                                    case 5:
                                        players[i].setColour(ANSI_BLUE);
                                        break;
                                    case 6:
                                        players[i].setColour(ANSI_PURPLE);
                                        break;
                                    case 7:
                                        players[i].setColour(ANSI_CYAN);
                                        break;
                                    case 8:
                                        players[i].setColour(ANSI_WHITE);
                                        break;
                                }
                                finished = true;
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println("Not Yet Implemented");
                    mainMenu(players);
                    break;
                case 3:
                    PlayerVComputer.main();
                    System.out.println("Game is over! Exiting app...");
                    System.exit(1);
                    break;
            }
        }
    }

    public static int getValidInt(String prompt) {

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
            if(num<0 || num>2)
            {
                System.out.println("Integer must be between 0 and 2");
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
            row = getValidInt("Enter row: ");
            col = getValidInt("Enter col: ");
            if(board.isFree(row,col))
            {
                break;
            }
            System.out.printf("[%d,%d] is already filled!\n",row,col);
        }
        board.setGridSquare(row,col,player.getColour() + player.getMarker() + ANSI_RESET);
        return board.checkHit();
    }

}


