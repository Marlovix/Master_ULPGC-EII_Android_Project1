package es.ulpgc.eii.android.project1.ui;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import es.ulpgc.eii.android.project1.R;
import es.ulpgc.eii.android.project1.listener.StartTurnListener;
import es.ulpgc.eii.android.project1.modal.Game;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

// Class which contains the text reports about the state of the game, as the accumulated score,
// the name of the player who is playing and the clickable text to start the turn with a color //
public class GameState {

    private Game game;
    private TextView textViewPlayerToPlay;
    private TextView textViewStartTurn;
    private TextView textViewAccumulated;

    public GameState(Game game) {
        this.game = game;

        // UI widgets initialization //
        Activity auxActivity = (Activity) game.getContext();
        textViewAccumulated = (TextView) auxActivity.findViewById(R.id.textView_accumulated_score);
        textViewPlayerToPlay = (TextView) auxActivity.findViewById(R.id.textView_player_turn);
        textViewStartTurn = (TextView) auxActivity.findViewById(R.id.textView_start_turn);

        // Listener //
        textViewStartTurn.setOnClickListener(
                new StartTurnListener(game.getButtons().getButtonThrow()));
    }

    public void newTurn() {
        textViewPlayerToPlay.setText(game.getPlayers().getPlayerToPlay().getName());
        textViewStartTurn.setText(String.format(game.getContext().getString(R.string.label_start_turn),
                game.getPlayers().getPlayerToPlay().getName()));
        textViewStartTurn.setTextColor(game.getPlayers().getPlayerToPlay().getColor());
        textViewStartTurn.setVisibility(View.VISIBLE);
    }

    public TextView getTextViewAccumulated() {
        return textViewAccumulated;
    }


}