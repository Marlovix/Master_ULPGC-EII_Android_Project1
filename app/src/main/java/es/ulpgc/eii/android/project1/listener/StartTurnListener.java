package es.ulpgc.eii.android.project1.listener;

import android.view.View;
import android.widget.Button;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class StartTurnListener implements View.OnClickListener {

    private Button buttonThrow;

    public StartTurnListener(Button buttonThrow) {
        this.buttonThrow = buttonThrow;
    }

    // The text to start the turn disappears and enables the Throw Button //
    @Override
    public void onClick(View v) {
        v.setVisibility(View.GONE);
        buttonThrow.setVisibility(View.VISIBLE);
    }
}