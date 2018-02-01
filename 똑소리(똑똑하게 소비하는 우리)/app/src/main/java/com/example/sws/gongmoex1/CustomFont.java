package com.example.sws.gongmoex1;

import android.app.Application;

import com.tsengvn.typekit.Typekit;

/**
 * Created by sws on 2017-10-29.
 */

// 글꼴을 바꾸기 위한 typekit 라이브러리 사용
// http://www.apache.org/licenses/LICENSE-2.0

public class CustomFont extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "BMJUA_ttf.ttf"))
                .addBold(Typekit.createFromAsset(this, "BMJUA_ttf.ttf"));
    }
    }

