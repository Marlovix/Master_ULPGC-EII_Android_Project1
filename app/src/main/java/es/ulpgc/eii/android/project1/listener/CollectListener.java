package es.ulpgc.eii.android.project1.listener;

import android.view.View;

import es.ulpgc.eii.android.project1.modal.Game;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class CollectListener implements View.OnClickListener {

    private Game game;

    public CollectListener(Game game) {
        this.game = game;
    }

    // When the player collects the accumulated score the game changes the player to play //
    @Override
    public void onClick(View v) {
        game.getPlayers().getPlayerToPlay().getBarScore().setScore(
                game.getPlayers().getPlayerToPlay().getBarScore().getScore() +  // current score
                        game.getPlayers().getPlayerToPlay().getAccumulatedScore());     // accumulated score
        game.startTurn();
    }
}