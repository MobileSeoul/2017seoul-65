package com.example.sws.gongmoex1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    // 스플래시 스크린을 3초 보여줌
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // 메인 액티비티로 들어가기전 스플래시 스크린 한번 실행
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);

                // 액티비티 닫기
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}