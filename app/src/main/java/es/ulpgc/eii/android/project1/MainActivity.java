package es.ulpgc.eii.android.project1;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import es.ulpgc.eii.android.project1.modal.Game;
import es.ulpgc.eii.android.project1.modal.Player;
import es.ulpgc.eii.android.project1.ui.BarScore;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // The game is created with two players when the application is launched //
        Game game = new Game(this,
                new Player(String.format(getResources().getString(R.string.player), 1),
                        Color.parseColor("#0000FF"), // Color Blue
                        new BarScore((TextView) findViewById(R.id.textView_player1),
                                (TextView) findViewById(R.id.textView_player1_score),
                                (ProgressBar) findViewById(R.id.progressBar_score_player1))),
                new Player(String.format(getResources().getString(R.string.player), 2),
                        Color.parseColor("#FF0000"), // Color Red
                        new BarScore((TextView) findViewById(R.id.textView_player2),
                                (TextView) findViewById(R.id.textView_player2_score),
                                (ProgressBar) findViewById(R.id.progressBar_score_player2))));

        // The game starts with the Player 1 //
        game.start(game.getPlayers().get(0));

    }

}