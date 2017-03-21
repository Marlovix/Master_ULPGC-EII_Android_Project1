package es.ulpgc.eii.android.project1.listener;

import android.view.View;

import es.ulpgc.eii.android.project1.modal.Game;
import es.ulpgc.eii.android.project1.tool.ViewTimer;
import es.ulpgc.eii.android.project1.ui.GameObject;

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

        game.setStateReady(); // Update game state //
        game.setAccumulatedScore(0); // Restart the accumulated score //

        // Update views //
        for (GameObject gameObject : gameObjects) gameObject.readyToPlay(game);
    }
}