package es.ulpgc.eii.android.project1.listener;

import android.view.View;

import es.ulpgc.eii.android.project1.modal.Game;
import es.ulpgc.eii.android.project1.modal.Player;
import es.ulpgc.eii.android.project1.ui.ButtonsToPlay;
import es.ulpgc.eii.android.project1.ui.DieView;
import es.ulpgc.eii.android.project1.ui.FinalAlertDialog;
import es.ulpgc.eii.android.project1.ui.GameState;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class ThrowListener implements View.OnClickListener {

    private Game game;
    private DieView dieView;
    private GameState gameState;
    private ButtonsToPlay buttons;
    private FinalAlertDialog alert;

    public ThrowListener(Game game, DieView dieView, GameState gameState, ButtonsToPlay buttons) {
        this.game = game;
        this.dieView = dieView;
        this.gameState = gameState;
        this.buttons = buttons;
        alert = new FinalAlertDialog();
    }

    @Override
    public void onClick(View v) {

        // Condition for turn change (Value of die throwing = 1) //
        int throwingValue = game.throwDie();
        dieView.setImage(throwingValue);

        if (throwingValue == 1) {
            game.startTurn();
            return;
        }

        // The player can keep playing, so the collect button is shown //
        buttons.showCollectButton();

        // The player who is playing wins the game //
        Player playerPlaying = game.getPlayers().getPlayer();
        int accumulatedScore = playerPlaying.getAccumulatedScore();
        int currentScore = playerPlaying.getScore();
        int newScore = accumulatedScore + currentScore + throwingValue;
        if (newScore >= game.getMaxScore()) { // Player wins //
            // Update score view //
            alert.show(v.getContext(), game);
        } else { // The score of the die throwing is accumulated //
            playerPlaying.addAccumulatedScore(throwingValue);
            gameState.updateAccumulatedView(playerPlaying.getAccumulatedScore());
        }
    }

}