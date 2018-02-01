package com.example.sws.gongmoex1.Mulga;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.sws.gongmoex1.CustomActivity;
import com.example.sws.gongmoex1.R;

/**
 * Created by sws on 2017-09-28.
 */

public class MulgaListModel extends CustomActivity {

    //배경 이미지를 뿌려주기 위한 배열 선언
    String [] icons = {"apple", "peer"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mulga_listmodel);

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout1);

        //icon 갯수만큼 이미지 보여주기
        for (int i = 0; i < icons.length; i++) {
            int resId = getResources().getIdentifier(icons[i], "drawable",
                    "drawable");

            ImageView image = new ImageView(this);
            image.setImageResource(resId);
            layout.addView(image);
        }
    }
}
