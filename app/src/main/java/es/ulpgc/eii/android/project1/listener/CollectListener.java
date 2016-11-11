package es.ulpgc.eii.android.project1.listener;

import android.view.View;

import es.ulpgc.eii.android.project1.modal.Game;
import es.ulpgc.eii.android.project1.modal.Player;
import es.ulpgc.eii.android.project1.ui.BarScore;
import es.ulpgc.eii.android.project1.ui.ButtonsToPlay;
import es.ulpgc.eii.android.project1.ui.DieView;
import es.ulpgc.eii.android.project1.ui.GameState;
import es.ulpgc.eii.android.project1.ui.ScoreBoard;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class CollectListener implements View.OnClickListener {

    private Game game;
    private ScoreBoard scoreBoard;
    private DieView dieView;
    private GameState gameState;
    private ButtonsToPlay buttons;

    public CollectListener(Game game, ScoreBoard scoreBoard, DieView dieView,
                           GameState gameState, ButtonsToPlay buttons) {
        this.game = game;
        this.scoreBoard = scoreBoard;
        this.dieView = dieView;
        this.gameState = gameState;
        this.buttons = buttons;
    }

    // When the player collects the accumulated score the game changes the player to play //
    @Override
    public void onClick(View v) {
        Player playerPlaying = game.getPlayers().getPlayer();
        String namePlayer = playerPlaying.getName();
        int accumulatedScore = playerPlaying.getAccumulatedScore();
        int currentScore = playerPlaying.getScore();
        int newScore = accumulatedScore + currentScore;

        playerPlaying.setScore(newScore);
        playerPlaying.resetAccumulatedScore();

        BarScore barScorePlayer1 = scoreBoard.getBarScorePlayer1();
        BarScore barScorePlayer2 = scoreBoard.getBarScorePlayer2();

        String labelPlayer1 = barScorePlayer1.getNamePlayer();
        String labelPlayer2 = barScorePlayer2.getNamePlayer();

        if(labelPlayer1.equals(namePlayer)){
            scoreBoard.updatePlayer1(newScore);
        }else if(labelPlayer2.equals(namePlayer)){
            scoreBoard.updatePlayer2(newScore);
        }

        game.startTurn();
        Player newPlayer = game.getPlayers().getPlayer();

        gameState.newTurn(newPlayer);
        dieView.hide();
        buttons.hideButtons();
    }
}