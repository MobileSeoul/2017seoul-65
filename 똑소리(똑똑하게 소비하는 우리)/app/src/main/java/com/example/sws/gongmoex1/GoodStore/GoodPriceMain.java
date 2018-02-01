package com.example.sws.gongmoex1.GoodStore;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
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

public class GoodPriceMain extends CustomActivity {

    //카드뷰를 위한 recyclerView 선언
    RecyclerView recyclerView;
    //volley로 데이터를 받기위해 requestQueue 선언
    private RequestQueue requestQueue;

    private ArrayList<GoodPriceListItem> goodPriceListItems;
    GoodPriceAdapter adapter;

    String str = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goodstore_main);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        goodPriceListItems = new ArrayList<>();

        //착한가게 카드뷰를 위한 어댑터
        adapter = new GoodPriceAdapter(this, goodPriceListItems, R.layout.goodstore_listmodel);
        recyclerView.setAdapter(adapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        setVolley();

        //어떤 카드뷰를 선택했는지 확인하기 위한 Touch Event
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                if(e.getAction() == MotionEvent.ACTION_DOWN){
                    View reV = rv.findChildViewUnder(e.getX(), e.getY());
                    int position = rv.getChildAdapterPosition(reV);
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        try {

            str = URLEncoder.encode("한독세탁","UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

        }

    //열린데이터 광장의 인증키를 받아오기
    public void setVolley(){

        requestQueue = Volley.newRequestQueue(this);

        String URL = "http://openapi.seoul.go.kr:8088/477a4a706273777337356f476b4d6e/json/ListPriceModelStoreService/1/1000/";

        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.GET, URL, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        ParseJSON(response);

                        adapter.updateList(goodPriceListItems);

                    }
                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GoodPriceMain.this, "죄송합니다. 다시 시작해주세요", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }

    //보여질 카드뷰에 필요한 정보들만 받아오기
    private void ParseJSON(JSONObject json) {

            try {
                JSONObject value = json.getJSONObject("ListPriceModelStoreService");
                JSONArray items = value.getJSONArray("row");

                for (int i = 0; i < items.length(); i++) {
                    JSONObject o = items.getJSONObject(i);

                    GoodPriceListItem item = new GoodPriceListItem();
                    item.setName(o.optString("SH_NAME"));
                    item.setAddress(o.optString("SH_ADDR"));
                    item.setImage(o.optString("SH_PHOTO"));
                    item.setInfo(o.optString("INDUTY_CODE_SE_NAME"));
                    item.setRank(o.optInt("SH_RCMN"));
                    item.setPride(o.optString("SH_PRIDE"));
                    item.setWay(o.optString("SH_WAY"));
                    item.setPhone(o.optString("SH_PHONE"));
                    item.setInformation(o.optString("SH_INFO"));

                    goodPriceListItems.add(item);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //원하는 가게의 검색을 위해 menu_main.xml 에서 선언한 searchView 사용
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        searchView.setQueryHint("검색 ex)미아동 or 할범탕수육");

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

                if (adapter != null) adapter.getFilter().filter(newText);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
