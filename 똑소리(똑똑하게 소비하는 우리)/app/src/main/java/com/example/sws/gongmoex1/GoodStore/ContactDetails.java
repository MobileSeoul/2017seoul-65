package com.example.sws.gongmoex1.GoodStore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sws.gongmoex1.CustomActivity;
import com.example.sws.gongmoex1.R;

public class ContactDetails extends CustomActivity{

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_detail);

        imageView = (ImageView)findViewById(R.id.image);

        //변수들의 초기화
        String name = ""; //가게 이름
        String address = ""; //가게 주소
        String pride = ""; //가게 자랑거리
        String way = ""; //찾아오는길
        String phone = ""; //가게 전화번호
        String information = ""; //영업 정보
        String thumbnail = ""; // 가게 사진

        Intent intent = getIntent();
        if( null != intent){
            name = intent.getStringExtra(GoodPriceAdapter.KEY_NAME);
            address = intent.getStringExtra(GoodPriceAdapter.KEY_ADDRESS);
            pride = intent.getStringExtra(GoodPriceAdapter.KEY_PRIDE);
            way = intent.getStringExtra(GoodPriceAdapter.KEY_WAY);
            phone = intent.getStringExtra(GoodPriceAdapter.KEY_PHONE);
            information = intent.getStringExtra(GoodPriceAdapter.KEY_INFORMATION);
            thumbnail = intent.getStringExtra(GoodPriceAdapter.KEY_IMAGE);
        }

        //착한가게 정보에 담길 변수들을 할당
        TextView nameTxt = (TextView)findViewById(R.id.txtName);
        nameTxt.setText(name);

        TextView addressTxt = (TextView)findViewById(R.id.txtAddress);
        addressTxt.setText(address);

        TextView prideTxt = (TextView)findViewById(R.id.txtPride);
        prideTxt.setText(pride);

        TextView wayTxt = (TextView)findViewById(R.id.txtWay);
        wayTxt.setText(way);

        TextView phoneTxt = (TextView)findViewById(R.id.txtPhone);
        phoneTxt.setText(phone);

        TextView informationTxt = (TextView)findViewById(R.id.txtInformation);
        informationTxt.setText(information);

        Glide.with(this).load(thumbnail)
                .into(imageView);

    }

}
