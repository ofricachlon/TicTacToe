package com.example.tictactoe;

public class Controller {
    private Model model;
    private char player;

    public Controller() {
        model = new Model();
        startGame();
    }

    public void startGame() {
        model.startGame();
        player = 'x';
    }

    public char userSelect(int loc)
    {
        char prev = player;
        model.setPlace(loc,player);
        if ((player=='x'))
            player='o';
        else
            player='x';
        return prev;
    }

    public char getPlayer()
    {
        return player;
    }
}
