package es.ulpgc.eii.android.project1.listener;

import android.content.DialogInterface;

import es.ulpgc.eii.android.project1.modal.Game;
import es.ulpgc.eii.android.project1.ui.FinalAlertDialog;
import es.ulpgc.eii.android.project1.ui.GameObject;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class PlayAgainListener implements DialogInterface.OnClickListener {

    private FinalAlertDialog finalDialog;
    private Game game;
    private GameObject[] gameObjects;

    public PlayAgainListener(Game game, FinalAlertDialog finalDialog, GameObject[] gameObjects) {
        this.finalDialog = finalDialog;
        this.game = game;
        this.gameObjects = gameObjects;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        game.setStateStart();
        game.restart();

        for (GameObject gameObject : gameObjects) gameObject.startGame(game);

        //  Necessary use finalDialog instead of dialog param to set the FinalAlertDialog.dialog
        // attribute to null and use it on FinalAlertDialog.show() method //
        finalDialog.dismiss();
    }
}