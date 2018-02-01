package com.example.sws.gongmoex1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.sws.gongmoex1.GoodStore.GoodPriceMain;
import com.example.sws.gongmoex1.Mulga.Mulga_Select;

public class MainActivity extends AppCompatActivity {

    //물가정보 카드뷰
    CardView first_cardView;
    //착한가게 카드뷰
    CardView second_cardView;

    LinearLayout linearLayout;
    LinearLayout linearLayout2;

    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        first_cardView = (CardView) findViewById(R.id.first_cardview);
        linearLayout = (LinearLayout) findViewById(R.id.layout1);

        second_cardView = (CardView) findViewById(R.id.second_cardview);
        linearLayout2 = (LinearLayout) findViewById(R.id.layout2);

        first_cardView.setOnClickListener(onClickListener);
        second_cardView.setOnClickListener(onClickListener);

        backPressCloseHandler = new BackPressCloseHandler(this);
    }

    //클릭하면 각각의 액티비티로 intent
    private final View.OnClickListener onClickListener =  new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.first_cardview){
                Intent intent = new Intent(MainActivity.this, Mulga_Select.class);
                startActivity(intent);
            }

            if(v.getId() == R.id.second_cardview){
                Intent intent = new Intent(MainActivity.this, GoodPriceMain.class);
                startActivity(intent);
            }
        }
    };

    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }
}
