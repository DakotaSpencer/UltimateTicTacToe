package main;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static int dims;
    private static final String ANSI_RESET = "\u001B[0m";
    public static String playerSym = null;
    public static Boolean blockingActive = false;

    public static void main(String[] args) {
        Board board = new Board(3);
        Player[] players = new Player[2];

        players[0] = new Player("X"); // change later
        players[1] = new Player("O"); // change later

        int foundWinner = 0;

        mainMenu(players, board);

        int i=0;
        while(i<board.getDims()*board.getDims())
        {
            if(blockingActive) {
                board.blockRandomSpace();
            }

            if(i%2==0) //Player 1
            {
                board.printBoard();
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
                System.out.println();
            }
            else //Player 2
            {
                board.printBoard();
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
                System.out.println();
            }

            if(blockingActive) {
                board.removeBlock();
            }

            i++;
        }

        if(foundWinner == 0)
            System.out.println("It's a draw!");
            System.exit(1);

    }

    public static void mainMenu(Player [] players, Board board) {
        System.out.println("Welcome to Tic Tac Toe.");
        //boardMenu(board);

        //Random rng = new Random();
        //int num1 = rng.nextInt(10) + 1;
        //System.out.println(num1);

        System.out.println("Choose a game mode.");
        System.out.println("1 - Player VS Player");
        System.out.println("2 - Player VS Computer");
        System.out.println("3 - Computer VS Computer");
        System.out.println("4 - Progressively Larger Game Board");
        System.out.println("5 - Marathon Mode");
        System.out.println("6 - Blocking Mode");
        Scanner scannyboi = new Scanner(System.in);
        System.out.println("Mode:");
        int modeinput = scannyboi.nextInt();
        if(modeinput > 0 && modeinput < 7) {
            switch (modeinput) {
                case 1:
                    dims = getValidInt("Please choose how many rows and columns your board has, between 3 and 10.\n", 10, 3);
                    board.setDimensions(dims);
                    chooseColour(players, 2);
                    break;
                case 2:
                    dims = getValidInt("Please choose how many rows and columns your board has, between 3 and 10.\n", 10, 3);
                    board.setDimensions(dims);
                    chooseColour(players, 1);

                    PlayerVComp runner = new PlayerVComp();
                    runner.main();
                    System.out.println("Game is over! Exiting app...");
                    System.exit(1);
                    break;
                case 3:
                    dims = getValidInt("Please choose how many rows and columns your board has, between 3 and 10.\n", 10, 3);
                    board.setDimensions(dims);
                    CompVComp compRunner = new CompVComp();
                    compRunner.main();
                    System.out.println("Game is over! Exiting app...");
                    System.exit(1);
                    break;
                case 4:
                    chooseColour(players, 2);
                    ProgGame.main(board, players);
                    break;
                case 5:
                    dims = 3;
                    board.setDimensions(dims);
                    Marathon marathonRunner = new Marathon();
                    Marathon.main();
                    System.out.println("Game is over! Exiting app...");
                    System.exit(1);
                    break;
                case 6:
                    dims = getValidInt("Please choose how many rows and columns your board has, between 3 and 10. \n", 10, 3);
                    board.setDimensions(dims);
                    chooseColour(players, 2);
                    blockingActive = true;
                    break;    
            }
        }
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

    public static void boardMenu(Board board) {
        dims = getValidInt("Please choose how many rows and columns your board has, between 3 and 10.", 10, 3);
        board.setDimensions(dims);
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


//    public static boolean getWinner(String turnPrompt, Board board, Player player) {
//        System.out.println(turnPrompt);
//        int row=0, col=0;
//        while(true)
//        {
//            row = getValidInt("Enter row: ", board.getDims(), 0);
//            col = getValidInt("Enter col: ", board.getDims(), 0);
//            if(board.isFree(row,col))
//            {
//                break;
//            }
//            System.out.printf("[%d,%d] is already filled!\n",row,col);
//        }
//        board.setGridSquare(row,col,player.getColour() + player.getMarker() + ANSI_RESET);
//        //return board.checkHit(player.getMarker());
//        return board.checkHit(row);
//    }
}


