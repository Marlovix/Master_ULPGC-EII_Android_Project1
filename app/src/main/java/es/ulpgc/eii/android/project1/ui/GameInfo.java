package es.ulpgc.eii.android.project1.ui;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import es.ulpgc.eii.android.project1.R;
import es.ulpgc.eii.android.project1.modal.Game;
import es.ulpgc.eii.android.project1.modal.Player;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class GameInfo implements GameObject {

    private TextView textViewAccumulated;
    private TextView textViewPlayerTurn;
    private TextView textViewStartTurn;

    public GameInfo(TextView textViewAccumulated,
                    TextView textViewPlayerTurn, TextView textViewStartTurn) {
        this.textViewAccumulated = textViewAccumulated;
        this.textViewPlayerTurn = textViewPlayerTurn;
        this.textViewStartTurn = textViewStartTurn;
    }

    @Override
    public void finishGame(Game game) {
        setInfo(game);
        textViewPlayerTurn.setVisibility(View.VISIBLE);
        textViewStartTurn.setVisibility(View.INVISIBLE);
    }

    @Override
    public void gamePlay(Game game) {
        setInfo(game);
        textViewPlayerTurn.setVisibility(View.VISIBLE);
        textViewStartTurn.setVisibility(View.INVISIBLE);
    }

    @Override
    public void lostTurnByOne(Game game) {
        setInfo(game);
        textViewPlayerTurn.setVisibility(View.VISIBLE);
        textViewStartTurn.setVisibility(View.VISIBLE);
    }

    @Override
    public void readyToPlay(Game game) {
        setInfo(game);
        textViewPlayerTurn.setVisibility(View.VISIBLE);
        textViewStartTurn.setVisibility(View.INVISIBLE);
    }

    @Override
    public void startGame(Game game) {
        setInfo(game);
        textViewPlayerTurn.setVisibility(View.VISIBLE);
        textViewStartTurn.setVisibility(View.VISIBLE);
    }

    @Override
    public void startTurn(Game game) {
        setInfo(game);
        textViewPlayerTurn.setVisibility(View.INVISIBLE);
        textViewStartTurn.setVisibility(View.VISIBLE);
    }

    private void setInfo(Game game) {
        Context context = textViewPlayerTurn.getContext();
        Player playerToStart = game.getTurnPlayer();
        int accumulatedScore = game.getAccumulatedScore();
        int colorText = playerToStart.getColor();
        String newPlayer = playerToStart.getName();
        String textNewTurn = String.format(context.getString(R.string.label_start_turn), newPlayer);

        updateAccumulatedView(accumulatedScore);
        textViewPlayerTurn.setText(newPlayer);
        textViewStartTurn.setText(textNewTurn);
        textViewStartTurn.setTextColor(colorText);
    }

    private void updateAccumulatedView(int accumulatedScore) {
        Context context = textViewAccumulated.getContext();
        String textAccumulated =
                String.format(context.getString(R.string.label_accumulated), accumulatedScore);
        textViewAccumulated.setText(textAccumulated);
    }

}