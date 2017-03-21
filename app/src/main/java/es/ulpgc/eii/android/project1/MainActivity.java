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
import es.ulpgc.eii.android.project1.ui.GameAlertDialog;
import es.ulpgc.eii.android.project1.ui.GameInfo;
import es.ulpgc.eii.android.project1.ui.GameObject;
import es.ulpgc.eii.android.project1.ui.ScoreBoard;

public class MainActivity extends FragmentActivity {

    // Constants //
    static final String GAME_TAG = Game.class.getSimpleName();
    static final String EXIT_ALERT_VISIBILITY = GameAlertDialog.class.getSimpleName();
    static final int SCORE_TO_WIN = 20;

    // Attributes //
    private GameAlertDialog gameAlertDialog;
    private Game game;
    private GameObject[] gameObjects;
    private boolean exitAlertVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // If the app is restored onRestoreInstanceState() will recover the state of game //
        if (savedInstanceState == null) {
            // Game variables //
            int colorPlayer1 = ContextCompat.getColor(this, R.color.player1);
            int colorPlayer2 = ContextCompat.getColor(this, R.color.player2);
            String namePlayer1 = String.format(getResources().getString(R.string.player), 1);
            String namePlayer2 = String.format(getResources().getString(R.string.player), 2);
            Player player1 = new Player(namePlayer1, colorPlayer1);
            Player player2 = new Player(namePlayer2, colorPlayer2);

            // The game is created with two players when the application is launched //
            game = new Game(player1, player2);

            // It is necessary set player who is going to start the game and the score to win //
            game.start(player1, SCORE_TO_WIN);

            initViews();
            updateState();
        }

    }

    // Activity views initialization //
    private void initViews() {
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

        // The views are stored in objects which handle its behaviour //
        BarScore barScorePlayer1 =
                new BarScore(textViewPlayer1, textViewScorePlayer1, progressBarPlayer1);
        BarScore barScorePlayer2 =
                new BarScore(textViewPlayer2, textViewScorePlayer2, progressBarPlayer2);
        ScoreBoard scoreBoard = new ScoreBoard(barScorePlayer1, barScorePlayer2);
        DieView dieView = new DieView(imageViewDie);
        GameInfo gameInfo =
                new GameInfo(textViewAccumulated, textViewPlayerTurn, textViewStartTurn);
        ButtonsToPlay buttons = new ButtonsToPlay(buttonThrow, buttonCollect);

        // These objects with views are GameObjects, so they are stored in a global array //
        gameObjects = new GameObject[]{scoreBoard, dieView, gameInfo, buttons};

        // The AlertDialog to show the winner message or exit message -> onBackPressed() //
        gameAlertDialog = new GameAlertDialog(this, game, gameObjects);

        // Listeners //
        buttonThrow.setOnClickListener(new ThrowListener(game, gameAlertDialog, gameObjects));
        buttonCollect.setOnClickListener(new CollectListener(game, gameObjects));
        textViewStartTurn.setOnClickListener(new StartTurnListener(game, gameObjects));
    }

    // The final call of system before the activity is destroyed, so the AlertDialog is closed //
    @Override
    protected void onDestroy() {
        super.onDestroy();
        gameAlertDialog.dismiss();
    }

    // The system calls this before Activity is destroyed and saves the game state //
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(GAME_TAG, game);
        outState.putBoolean(EXIT_ALERT_VISIBILITY, exitAlertVisible); // is EXIT AlertDialog shown?
        super.onSaveInstanceState(outState);
    }

    // Ask if exit or start a new game on AlertDialog //
    @Override
    public void onBackPressed() {
        gameAlertDialog.show("EXIT");
        exitAlertVisible = true;
    }

    // Depending on the game state is executed a method //
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
            case FINISH:
                finishGame();
                break;
        }
    }

    /* These methods only loop the gameObjects array and execute the methods of the interface */
    private void finishGame() {
        for (GameObject gameObject : gameObjects) gameObject.finishGame(game);
        gameAlertDialog.show("FINISH");
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


    // The game state is restored and set views according that state //
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        game = savedInstanceState.getParcelable(GAME_TAG);
        exitAlertVisible = savedInstanceState.getBoolean(EXIT_ALERT_VISIBILITY);
        initViews();
        updateState();
        if (exitAlertVisible) gameAlertDialog.show("EXIT");
    }

}