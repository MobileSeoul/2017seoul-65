package com.example.sws.gongmoex1.Mulga;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.sws.gongmoex1.CustomActivity;
import com.example.sws.gongmoex1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import static com.example.sws.gongmoex1.R.id.mulga_recycler_view;

/**
 * Created by sws on 2017-09-08.
 */

public class Mulga extends CustomActivity {

    //공공데이터를 얻기 위한 key
    String key = "424c7776517377733637684558516f";

    String maktet="";

    String str;

    Mulga_Adapter adapter_mulga;
    RecyclerView recyclerView_mulga;

    private ArrayList<ViewItem_mulga> viewItem_mulga;
    private ArrayList<Mulga_Cal_Item> mulga_cal_items;

    String mulga_cal_Price, mulga_cal_Market, mulga_cal_Product, mulga_cal_Gu;

    int cal_count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mulga_main);

        final Button calculate_Btn = (Button)findViewById(R.id.calculate_Btn);

        //recyclerview 선언
        recyclerView_mulga = (RecyclerView)findViewById(mulga_recycler_view);
        recyclerView_mulga.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView_mulga.setLayoutManager(layoutManager);

        viewItem_mulga = new ArrayList<>();
        mulga_cal_items = new ArrayList<>();

        //recyclerview에 adapter 연결
        adapter_mulga = new Mulga_Adapter(this, viewItem_mulga, R.layout.mulga_listmodel);
        recyclerView_mulga.setAdapter(adapter_mulga);

        recyclerView_mulga.setItemAnimator(new DefaultItemAnimator());

        maktet = getIntent().getStringExtra("market_Name");

        try {

            str = URLEncoder.encode(maktet,"UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        sendRequest();

        calculate_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_calculate = new Intent(getApplicationContext(),Mulga_calculate.class);
                intent_calculate.putExtra("cal_market", mulga_cal_Market);
                intent_calculate.putExtra("cal_gu", mulga_cal_Gu);
                intent_calculate.putExtra("cal_object", mulga_cal_items);
                startActivity(intent_calculate);

            }
        });

    }

    //Json객체를 얻기 위한 통신 메소드(Volley활용)
    public void sendRequest(){
        RequestQueue queue = Volley.newRequestQueue(this);

        //1~1000번째의 json객체 중 검색
        String url = "http://openAPI.seoul.go.kr:8088/"+key+"/json/ListNecessariesPricesService/1/1000/"+str;

        JsonObjectRequest jsonObjectRequest2 =
                new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        parseJSON(response);

                        adapter_mulga.updateList(viewItem_mulga);

                    }
                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        sendRequest2();
                        Toast.makeText(Mulga.this, "죄송합니다. 다시 시작해주세요", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonObjectRequest2);

    }

    //Json객체를 얻기 위한 통신 메소드 2 (Volley활용)
    public void sendRequest2(){
        RequestQueue queue2 = Volley.newRequestQueue(this);

        //1001~2000번째의 json객체 중 검색
        String url = "http://openAPI.seoul.go.kr:8088/"+key+"/json/ListNecessariesPricesService/1001/2000/"+str;

        JsonObjectRequest jsonObjectRequest3 =
                new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parseJSON(response);
                        adapter_mulga.updateList(viewItem_mulga);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue2.add(jsonObjectRequest3);
    }


    //받아온 Json객체 중 필요한 데이터 추출
    private void parseJSON(JSONObject json){

        try {
            JSONObject value = json.getJSONObject("ListNecessariesPricesService");
            JSONArray items = value.getJSONArray("row");


            for (int i = 0; i<items.length();i++){
                JSONObject item = items.getJSONObject(i);
                JSONObject item2 = items.getJSONObject(i);

                ViewItem_mulga mulga_item = new ViewItem_mulga();
                Mulga_Cal_Item mulgaCalItem = new Mulga_Cal_Item();

                //Json객체에 담겨있는 데이트를 각각 저장
                mulga_item.setMktName(item.optString("M_NAME"));
                mulga_item.setProduct_Name(item.optString("A_NAME"));
                mulga_item.setProduct_Price(item.optString("A_PRICE"));
                mulga_item.setGu_name(item.optString("M_GU_NAME"));
                mulga_item.setUnit(item.optString("A_UNIT"));
                mulga_item.setDate(item.optString("P_DATE"));
                mulga_item.setInfo(item.optString("ADD_COL"));

                //mulga_calculate에 전달한 데이터 저장
                if (cal_count<17){
                    mulga_cal_Price = item.optString("A_PRICE");
                    mulga_cal_Product = item.optString("A_NAME");
                    mulga_cal_Gu = item.optString("M_GU_NAME");
                    mulga_cal_Market = item.optString("M_NAME");

                    mulgaCalItem.setCal_product(mulga_cal_Product);
                    mulgaCalItem.setCal_price(mulga_cal_Price);
                    mulga_cal_items.add(mulgaCalItem);
                    cal_count++;
                }

                viewItem_mulga.add(mulga_item);

                adapter_mulga = new Mulga_Adapter(this,viewItem_mulga,R.layout.mulga_listmodel);
                recyclerView_mulga.setAdapter(adapter_mulga);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        searchView.setQueryHint("찾을 물품 ex) 고등어 or 달걀");

        //searchview 힌트 글씨색은 회색, 검색할때의 색은 검은색으로 설정
        SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete)searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setHintTextColor(Color.GRAY);
        searchAutoComplete.setTextColor(Color.BLACK);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private void search(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (adapter_mulga != null) adapter_mulga.getFilter().filter(newText);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}


