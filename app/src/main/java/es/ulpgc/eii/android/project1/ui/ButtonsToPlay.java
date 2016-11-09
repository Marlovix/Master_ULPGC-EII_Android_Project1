package es.ulpgc.eii.android.project1.ui;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import es.ulpgc.eii.android.project1.R;
import es.ulpgc.eii.android.project1.listener.CollectListener;
import es.ulpgc.eii.android.project1.listener.ThrowListener;
import es.ulpgc.eii.android.project1.modal.Game;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

// Class which contains the buttons that are necessaries to play //
public class ButtonsToPlay {

    private Button buttonThrow;
    private Button buttonCollect;

    public ButtonsToPlay(Game game) {

        // UI widgets initialization //
        buttonCollect = (Button) ((Activity) game.getContext()).findViewById(R.id.button_collect);
        buttonThrow = (Button) ((Activity) game.getContext()).findViewById(R.id.button_throw);

        // Visibility of the buttons //
        hideButtons();

        // Listeners //
        buttonThrow.setOnClickListener(new ThrowListener(game));
        buttonCollect.setOnClickListener(new CollectListener(game));
    }

    public void hideButtons() {
        buttonCollect.setVisibility(View.GONE);
        buttonThrow.setVisibility(View.GONE);
    }

    Button getButtonThrow() {
        return buttonThrow;
    }

    public Button getButtonCollect() {
        return buttonCollect;
    }
}
