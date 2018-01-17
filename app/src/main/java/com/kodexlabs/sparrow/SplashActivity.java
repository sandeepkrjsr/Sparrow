package com.kodexlabs.sparrow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        preferences = getSharedPreferences("firstLaunch", MODE_PRIVATE);
        if (!preferences.getBoolean("isFirstTime", false)) {
            startMain();
            final SharedPreferences pref = getSharedPreferences("firstLaunch", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("isFirstTime", true);
            editor.commit();
        }

        else {

            if (getIntent().getExtras() != null) {
                if (getIntent().getExtras().getString("id") != null) {
                //    passExtra();;
                } else {
                    startMain();
                }
            } else {

                startMain();

            }
        }
    }

    public void startMain() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getBaseContext(), SignInActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
