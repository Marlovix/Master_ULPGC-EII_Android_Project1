package es.ulpgc.eii.android.project1.listener;

import android.content.DialogInterface;

import es.ulpgc.eii.android.project1.modal.Game;
import es.ulpgc.eii.android.project1.modal.Player;
import es.ulpgc.eii.android.project1.ui.ButtonsToPlay;
import es.ulpgc.eii.android.project1.ui.DieView;
import es.ulpgc.eii.android.project1.ui.GameState;
import es.ulpgc.eii.android.project1.ui.ScoreBoard;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class PlayAgainListener implements DialogInterface.OnClickListener {

    private Game game;
    private ScoreBoard scoreBoard;
    private DieView dieView;
    private GameState gameState;
    private ButtonsToPlay buttons;

    public PlayAgainListener(Game game, ScoreBoard scoreBoard, DieView dieView,
                             GameState gameState, ButtonsToPlay buttons) {
        this.game = game;
        this.scoreBoard = scoreBoard;
        this.dieView = dieView;
        this.gameState = gameState;
        this.buttons = buttons;
    }

    // The player closes the final dialog, then the game starts a new game with the loser player //
    @Override
    public void onClick(DialogInterface dialog, int which) {
        // Set new player to start game //
        game.getPlayers().changePlayer();
        Player newPlayer = game.getPlayers().getPlayer();
        game.start(newPlayer);

        scoreBoard.resetBarScores();
        dieView.hide();
        gameState.newTurn(newPlayer);
        buttons.hideButtons();

        dialog.dismiss();
    }
}