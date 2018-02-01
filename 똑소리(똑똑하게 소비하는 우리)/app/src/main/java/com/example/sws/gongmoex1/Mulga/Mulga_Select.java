package com.example.sws.gongmoex1.Mulga;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.sws.gongmoex1.CustomActivity;
import com.example.sws.gongmoex1.R;

public class Mulga_Select extends CustomActivity {
    Spinner spinner;
    Spinner spinner2;
    String market_Name = "";

    //원하는 구 선택을 위한 Spinner 메소드
    private void popSpinner(){
        ArrayAdapter<CharSequence> fAdapter;

        fAdapter=ArrayAdapter.createFromResource(this,R.array.select_Gu,android.R.layout.simple_spinner_item);
        fAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(fAdapter);
    }

    //원하는 시장 선택을 위한 Spinner 메소드
    private void popSubSpinner(int itemNum){
        ArrayAdapter<CharSequence> fAdapter;
        fAdapter=ArrayAdapter.createFromResource(this,itemNum,android.R.layout.simple_spinner_item);
        fAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(fAdapter);
    }

    //해당 구에 따라 시장이 변하는 리스너 등록한다.
    private AdapterView.OnItemSelectedListener spinSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
            switch (position){
                case (0):
                    popSubSpinner(R.array.gangnam_market);
                    break;
                case (1):
                    popSubSpinner(R.array.gangdong_market);
                    break;
                case (2):
                    popSubSpinner(R.array.gangbuk_market);
                    break;
                case (3):
                    popSubSpinner(R.array.gangseo_market);
                    break;
                case (4):
                    popSubSpinner(R.array.gwanak_market);
                    break;
                case (5):
                    popSubSpinner(R.array.gwangjin_market);
                    break;
                case (6):
                    popSubSpinner(R.array.guro_market);
                    break;
                case (7):
                    popSubSpinner(R.array.geumcheon_market);
                    break;
                case (8):
                    popSubSpinner(R.array.nowon_market);
                    break;
                case (9):
                    popSubSpinner(R.array.dobong_market);
                    break;
                case (10):
                    popSubSpinner(R.array.dongdaemun_market);
                    break;
                case (11):
                    popSubSpinner(R.array.dongjak_market);
                    break;
                case (12):
                    popSubSpinner(R.array.mapo_market);
                    break;
                case (13):
                    popSubSpinner(R.array.seodaemun_market);
                    break;
                case (14):
                    popSubSpinner(R.array.seocho_market);
                    break;
                case (15):
                    popSubSpinner(R.array.seongdong_market);
                    break;
                case (16):
                    popSubSpinner(R.array.seongbuk_market);
                    break;
                case (17):
                    popSubSpinner(R.array.songpa_market);
                    break;
                case (18):
                    popSubSpinner(R.array.yangcheon_market);
                    break;
                case (19):
                    popSubSpinner(R.array.yeongdeungpo_market);
                    break;
                case (20):
                    popSubSpinner(R.array.yongsan_market);
                    break;
                case (21):
                    popSubSpinner(R.array.eunpyeong_market);
                    break;
                case (22):
                    popSubSpinner(R.array.jongro_market);
                    break;
                case (23):
                    popSubSpinner(R.array.junggu_market);
                    break;
                case (24):
                    popSubSpinner(R.array.jungrang_market);
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mulga__select);

        spinner = (Spinner)findViewById(R.id.spinner_Gu);
        spinner2 = (Spinner)findViewById(R.id.spinner_Market);

        final Button select_Btn = (Button)findViewById(R.id.select_Btn);

        popSpinner();
        popSubSpinner(R.array.gangnam_market);
        spinner.setOnItemSelectedListener(spinSelectedListener);

        //물가검색 버튼 클릭 시 원하는 시장의 물가를 검색하도록 한다.
        select_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                market_Name = (String)spinner2.getSelectedItem();
                Intent intent_String = new Intent(Mulga_Select.this,Mulga.class);
                intent_String.putExtra("market_Name", market_Name);
                startActivity(intent_String);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
