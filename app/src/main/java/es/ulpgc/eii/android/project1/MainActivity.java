package es.ulpgc.eii.android.project1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import es.ulpgc.eii.android.project1.listener.CollectListener;
import es.ulpgc.eii.android.project1.listener.StartTurnListener;
import es.ulpgc.eii.android.project1.listener.ThrowListener;
import es.ulpgc.eii.android.project1.modal.Die;
import es.ulpgc.eii.android.project1.modal.Game;
import es.ulpgc.eii.android.project1.modal.Player;
import es.ulpgc.eii.android.project1.ui.BarScore;
import es.ulpgc.eii.android.project1.ui.ButtonsToPlay;
import es.ulpgc.eii.android.project1.ui.DieView;
import es.ulpgc.eii.android.project1.ui.GameState;
import es.ulpgc.eii.android.project1.ui.ScoreBoard;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class MainActivity extends FragmentActivity {

    private RetainedFragment dataFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Data objects initialization //
        String namePlayer1 = String.format(getResources().getString(R.string.player), 1);
        String namePlayer2 = String.format(getResources().getString(R.string.player), 2);

        int colorPlayer1 = Color.parseColor("#0000FF"); // Color Blue
        int colorPlayer2 = Color.parseColor("#FF0000"); // Color Red

        Player player1 = new Player(namePlayer1, colorPlayer1);
        Player player2 = new Player(namePlayer2, colorPlayer2);

        // The game is created with two players when the application is launched //
        Game game = new Game(player1, player2);

        TextView textViewPlayer1 = (TextView) findViewById(R.id.textView_player1);
        TextView textViewScorePlayer1 = (TextView) findViewById(R.id.textView_player1_score);
        ProgressBar progressBarPlayer1 = (ProgressBar) findViewById(R.id.progressBar_score_player1);

        TextView textViewPlayer2 = (TextView) findViewById(R.id.textView_player2);
        TextView textViewScorePlayer2 = (TextView) findViewById(R.id.textView_player2_score);
        ProgressBar progressBarPlayer2 = (ProgressBar) findViewById(R.id.progressBar_score_player2);

        ImageView imageViewDie = (ImageView) findViewById(R.id.imageView_die);

        TextView textViewAccumulated = (TextView) findViewById(R.id.textView_accumulated_score);
        TextView textViewPlayerToPlay = (TextView) findViewById(R.id.textView_player_turn);
        TextView textViewStartTurn = (TextView) findViewById(R.id.textView_start_turn);

        Button buttonCollect = (Button)findViewById(R.id.button_collect);
        Button buttonThrow = (Button) findViewById(R.id.button_throw);

        BarScore barScorePlayer1 =
                new BarScore(textViewPlayer1, textViewScorePlayer1, progressBarPlayer1);
        BarScore barScorePlayer2 =
                new BarScore(textViewPlayer2, textViewScorePlayer2, progressBarPlayer2);

        ScoreBoard scoreBoard = new ScoreBoard(barScorePlayer1, barScorePlayer2);

        DieView dieView = new DieView(imageViewDie);

        GameState gameState =
                new GameState(textViewAccumulated, textViewPlayerToPlay, textViewStartTurn);

        ButtonsToPlay buttons = new ButtonsToPlay(buttonThrow, buttonCollect);

        scoreBoard.setPlayer1(player1.getName());
        scoreBoard.setPlayer2(player2.getName());
        scoreBoard.setMax(game.getMaxScore());

        gameState.newTurn(player1);
        gameState.updateAccumulatedView(0);

        game.start(player1);

        // Listeners //
        buttonThrow.setOnClickListener(new ThrowListener(game, dieView, gameState, buttons));
        buttonCollect.setOnClickListener(
                new CollectListener(game, scoreBoard, dieView, gameState, buttons));

        // Listener //
        textViewStartTurn.setOnClickListener(
                new StartTurnListener(buttons));

        // Find the retained fragment on activity restart //
        FragmentManager manager = getSupportFragmentManager();
        dataFragment = (RetainedFragment) manager.findFragmentByTag("data");

        // Create the fragment and data the first time //
        if(dataFragment == null){
            // Add the fragment //
            //dataFragment = new RetainedFragment();
            //manager.beginTransaction().add(dataFragment, "data").commit();
            //dataFragment.setGame(game);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Store the data in the fragment //
        //dataFragment.setGame(game);
    }
}