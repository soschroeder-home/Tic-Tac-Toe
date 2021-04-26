package tictactoe;

import java.util.Scanner;

public class Main {
    final static char nought = 'O';
    final static char cross = 'X';
    final static char blank = '_';
    static Scanner input;
    static char[][] board;

    public static void main(String[] args) {

        System.out.println("Enter cells: ");

        input = new Scanner(System.in);
//        String inData = input.nextLine();

        board = new char[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = blank;
            }
        }

        printBoard();
        getMove();

    }

    static void getMove() {
        boolean done = false;
        String xCoordinate, yCoordinate, status;
        int xIndex, yIndex;
        char nextMark = cross;

        do {
            System.out.println("Enter the coordinates: ");

            String inX = input.next();
            String inY = input.next();

            if (inX.matches("[1-3]") && inY.matches("[1-3]")) {
                xIndex = Integer.parseInt(inX) - 1;
                yIndex = Integer.parseInt(inY) - 1;

                if (xIndex < 0 || xIndex > 2 || yIndex < 0 || yIndex > 2) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (board[xIndex][yIndex] == blank) {
                    board[xIndex][yIndex] = nextMark;
                    printBoard();
                    status = checkBoard();
                    if (status.equals("Game not finished")) {
                        nextMark = nextMark == nought ? cross : nought;
                    } else {
                        done = true;
                        System.out.println(status);
                    }
                } else {
                    System.out.println("This cell is occupied!  Choose another one!");
                }
            }  else {
                System.out.println("You should enter numbers!");
            }
        } while (!done);

    }

    static String checkBoard() {
        int xWins = 0;
        int oWins = 0;
        int xCount = 0;
        int oCount = 0;
        int blankCount = 0;

        //count number of each character
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 'X') {
                    xCount++;
                } else if (board[i][j] == 'O') {
                    oCount++;
                } else {
                    blankCount++;
                    board[i][j] = '_';
                }
            }
        }

        //check rows for wins
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == cross && board[i][1] == cross && board[i][2] == cross) {
                xWins++;
            } else if (board[i][0] == nought && board[i][1] == nought && board[i][2] == nought) {
                oWins++;
            }
        }

        //check columns for wins
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == cross && board[1][i] == cross && board[2][i] == cross) {
                xWins++;
            } else if (board[0][i] == nought && board[1][i] == nought && board[2][i] == nought) {
                oWins++;
            }
        }

        //check top left to bottom right diagonal
        if (board[0][0] == cross && board[1][1] == cross && board[2][2] == cross) {
            xWins++;
        } else if (board[0][0] == nought && board[1][1] == nought && board[2][2] == nought) {
            oWins++;
        }

        //check top right to bottom left diagonal
        if (board[0][2] == cross && board[1][1] == cross && board[2][0] == cross) {
            xWins++;
        } else if (board[0][2] == nought && board[1][1] == nought && board[2][0] == nought) {
            oWins++;
        }
/*
        //Print criteria for debugging purposes.
        System.out.println("X count: " + xCount);
        System.out.println("O count: " + oCount);
        System.out.println("blank count: " + blankCount);
        System.out.println("X wins: " + xWins);
        System.out.println("O wins: " + oWins);
*/
        String status;

        if (xWins > 0 && oWins > 0) { status = "Impossible";}
        else if (Math.abs(xCount - oCount) > 1) {status = "Impossible";}
        else if (xWins > 0) {status = "X wins";}
        else if (oWins > 0) {status = "O wins";}
        else if (blankCount > 0) {status = "Game not finished";}
        else {status = "Draw";}

        return status;

    }

    static void printBoard() {
        System.out.println("---------");
        System.out.println("| " + board[0][0] + " " + board[0][1] + " " + board[0][2] + " |");
        System.out.println("| " + board[1][0] + " " + board[1][1] + " " + board[1][2] + " |");
        System.out.println("| " + board[2][0] + " " + board[2][1] + " " + board[2][2] + " |");
        System.out.println("---------");
    }

}
