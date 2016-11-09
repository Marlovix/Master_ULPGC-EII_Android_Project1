package es.ulpgc.eii.android.project1.listener;

import android.view.View;

import es.ulpgc.eii.android.project1.modal.Game;
import es.ulpgc.eii.android.project1.ui.FinalAlertDialog;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class ThrowListener implements View.OnClickListener {

    private Game game;
    private FinalAlertDialog alert;

    public ThrowListener(Game game) {
        this.game = game;
        alert = new FinalAlertDialog();
    }

    @Override
    public void onClick(View v) {

        // Condition for turn change (Value of die throwing = 1) //
        int throwingValue = game.getDie().getValue();
        if (throwingValue == 1) {
            game.startTurn();
            return;
        }

        // The player can keep playing, so the collect button is shown //
        game.getButtons().getButtonCollect().setVisibility(View.VISIBLE);

        // The player who is playing wins the game //
        if (game.getPlayers().getPlayerToPlay().getBarScore().getScore() + throwingValue +
                game.getPlayers().getPlayerToPlay().getAccumulatedScore() >= game.getMaxScore()) {

            game.getPlayers().getPlayerToPlay().getBarScore().setScore(game.getMaxScore());
            alert.show(game);
        } else { // The score of the die throwing is accumulated //
            game.getPlayers().getPlayerToPlay().addAccumulatedScore(throwingValue);
            game.update();
        }
    }

}