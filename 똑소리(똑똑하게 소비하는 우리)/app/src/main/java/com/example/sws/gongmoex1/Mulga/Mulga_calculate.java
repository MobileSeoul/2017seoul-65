package com.example.sws.gongmoex1.Mulga;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sws.gongmoex1.CustomActivity;
import com.example.sws.gongmoex1.R;

import java.util.ArrayList;

public class Mulga_calculate extends CustomActivity implements View.OnClickListener {


    private ArrayList<Mulga_Cal_Item> mulga_cal_items;

    String cal_market, cal_gu;

    TextView txt_cal_market, txt_cal_gu, txt_cal_result;

    TextView[] calSelNum, calPrice;
    Button[] cal_Btn_Inc, cal_Btn_Dec;

    int result_Price = 0;
    int[] price;
    int[] pick_num = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    String num_result;

    ArrayList<Integer> cal_Pick_Num;

    int[] calselNum_ID, calPriceID, calBtnInc, calBtnDec;

    FloatingActionButton cal_Fab;
    PopupMenu popupMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mulga_calculate);

        cal_Pick_Num = new ArrayList<Integer>();

        //17개의 레이아웃에서 제품가격, 증가버튼, 감소버튼, 선택한 개수를 등록
        calselNum_ID = new int[]{R.id.cal_sel_Num_1, R.id.cal_sel_Num_2, R.id.cal_sel_Num_3, R.id.cal_sel_Num_4, R.id.cal_sel_Num_5, R.id.cal_sel_Num_6, R.id.cal_sel_Num_7, R.id.cal_sel_Num_8,
                R.id.cal_sel_Num_9, R.id.cal_sel_Num_10, R.id.cal_sel_Num_11, R.id.cal_sel_Num_12, R.id.cal_sel_Num_13, R.id.cal_sel_Num_14, R.id.cal_sel_Num_15, R.id.cal_sel_Num_16, R.id.cal_sel_Num_17};

        calPriceID = new int[]{R.id.cal_price_1, R.id.cal_price_2, R.id.cal_price_3, R.id.cal_price_4, R.id.cal_price_5, R.id.cal_price_6, R.id.cal_price_7, R.id.cal_price_8, R.id.cal_price_9,
                R.id.cal_price_10, R.id.cal_price_11, R.id.cal_price_12, R.id.cal_price_13, R.id.cal_price_14, R.id.cal_price_15, R.id.cal_price_16, R.id.cal_price_17};
        calBtnInc = new int[]{R.id.cal_Inc_Btn_1, R.id.cal_Inc_Btn_2, R.id.cal_Inc_Btn_3, R.id.cal_Inc_Btn_4, R.id.cal_Inc_Btn_5, R.id.cal_Inc_Btn_6, R.id.cal_Inc_Btn_7, R.id.cal_Inc_Btn_8, R.id.cal_Inc_Btn_9,
                R.id.cal_Inc_Btn_10, R.id.cal_Inc_Btn_11, R.id.cal_Inc_Btn_12, R.id.cal_Inc_Btn_13, R.id.cal_Inc_Btn_14, R.id.cal_Inc_Btn_15, R.id.cal_Inc_Btn_16, R.id.cal_Inc_Btn_17};
        calBtnDec = new int[]{R.id.cal_Dec_Btn_1, R.id.cal_Dec_Btn_2, R.id.cal_Dec_Btn_3, R.id.cal_Dec_Btn_4, R.id.cal_Dec_Btn_5, R.id.cal_Dec_Btn_6, R.id.cal_Dec_Btn_7, R.id.cal_Dec_Btn_8, R.id.cal_Dec_Btn_9,
                R.id.cal_Dec_Btn_10, R.id.cal_Dec_Btn_11, R.id.cal_Dec_Btn_12, R.id.cal_Dec_Btn_13, R.id.cal_Dec_Btn_14, R.id.cal_Dec_Btn_15, R.id.cal_Dec_Btn_16, R.id.cal_Dec_Btn_17};

        txt_cal_market = (TextView)findViewById(R.id.cal_market_Name);
        txt_cal_gu = (TextView)findViewById(R.id.cal_gu_Name);
        cal_Fab = (FloatingActionButton)findViewById(R.id.cal_fab);

        calSelNum = new TextView[17];
        calPrice = new TextView[17];
        cal_Btn_Inc = new Button[17];
        cal_Btn_Dec = new Button[17];

        txt_cal_result = (TextView)findViewById(R.id.cal_result);

        Intent intent = getIntent();

        cal_market = intent.getExtras().getString("cal_market");
        cal_gu = intent.getExtras().getString("cal_gu");

        txt_cal_market.setText(cal_market);
        txt_cal_gu.setText(cal_gu);

        mulga_cal_items = new ArrayList<>();
        mulga_cal_items = (ArrayList<Mulga_Cal_Item>)intent.getSerializableExtra("cal_object");

        cal_First();
        //set_Product();
        set_Price();
        set_cal_Num();
        set_Btn_Inc();
        set_Btn_Dec();
        BtnOnClick();

        cal_Fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupMenu = new PopupMenu(Mulga_calculate.this,view);
                MenuInflater inflater = popupMenu.getMenuInflater();
                Menu menu = popupMenu.getMenu();
                inflater.inflate(R.menu.popupmenu, menu);
                popupMenu.setGravity(Gravity.CENTER);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.bulgogi:
                                String bulgogi_Uri = "https://www.hansik.org/kr/board.do?cmd=view&bbs_id=021&menu=pkr2020100&art_id=2006&lang=kr&preSearchType=ALL&preSearchWord=%EB%B6%88%EA%B3%A0%EA%B8%B0&recipeType=&searchType=ALL&searchWord=%EB%B6%88%EA%B3%A0%EA%B8%B0&cpage=1&row=8";
                                product_Recipe(bulgogi_Uri);
                                break;
                            case R.id.japchae:
                                String japchae_Uri = "https://www.hansik.org/kr/board.do?cmd=view&bbs_id=021&menu=pkr2020100&art_id=2007&lang=kr&preSearchType=ALL&preSearchWord=%EC%87%A0%EA%B3%A0%EA%B8%B0&recipeType=&searchType=ALL&searchWord=%EC%87%A0%EA%B3%A0%EA%B8%B0&cpage=1&row=8";
                                product_Recipe(japchae_Uri);
                                break;
                            case R.id.jangjorim:
                                String jangjorim_Uri = "https://www.hansik.org/kr/board.do?cmd=view&bbs_id=021&menu=pkr2020100&art_id=1938&lang=kr&preSearchType=ALL&preSearchWord=%EC%87%A0%EA%B3%A0%EA%B8%B0&recipeType=&searchType=ALL&searchWord=%EC%87%A0%EA%B3%A0%EA%B8%B0&cpage=2&row=8";
                                product_Recipe(jangjorim_Uri);
                                break;
                            case R.id.jaeyouk:
                                String jayouk_Uri = "https://www.hansik.org/kr/board.do?cmd=view&bbs_id=021&menu=pkr2020100&art_id=2005&lang=kr&preSearchType=ALL&preSearchWord=%EB%8F%BC%EC%A7%80&recipeType=&searchType=ALL&searchWord=%EB%8F%BC%EC%A7%80&cpage=1&row=8";
                                product_Recipe(jayouk_Uri);
                                break;
                            case R.id.ttakjjim:
                                String ttakjjim_Uri = "http://www.hansik.org/kr/board.do?cmd=view&bbs_id=021&menu=pkr2020100&art_id=1893&lang=kr&preSearchType=ALL&preSearchWord=%EB%8B%AD&recipeType=&searchType=ALL&searchWord=%EB%8B%AD&cpage=1&row=8";
                                product_Recipe(ttakjjim_Uri);
                                break;
                            case R.id.samgyetang:
                                String samgyetang_Uri = "http://www.hansik.org/kr/board.do?cmd=view&bbs_id=021&menu=pkr2020100&art_id=1716&lang=kr&preSearchType=ALL&preSearchWord=%EB%8B%AD&recipeType=&searchType=ALL&searchWord=%EB%8B%AD&cpage=1&row=8";
                                product_Recipe(samgyetang_Uri);
                                break;
                            case R.id.jogigui:
                                String jogigui_Uri = "http://www.hansik.org/kr/board.do?cmd=view&bbs_id=021&menu=pkr2020100&art_id=2003&lang=kr&preSearchType=ALL&preSearchWord=%EC%A1%B0%EA%B8%B0&recipeType=&searchType=ALL&searchWord=%EC%A1%B0%EA%B8%B0&cpage=1&row=8";
                                product_Recipe(jogigui_Uri);
                                break;
                            case R.id.kimchijjigae:
                                String kimchijjigae_Uri = "https://www.hansik.org/kr/board.do?cmd=view&bbs_id=021&menu=pkr2020100&art_id=1660&lang=kr&preSearchType=ALL&preSearchWord=%EB%8F%BC%EC%A7%80&recipeType=&searchType=ALL&searchWord=%EB%8F%BC%EC%A7%80&cpage=1&row=8";
                                product_Recipe(kimchijjigae_Uri);
                                break;
                            case R.id.dongtaejeon:
                                String dongtaejeon_Uri = "https://www.hansik.org/kr/board.do?cmd=view&bbs_id=021&menu=pkr2020100&art_id=1947&lang=kr&preSearchType=ALL&preSearchWord=%EB%8F%99%ED%83%9C&recipeType=&searchType=ALL&searchWord=%EB%8F%99%ED%83%9C&cpage=1&row=8";
                                product_Recipe(dongtaejeon_Uri);
                                break;
                            case R.id.ttakgalbi:
                                String ttakgalbi_Uri = "https://www.hansik.org/kr/board.do?cmd=view&bbs_id=004&menu=pkr2010100&art_id=2351&lang=kr&preSearchType=&preSearchWord=&recipeType=&searchType=ALL&searchWord=&cpage=1&row=8";
                                product_Recipe(ttakgalbi_Uri);
                                break;
                            case R.id.jogittang:
                                String jogittang_Uri = "http://yummycook.blog.me/70165552939";
                                product_Recipe(jogittang_Uri);
                                break;
                            case R.id.jogijorim:
                                String jogijorim_Uri = "http://blog.naver.com/mohangrim/221042245678";
                                product_Recipe(jogijorim_Uri);
                                break;
                            case R.id.dongtaejorim:
                                String dongtaejorim_Uri = "http://blog.naver.com/teaser1/220908374861";
                                product_Recipe(dongtaejorim_Uri);
                                break;
                            case R.id.dongtaejjigae:
                                String dongtaejjigae_Uri = "http://blog.naver.com/gibera/221123601916";
                                product_Recipe(dongtaejjigae_Uri);
                                break;
                            case R.id.jaeyouk2:
                                String jaeyouk2_Uri = "http://blog.naver.com/bdan9333/221121084586";
                                product_Recipe(jaeyouk2_Uri);
                                break;
                            case R.id.ojingeoguk:
                                String ojingeoguk_Uri = "http://blog.naver.com/bdan9333/221110985076";
                                product_Recipe(ojingeoguk_Uri);
                                break;
                            case R.id.ojingeodeopbap:
                                String ojingeodeopbap_Uri = "http://blog.naver.com/ykkim9500/221091841276";
                                product_Recipe(ojingeodeopbap_Uri);
                                break;
                            case R.id.friedojingeo:
                                String friedojingeo_Uri = "http://blog.naver.com/indiblue/90188488015";
                                product_Recipe(friedojingeo_Uri);
                                break;
                            case R.id.godeungeojorim:
                                String godeungeojorim_Uri = "http://blog.naver.com/yoonjs3/221097265571";
                                product_Recipe(godeungeojorim_Uri);
                                break;
                            case R.id.gogalbi:
                                String gogalbi_Uri = "http://kimdahye.com/221068088323";
                                product_Recipe(gogalbi_Uri);
                                break;
                            case R.id.godeungeokimchijjim:
                                String godeungeokimchijjim_Uri = "http://blog.naver.com/dksmf2626/221030491173";
                                product_Recipe(godeungeokimchijjim_Uri);
                                break;

                        }
                        return false;
                    }
                });

                popupMenu.show();


            }
        });
    }

    //선택된 요리 레시피를 알려주는 사이트로의 이동 메소드
    private void product_Recipe(String product){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(product));
        startActivity(intent);
    }

    //제품의 수량 선언
    public void set_cal_Num(){
        for (int i =0; i<calSelNum.length; i++){
            calSelNum[i] = (TextView)findViewById(calselNum_ID[i]);
            calSelNum[i].setText(String.valueOf(pick_num[i]));
        }
    }

    //증가 버튼 선언
    public void set_Btn_Inc(){
        for (int i = 0; i<calBtnInc.length;i++){
            cal_Btn_Inc[i] = (Button)findViewById(calBtnInc[i]);
        }
    }

    //감소 버튼 선언
    public void set_Btn_Dec(){
        for (int i = 0 ; i<calBtnDec.length;i++){
            cal_Btn_Dec[i] = (Button)findViewById(calBtnDec[i]);
        }
    }

    //증가 버튼을 클릭시 calSelNum이 1씩 증가하고 가격을 계산하는 메소드
    private void btn_Inc(int i){
        pick_num[i]++;
        calSelNum[i].setText(String.valueOf(pick_num[i]));
        cal_Third();
    }
    //감소 버튼을 클릭시 calSelNum이 1씩 감소하고 가격을 계산하는 메소드
    private void btn_dec(int i){
        pick_num[i]--;
        if (pick_num[i]<0){
            pick_num[i] = 0;
        }else{
            calSelNum[i].setText(String.valueOf(pick_num[i]));
            cal_Third();
        }
    }

    //17개의 증가, 감소 버튼이 선택될 때마다, btn_inc()와 btn_dec()에 정보를 전달한다.
    //각각의 버튼에 맞는 pick_num 배열의 값을 수정하고 최종가격을 변화시킨다.
    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){
            case R.id.cal_Inc_Btn_1: btn_Inc(0); break;
            case R.id.cal_Inc_Btn_2: btn_Inc(1); break;
            case R.id.cal_Inc_Btn_3: btn_Inc(2); break;
            case R.id.cal_Inc_Btn_4: btn_Inc(3); break;
            case R.id.cal_Inc_Btn_5: btn_Inc(4); break;
            case R.id.cal_Inc_Btn_6: btn_Inc(5); break;
            case R.id.cal_Inc_Btn_7: btn_Inc(6); break;
            case R.id.cal_Inc_Btn_8: btn_Inc(7); break;
            case R.id.cal_Inc_Btn_9: btn_Inc(8); break;
            case R.id.cal_Inc_Btn_10: btn_Inc(9); break;
            case R.id.cal_Inc_Btn_11: btn_Inc(10); break;
            case R.id.cal_Inc_Btn_12: btn_Inc(11); break;
            case R.id.cal_Inc_Btn_13: btn_Inc(12); break;
            case R.id.cal_Inc_Btn_14: btn_Inc(13); break;
            case R.id.cal_Inc_Btn_15: btn_Inc(14); break;
            case R.id.cal_Inc_Btn_16: btn_Inc(15); break;
            case R.id.cal_Inc_Btn_17: btn_Inc(16); break;
            case R.id.cal_Dec_Btn_1: btn_dec(0); break;
            case R.id.cal_Dec_Btn_2: btn_dec(1); break;
            case R.id.cal_Dec_Btn_3: btn_dec(2); break;
            case R.id.cal_Dec_Btn_4: btn_dec(3); break;
            case R.id.cal_Dec_Btn_5: btn_dec(4); break;
            case R.id.cal_Dec_Btn_6: btn_dec(5); break;
            case R.id.cal_Dec_Btn_7: btn_dec(6); break;
            case R.id.cal_Dec_Btn_8: btn_dec(7); break;
            case R.id.cal_Dec_Btn_9: btn_dec(8); break;
            case R.id.cal_Dec_Btn_10: btn_dec(9); break;
            case R.id.cal_Dec_Btn_11: btn_dec(10); break;
            case R.id.cal_Dec_Btn_12: btn_dec(11); break;
            case R.id.cal_Dec_Btn_13: btn_dec(12); break;
            case R.id.cal_Dec_Btn_14: btn_dec(13); break;
            case R.id.cal_Dec_Btn_15: btn_dec(14); break;
            case R.id.cal_Dec_Btn_16: btn_dec(15); break;
            case R.id.cal_Dec_Btn_17: btn_dec(16); break;
        }
    }

    //증가, 감소 버튼들의 리스너 등록한다.
    public void BtnOnClick(){
        for (int i = 0; i<calBtnInc.length;i++){
            cal_Btn_Inc[i].setOnClickListener(this);
        }
        for (int i = 0 ; i<calBtnDec.length;i++){
            cal_Btn_Dec[i].setOnClickListener(this);
        }
    }

    //제품의 가격을 등록하는 메소드
    public void set_Price(){
        for (int i=0; i<calPriceID.length;i++){
            calPrice[i] = (TextView)findViewById(calPriceID[i]);
        }
        for(int i=0;i<calPriceID.length;i++){
            calPrice[i].setText(mulga_cal_items.get(i).getCal_price());
        }
    }

    //Json으로 받아온 제품의 가격을 price 배열에 전달한다.
    public  void cal_First(){
        price = new int[mulga_cal_items.size()];

        for (int i=0 ; i<mulga_cal_items.size() ; i++){
            price[i] = Integer.parseInt(mulga_cal_items.get(i).getCal_price());
        }
    }

    //가격과 수량에 따라 최종가격을 계산하는 메소드
    public void cal_Third(){
        for (int i=0; i<pick_num.length;i++){
            result_Price += price[i] * pick_num[i];
        }
        num_result = String.valueOf(result_Price);

        txt_cal_result.setText(num_result);

        result_Price = 0;
    }
    @Override
    public void onBackPressed() {
        finish();
    }

}
