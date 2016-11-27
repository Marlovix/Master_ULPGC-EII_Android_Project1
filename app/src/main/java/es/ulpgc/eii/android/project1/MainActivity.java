package es.ulpgc.eii.android.project1;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import es.ulpgc.eii.android.project1.listener.CollectListener;
import es.ulpgc.eii.android.project1.listener.StartTurnListener;
import es.ulpgc.eii.android.project1.listener.ThrowListener;
import es.ulpgc.eii.android.project1.modal.Game;
import es.ulpgc.eii.android.project1.modal.Player;
import es.ulpgc.eii.android.project1.ui.BarScore;
import es.ulpgc.eii.android.project1.ui.ButtonsToPlay;
import es.ulpgc.eii.android.project1.ui.DieView;
import es.ulpgc.eii.android.project1.ui.FinalAlertDialog;
import es.ulpgc.eii.android.project1.ui.GameInfo;
import es.ulpgc.eii.android.project1.ui.GameObject;
import es.ulpgc.eii.android.project1.ui.ScoreBoard;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class MainActivity extends FragmentActivity {

    static final String GAME_TAG = Game.class.getSimpleName();
    private FinalAlertDialog finalAlertDialog;
    private Game game;
    private GameObject[] gameObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            String namePlayer1 = String.format(getResources().getString(R.string.player), 1);
            String namePlayer2 = String.format(getResources().getString(R.string.player), 2);

            int colorPlayer1 = ContextCompat.getColor(this, R.color.player1);
            int colorPlayer2 = ContextCompat.getColor(this, R.color.player2);

            Player player1 = new Player(namePlayer1, colorPlayer1);
            Player player2 = new Player(namePlayer2, colorPlayer2);

            // The game is created with two players when the application is launched //
            game = new Game(player1, player2);
            game.start(player1);

            initViews();
            updateState();
        }

    }

    private void initViews() {
        /* Views initialization */
        TextView textViewPlayer1 = (TextView) findViewById(R.id.textView_player1);
        TextView textViewScorePlayer1 = (TextView) findViewById(R.id.textView_player1_score);
        ProgressBar progressBarPlayer1 = (ProgressBar) findViewById(R.id.progressBar_player1);

        TextView textViewPlayer2 = (TextView) findViewById(R.id.textView_player2);
        TextView textViewScorePlayer2 = (TextView) findViewById(R.id.textView_player2_score);
        ProgressBar progressBarPlayer2 = (ProgressBar) findViewById(R.id.progressBar_player2);

        ImageView imageViewDie = (ImageView) findViewById(R.id.imageView_die);

        TextView textViewAccumulated = (TextView) findViewById(R.id.textView_accumulated);
        TextView textViewPlayerTurn = (TextView) findViewById(R.id.textView_player_turn);
        TextView textViewStartTurn = (TextView) findViewById(R.id.textView_start_turn);

        Button buttonCollect = (Button) findViewById(R.id.button_collect);
        Button buttonThrow = (Button) findViewById(R.id.button_throw);

        BarScore barScorePlayer1 =
                new BarScore(textViewPlayer1, textViewScorePlayer1, progressBarPlayer1);
        BarScore barScorePlayer2 =
                new BarScore(textViewPlayer2, textViewScorePlayer2, progressBarPlayer2);

        ScoreBoard scoreBoard = new ScoreBoard(barScorePlayer1, barScorePlayer2);
        DieView dieView = new DieView(imageViewDie);
        GameInfo gameInfo = new GameInfo(textViewAccumulated, textViewPlayerTurn, textViewStartTurn);
        ButtonsToPlay buttons = new ButtonsToPlay(buttonThrow, buttonCollect);

        gameObjects = new GameObject[]{scoreBoard, dieView, gameInfo, buttons};

        finalAlertDialog = new FinalAlertDialog(this, game, gameObjects);

        /* Listeners */
        buttonThrow.setOnClickListener(new ThrowListener(game, finalAlertDialog, gameObjects));
        buttonCollect.setOnClickListener(new CollectListener(game, gameObjects));
        textViewStartTurn.setOnClickListener(new StartTurnListener(game, gameObjects));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finalAlertDialog.dismiss();
    }

    // The system call this before Activity is destroyed //
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(GAME_TAG, game);
        super.onSaveInstanceState(outState);
    }

    private void updateState() {
        switch (game.getGameState()) {
            case GAME:
                gamePlay();
                break;
            case ONE:
                lostTurnByOne();
                break;
            case READY:
                readyToPlay();
                break;
            case START:
                startGame();
                break;
            case TURN:
                startTurn();
                break;
            case WINNER:
                finishGame();
                break;
        }
    }

    private void finishGame() {
        for (GameObject gameObject : gameObjects) gameObject.finishGame(game);
        finalAlertDialog.show();
    }

    private void gamePlay() {
        for (GameObject gameObject : gameObjects) gameObject.gamePlay(game);
    }

    private void lostTurnByOne() {
        for (GameObject gameObject : gameObjects) gameObject.lostTurnByOne(game);
    }

    private void readyToPlay() {
        for (GameObject gameObject : gameObjects) gameObject.readyToPlay(game);
    }

    private void startGame() {
        for (GameObject gameObject : gameObjects) gameObject.startGame(game);
    }

    private void startTurn() {
        for (GameObject gameObject : gameObjects) gameObject.startTurn(game);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        game = savedInstanceState.getParcelable(GAME_TAG);
        initViews();
        updateState();
    }

}