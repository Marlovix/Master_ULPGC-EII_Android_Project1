package es.ulpgc.eii.android.project1.ui;

import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by Marlovix
 * Class which updates the view of the widgets related with the player score
 */

public class BarScore {

    private ProgressBar progressBar;
    private TextView textViewNamePlayer;
    private TextView textViewScore;

    public BarScore(TextView textViewNamePlayer, TextView textViewScore, ProgressBar progressBar) {
        this.textViewNamePlayer = textViewNamePlayer;
        this.textViewScore = textViewScore;
        this.progressBar = progressBar;
    }

    void setMax(int max) {
        progressBar.setMax(max);
    }

    void setNameBarScore(String name) {
        textViewNamePlayer.setText(name);
    }

    void setScore(int score) {
        progressBar.setProgress(score);
        textViewScore.setText(String.format(Locale.getDefault(), "%d", score));
    }
}