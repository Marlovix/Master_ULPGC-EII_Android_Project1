package es.ulpgc.eii.android.project1.listener;

import android.view.View;

import es.ulpgc.eii.android.project1.modal.Game;
import es.ulpgc.eii.android.project1.modal.Player;
import es.ulpgc.eii.android.project1.tool.ViewTimer;
import es.ulpgc.eii.android.project1.ui.GameObject;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class CollectListener implements View.OnClickListener {

    private Game game;
    private GameObject[] gameObjects;

    public CollectListener(Game game, GameObject[] gameObjects) {
        this.game = game;
        this.gameObjects = gameObjects;
    }

    @Override
    public void onClick(View v) {
        ViewTimer.normalizeClick(v);

        game.setStateTurn();

        Player playerPlaying = game.getTurnPlayer();
        int accumulatedScore = game.getAccumulatedScore();
        int currentScore = playerPlaying.getScore();
        int newScore = accumulatedScore + currentScore;

        playerPlaying.setScore(newScore);

        game.changeTurn();

        for (GameObject gameObject : gameObjects) gameObject.startTurn(game);
    }
}