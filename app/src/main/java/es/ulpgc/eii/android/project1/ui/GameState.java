package es.ulpgc.eii.android.project1.ui;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import es.ulpgc.eii.android.project1.R;
import es.ulpgc.eii.android.project1.modal.Player;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

// Class which contains the text reports about the state of the game, as the accumulated score,
// the name of the player who is playing and the clickable text to start the turn with a color //
public class GameState {

    private TextView textViewPlayerToPlay;
    private TextView textViewStartTurn;
    private TextView textViewAccumulated;

    public GameState(TextView textViewAccumulated,
                     TextView textViewPlayerToPlay, TextView textViewStartTurn) {
        // UI widgets initialization //
        this.textViewAccumulated = textViewAccumulated;
        this.textViewPlayerToPlay = textViewPlayerToPlay;
        this.textViewStartTurn = textViewStartTurn;
    }

    public void newTurn(Player player) {
        Context context = textViewPlayerToPlay.getContext();
        String newPlayer = player.getName();
        String textNewTurn = String.format(context.getString(R.string.label_start_turn), newPlayer);
        int colorText = player.getColor();
        updateAccumulatedView(0);
        textViewPlayerToPlay.setText(newPlayer);
        textViewStartTurn.setText(textNewTurn);
        textViewStartTurn.setTextColor(colorText);
        textViewStartTurn.setVisibility(View.VISIBLE);
    }

    // The only element which needs a update is the accumulated score //
    public void updateAccumulatedView(int accumulatedScore) {
        Context context = textViewAccumulated.getContext();
        String textAccumulated =
                String.format(context.getString(R.string.label_accumulated), accumulatedScore);
        textViewAccumulated.setText(textAccumulated);
    }
}