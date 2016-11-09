package es.ulpgc.eii.android.project1.listener;

import android.content.DialogInterface;

import es.ulpgc.eii.android.project1.modal.Game;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class PlayAgainListener implements DialogInterface.OnClickListener {

    private Game game;

    public PlayAgainListener(Game game) {
        this.game = game;
    }

    // The player closes the final dialog, then the game starts a new game with the loser player //
    @Override
    public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
        game.getPlayers().changePlayerToPlay();
        game.start(game.getPlayers().getPlayerToPlay());
    }
}