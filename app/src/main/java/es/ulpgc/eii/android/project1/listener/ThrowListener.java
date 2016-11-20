package es.ulpgc.eii.android.project1.listener;

import android.view.View;

import es.ulpgc.eii.android.project1.modal.Game;
import es.ulpgc.eii.android.project1.modal.GameState;
import es.ulpgc.eii.android.project1.modal.Player;
import es.ulpgc.eii.android.project1.tool.ViewTimer;
import es.ulpgc.eii.android.project1.ui.FinalAlertDialog;
import es.ulpgc.eii.android.project1.ui.GameObject;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class ThrowListener implements View.OnClickListener {

    private FinalAlertDialog finalAlertDialog;
    private Game game;
    private GameObject[] gameObjects;

    public ThrowListener(Game game, FinalAlertDialog finalAlertDialogs, GameObject[] gameObjects) {
        this.game = game;
        this.gameObjects = gameObjects;
        this.finalAlertDialog = finalAlertDialogs;
    }

    @Override
    public void onClick(View v) {

        ViewTimer.normalizeClick(v);

        int throwingValue = game.throwDie();

        // Condition for turn change (Value of die throwing = 1) //
        if (throwingValue == 1) {
            game.setStateOne();
            game.changeTurn();
        } else {
            Player playerPlaying = game.getTurnPlayer();
            int currentAccumulatedScore = game.getAccumulatedScore();
            int currentScore = playerPlaying.getScore();
            int newAccumulatedScore = currentAccumulatedScore + throwingValue;
            int newScore = currentAccumulatedScore + currentScore + throwingValue;

            if (newScore >= game.getMaxScore()) { // Player wins //
                game.setStateWinner();
                playerPlaying.setScore(newScore);
                finalAlertDialog.show();
            } else { // The score of the die throwing is accumulated //
                game.setStateGame();
            }

            game.setAccumulatedScore(newAccumulatedScore);
        }

        updateViewByState(gameObjects, game.getGameState());
    }

    private void updateViewByState(GameObject[] gameObjects, GameState gameState) {
        switch (gameState) {
            case ONE:
                for (GameObject gameObject : gameObjects) gameObject.lostTurnByOne(game);
                break;
            case WINNER:
                for (GameObject gameObject : gameObjects) gameObject.finishGame(game);
                break;
            case GAME:
                for (GameObject gameObject : gameObjects) gameObject.gamePlay(game);
                break;
        }
    }

}