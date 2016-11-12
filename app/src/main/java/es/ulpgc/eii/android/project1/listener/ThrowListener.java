package es.ulpgc.eii.android.project1.listener;

import android.view.View;

import es.ulpgc.eii.android.project1.modal.Game;
import es.ulpgc.eii.android.project1.modal.Player;
import es.ulpgc.eii.android.project1.ui.BarScore;
import es.ulpgc.eii.android.project1.ui.ButtonsToPlay;
import es.ulpgc.eii.android.project1.ui.DieView;
import es.ulpgc.eii.android.project1.ui.FinalAlertDialog;
import es.ulpgc.eii.android.project1.ui.GameState;
import es.ulpgc.eii.android.project1.ui.ScoreBoard;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class ThrowListener implements View.OnClickListener {

    private Game game;
    private ScoreBoard scoreBoard;
    private DieView dieView;
    private GameState gameState;
    private ButtonsToPlay buttons;
    private FinalAlertDialog alert;

    public ThrowListener(Game game, ScoreBoard scoreBoard, DieView dieView,
                         GameState gameState, ButtonsToPlay buttons) {
        this.game = game;
        this.scoreBoard = scoreBoard;
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
            Player newPlayer = game.getPlayers().getPlayer();
            gameState.newTurn(newPlayer);
            buttons.hideButtons();
            return;
        }

        // The player can keep playing, so the collect button is shown //
        buttons.showCollectButton();

        // The player who is playing wins the game //
        Player playerPlaying = game.getPlayers().getPlayer();
        String namePlayer = playerPlaying.getName();
        int accumulatedScore = playerPlaying.getAccumulatedScore();
        int currentScore = playerPlaying.getScore();
        int newScore = accumulatedScore + currentScore + throwingValue;
        if (newScore >= game.getMaxScore()) { // Player wins //
            // Update score view //
            BarScore barScorePlayer1 = scoreBoard.getBarScorePlayer1();
            BarScore barScorePlayer2 = scoreBoard.getBarScorePlayer2();

            String labelPlayer1 = barScorePlayer1.getNamePlayer();
            String labelPlayer2 = barScorePlayer2.getNamePlayer();

            if(labelPlayer1.equals(namePlayer)){
                scoreBoard.updatePlayer1(newScore);
            }else if(labelPlayer2.equals(namePlayer)){
                scoreBoard.updatePlayer2(newScore);
            }

            alert.show(v.getContext(), game, scoreBoard, dieView, gameState, buttons);
        } else { // The score of the die throwing is accumulated //
            playerPlaying.addAccumulatedScore(throwingValue);
            gameState.updateAccumulatedView(playerPlaying.getAccumulatedScore());
        }
    }

}