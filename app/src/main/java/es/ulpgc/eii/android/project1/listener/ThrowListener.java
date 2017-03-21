package es.ulpgc.eii.android.project1.listener;

import android.view.View;

import es.ulpgc.eii.android.project1.modal.Game;
import es.ulpgc.eii.android.project1.modal.GameState;
import es.ulpgc.eii.android.project1.modal.Player;
import es.ulpgc.eii.android.project1.tool.ViewTimer;
import es.ulpgc.eii.android.project1.ui.GameAlertDialog;
import es.ulpgc.eii.android.project1.ui.GameObject;

public class ThrowListener implements View.OnClickListener {

    private GameAlertDialog gameAlertDialog;
    private Game game;
    private GameObject[] gameObjects;

    public ThrowListener(Game game, GameAlertDialog gameAlertDialogs, GameObject[] gameObjects) {
        this.game = game;
        this.gameObjects = gameObjects;
        this.gameAlertDialog = gameAlertDialogs;
    }

    @Override
    public void onClick(View v) {

        ViewTimer.normalizeClick(v);

        int throwingValue = game.throwDie(); // Random number between 1 and 6 //

        // Condition for turn change (Value of die throwing = 1) //
        if (throwingValue == 1) {
            game.setStateOne(); // Update game state //
            game.changeTurn();
        } else {
            Player playerPlaying = game.getTurnPlayer();
            int currentAccumulatedScore = game.getAccumulatedScore();
            int currentScore = playerPlaying.getScore();
            int newAccumulatedScore = currentAccumulatedScore + throwingValue;
            int newScore = currentAccumulatedScore + currentScore + throwingValue;

            if (newScore >= game.getScoreToWin()) { // Player wins //
                game.setStateWinner(); // Update game state //
                playerPlaying.setScore(newScore);
                gameAlertDialog.show("FINISH");
                game.changeTurn();
            } else { // The score of the die throwing is accumulated //
                game.setStateGame(); // Update game state //
            }

            game.setAccumulatedScore(newAccumulatedScore); // Accumulate score //
        }

        // Update views //
        updateViewByState(gameObjects, game.getGameState());
    }

    private void updateViewByState(GameObject[] gameObjects, GameState gameState) {
        switch (gameState) {
            case ONE:
                for (GameObject gameObject : gameObjects) gameObject.lostTurnByOne(game);
                break;
            case FINISH:
                for (GameObject gameObject : gameObjects) gameObject.finishGame(game);
                break;
            case GAME:
                for (GameObject gameObject : gameObjects) gameObject.gamePlay(game);
                break;
        }
    }

}