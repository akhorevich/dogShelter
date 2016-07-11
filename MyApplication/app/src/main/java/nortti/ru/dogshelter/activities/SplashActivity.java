package nortti.ru.dogshelter.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import nortti.ru.dogshelter.R;

public class SplashActivity extends AppCompatActivity {
    private ProgressBar bar;
int progressStatus;
    Handler handler;
    ProgressBar progressBar;
            /** Called when the activity is first created. */

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        handler = new Handler();
        progressBar = (ProgressBar)findViewById(R.id.prog);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 5;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);

                        }
                    });

                    try {
                        // Sleep for 200 milliseconds.
                        //Just to display the progress slowly
                        Thread.sleep(200);
                        if (progressStatus == 100){
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(intent);
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } // while loop
            } // run()
        }).start();


    }




}
