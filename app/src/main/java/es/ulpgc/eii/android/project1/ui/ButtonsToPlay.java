package es.ulpgc.eii.android.project1.ui;

import android.view.View;
import android.widget.Button;

import es.ulpgc.eii.android.project1.modal.Game;

public class ButtonsToPlay implements GameObject {

    private Button buttonCollect;
    private Button buttonThrow;

    public ButtonsToPlay(Button buttonThrow, Button buttonCollect) {
        this.buttonThrow = buttonThrow;
        this.buttonCollect = buttonCollect;
    }

    @Override
    public void finishGame(Game game) {
        buttonThrow.setVisibility(View.INVISIBLE);
        buttonCollect.setVisibility(View.INVISIBLE);
    }

    @Override
    public void gamePlay(Game game) {
        buttonThrow.setVisibility(View.VISIBLE);
        buttonCollect.setVisibility(View.VISIBLE);
    }

    @Override
    public void lostTurnByOne(Game game) {
        buttonCollect.setVisibility(View.INVISIBLE);
        buttonThrow.setVisibility(View.INVISIBLE);
    }

    @Override
    public void readyToPlay(Game game) {
        buttonCollect.setVisibility(View.INVISIBLE);
        buttonThrow.setVisibility(View.VISIBLE);
    }

    @Override
    public void startGame(Game game) {
        buttonCollect.setVisibility(View.INVISIBLE);
        buttonThrow.setVisibility(View.INVISIBLE);
    }

    @Override
    public void startTurn(Game game) {
        buttonCollect.setVisibility(View.INVISIBLE);
        buttonThrow.setVisibility(View.INVISIBLE);
    }
}
