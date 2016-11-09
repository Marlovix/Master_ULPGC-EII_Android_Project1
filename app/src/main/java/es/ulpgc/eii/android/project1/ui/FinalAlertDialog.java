package es.ulpgc.eii.android.project1.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.Gravity;
import android.widget.TextView;

import es.ulpgc.eii.android.project1.R;
import es.ulpgc.eii.android.project1.listener.ExitListener;
import es.ulpgc.eii.android.project1.listener.PlayAgainListener;
import es.ulpgc.eii.android.project1.modal.Game;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class FinalAlertDialog {

    public void show(Game game) {
        AlertDialog.Builder builder = new AlertDialog.Builder(game.getContext());
        builder.setTitle(game.getContext().getString(R.string.button_final));
        builder.setMessage(String.format(game.getContext().getString(R.string.label_won),
                game.getPlayers().getPlayerToPlay().getName()));
        builder.setPositiveButton(game.getContext().getResources().getString(R.string.button_play_again),
                new PlayAgainListener(game));
        builder.setNegativeButton(game.getContext().getResources().getString(R.string.button_exit),
                new ExitListener((Activity) game.getContext()));
        builder.setCancelable(false);

        // Center the message of the Alert Dialog //
        AlertDialog dialog = builder.show();
        TextView messageText = (TextView) dialog.findViewById(android.R.id.message);
        messageText.setGravity(Gravity.CENTER);
        dialog.show();
    }

}
