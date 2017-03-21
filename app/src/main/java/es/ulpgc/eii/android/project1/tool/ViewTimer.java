package es.ulpgc.eii.android.project1.tool;

import android.app.Activity;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

// This class blocks the view (Button, TextView) for a time to avoid a fast click //
public class ViewTimer {

    public static void normalizeClick(View view) {
        view.setClickable(false);
        final View v = view;
        Timer viewTimer = new Timer();
        viewTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                ((Activity) v.getContext()).runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        v.setClickable(true);
                    }
                });
            }
        }, 200); // Milliseconds are required //
    }

}