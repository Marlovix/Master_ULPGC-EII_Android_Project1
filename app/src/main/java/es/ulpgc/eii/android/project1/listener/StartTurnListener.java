package es.ulpgc.eii.android.project1.listener;

import android.view.View;

import es.ulpgc.eii.android.project1.modal.Game;
import es.ulpgc.eii.android.project1.tool.ViewTimer;
import es.ulpgc.eii.android.project1.ui.GameObject;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class StartTurnListener implements View.OnClickListener {

    private Game game;
    private GameObject[] gameObjects;

    public StartTurnListener(Game game, GameObject... gameObjects) {
        this.game = game;
        this.gameObjects = gameObjects;
    }

    @Override
    public void onClick(View v) {
        ViewTimer.normalizeClick(v);

        game.setStateReady();
        game.setAccumulatedScore(0);
        for (GameObject gameObject : gameObjects) gameObject.readyToPlay(game);
    }
}