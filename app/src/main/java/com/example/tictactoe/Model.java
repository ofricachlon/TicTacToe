package com.example.tictactoe;

public class Model {

    private char[][] board;
    private int counter;
    private final int SIZE=3;
    private char winner;


    public Model() {
        board = new char[SIZE][SIZE];
    }

    public void startGame() {
        counter = 0;
        winner = ' ';
        for (int i=0;i<SIZE;i++)
        {
            for(int k=0;k<SIZE;k++)
            {
                board[i][k]=' ';
            }
        }
    }

    public void setPlace(int loc, char player)
    {
        int row = loc/SIZE;
        int col = loc%SIZE;
        board[row][col]= player;
        counter++;
    }

    public boolean gameOver()
    {
        if(counter<5)
            return false;
        for (int i=0;i<SIZE;i++)
        {
            if(board[i][0]!=' '&& board[i][0] ==board[i][1] && board[i][0]==board[i][2])
            {
                winner = board[i][0];
                return true;
            }
        }
        for (int i=0;i<SIZE && winner==' ';i++)
        {
            if(board[0][i]!=' '&& board[0][i] ==board[1][i] && board[0][i]==board[2][i])
            {
                winner = board[0][i];
                return true;
            }
        }
        if(board[0][0]!=' '&& board[0][0] ==board[1][1] && board[0][1]==board[2][2])
        {
            winner = board[0][0];
            return true;
        }
        if(board[0][2]!=' '&& board[0][2] ==board[1][1] && board[0][2]==board[2][0])
        {
            winner = board[2][0];
            return true;
        }
        if(counter==9)
            return true;
        return false;
    }

    public char getWinner()
    {
        return winner;
    }
}
