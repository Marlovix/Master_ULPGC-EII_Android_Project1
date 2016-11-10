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

    public ButtonsToPlay(Button buttonThrow, Button buttonCollect) {

        this.buttonThrow = buttonThrow;
        this.buttonCollect = buttonCollect;

        // Visibility of the buttons //
        hideButtons();
    }

    public void hideButtons() {
        buttonCollect.setVisibility(View.INVISIBLE);
        buttonThrow.setVisibility(View.INVISIBLE);
    }

    public void showThrowButton(){
        buttonThrow.setVisibility(View.VISIBLE);
    }

    public void showCollectButton(){
        buttonCollect.setVisibility(View.VISIBLE);
    }

    Button getButtonThrow() {
        return buttonThrow;
    }

    public Button getButtonCollect() {
        return buttonCollect;
    }
}
