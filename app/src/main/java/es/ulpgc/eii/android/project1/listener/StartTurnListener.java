package es.ulpgc.eii.android.project1.listener;

import android.view.View;
import android.widget.Button;

import es.ulpgc.eii.android.project1.ui.ButtonsToPlay;
import es.ulpgc.eii.android.project1.ui.GameState;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class StartTurnListener implements View.OnClickListener {

    private ButtonsToPlay buttons;

    public StartTurnListener(ButtonsToPlay buttons) {
        this.buttons = buttons;
    }

    // The text to start the turn disappears and enables the Throw Button //
    @Override
    public void onClick(View v) {
        v.setVisibility(View.INVISIBLE);
        buttons.showThrowButton();
    }
}