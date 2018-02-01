package com.example.sws.gongmoex1.GoodStore;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.example.sws.gongmoex1.R;

import java.util.ArrayList;

/**
 * Created by sws on 2017-08-24.
 */

class GoodPriceAdapter extends RecyclerView.Adapter<GoodPriceAdapter.ViewHolder> implements Filterable{
    Context context;
    //검색전 배열(mArrayList)과 검색후 배열(mFilteredList)
    private ArrayList<GoodPriceListItem> mArrayList, mFilteredList;
    private int itemLayout;

    ImageLoader imageLoader;
    private int position = 0;

    public static final String KEY_NAME = "name";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_PRIDE = "pride";
    public static final String KEY_WAY = "way";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_INFORMATION = "information";
    public static final String KEY_IMAGE = "thumbnail";

    public GoodPriceAdapter(final Context context, ArrayList<GoodPriceListItem> items, int itemLayout) {
        this.context = context;
        this.mArrayList = items;
        this.mFilteredList = items;
        this.itemLayout = itemLayout;

        /*메모리 캐시는 어플리케이션의 중요한 memory를 소모하여 빠른 access를 제공. 저장해놓은
          사진이 아닌 url에서 받아온 사진을 바로 보여준다.*/
        imageLoader = new ImageLoader(Volley.newRequestQueue(context), new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<>(10);

            @Override
            public Bitmap getBitmap(String url) {
                    return mCache.get(url);
                }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                    mCache.put(url, bitmap);
                }
            });
        }

    public void updateList(ArrayList<GoodPriceListItem> items){
        this.mFilteredList = items;
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(itemLayout,parent,false);

        final ViewHolder holder = new ViewHolder(v);

        //착한가게의 카드뷰중 하나를 클릭하면 가게 정보 액티비티(ContactDetails 클래스)로 각각의 정보를 intent
        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                position = holder.getAdapterPosition();
                GoodPriceListItem goodPriceListItem = mFilteredList.get(position);
                Intent intent = new Intent(view.getContext(), ContactDetails.class);
                intent.putExtra(KEY_NAME, goodPriceListItem.getName());
                intent.putExtra(KEY_ADDRESS, goodPriceListItem.getAddress());
                intent.putExtra(KEY_PRIDE, goodPriceListItem.getPride());
                intent.putExtra(KEY_WAY, goodPriceListItem.getWay());
                intent.putExtra(KEY_PHONE, goodPriceListItem.getPhone());
                intent.putExtra(KEY_IMAGE, goodPriceListItem.getImage());
                intent.putExtra(KEY_INFORMATION, goodPriceListItem.getInformation());
                view.getContext().startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View view) {
                position = holder.getAdapterPosition();
                return true;
            }
        });
        return holder;

    }

    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.name.setText(mFilteredList.get(position).getName());
        holder.address.setText(mFilteredList.get(position).getAddress());
        holder.way.setText(mFilteredList.get(position).getWay());
        holder.info.setText(mFilteredList.get(position).getInfo());
        holder.image.setImageUrl(mFilteredList.get(position).getImage(), imageLoader);
        holder.Rank.setText(String.valueOf(mFilteredList.get(position).getRank()));
        holder.pride.setText(mFilteredList.get(position).getPride());
        holder.phone.setText(mFilteredList.get(position).getPhone());
        holder.information.setText(mFilteredList.get(position).getInformation());
        //이미지를 배경에 넣기위해 어둡게 설정
        holder.image.setColorFilter(Color.parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY);
        context = holder.image.getContext();

        //열린데이터 광장에 이미지가 한글로 돼있어서 따로 설정
        String name = mFilteredList.get(position).getName();
        if(name.contains("한독세탁")) {
            String url = "http://mulga.seoul.go.kr/inc/img_view.jsp?filename=%C7%D1%B5%B6%BC%BC%C5%B9.JPG";
            holder.image.setImageUrl(url, imageLoader);
        }
        else if(name.contains("참 맛있는 순대국")){
                String url = "http://mulga.seoul.go.kr/inc/img_view.jsp?filename=%BB%E7%C1%F8080519_005.jpg";
                holder.image.setImageUrl(url,imageLoader);
        }
        else if(name.contains("금강숯불생고기")) {
            String url = "http://mulga.seoul.go.kr/inc/img_view.jsp?filename=%B1%DD%B0%AD%BD%A1%BA%D2%BB%FD%B0%ED%B1%E2.JPG";
            holder.image.setImageUrl(url, imageLoader);
        }
        else if(name.contains("머리잘하는집")) {
            String url = "http://mulga.seoul.go.kr/inc/img_view.jsp?filename=%B8%D3%B8%AE%C0%DF%C7%CF%B4%C2%C1%FD.JPG";
            holder.image.setImageUrl(url, imageLoader);
        }
        else if(name.contains("은미용실")) {
            String url = "http://mulga.seoul.go.kr/inc/img_view.jsp?filename=%C0%BA%B9%CC%BF%EB%BD%C7.jpg";
            holder.image.setImageUrl(url, imageLoader);
        }
        else if(name.contains("주연미용실")) {
            String url = "http://mulga.seoul.go.kr/inc/img_view.jsp?filename=%B9%B0%B0%A1%B0%FC%B7%C3%B4%DC%C3%BC%20%B1%B3%C0%B0%20009.jpg";
            holder.image.setImageUrl(url, imageLoader);
        }
        else if(name.contains("동경헤어월드")) {
            String url = "http://mulga.seoul.go.kr/inc/img_view.jsp?filename=%B9%B0%B0%A1%B0%FC%B7%C3%B4%DC%C3%BC%20%B1%B3%C0%B0%20008.jpg";
            holder.image.setImageUrl(url, imageLoader);
        }
        else if(name.contains("동일미용실")) {
            String url = "http://mulga.seoul.go.kr/inc/img_view.jsp?filename=%B5%BF%C0%CF.jpg";
            holder.image.setImageUrl(url, imageLoader);
        }
        else if(name.contains("새론헤어라인")) {
            String url = "http://mulga.seoul.go.kr/inc/img_view.jsp?filename=2.4%BA%D0%B1%E2%20%B0%A1%B0%DD%BE%C8%C1%A4%20%B8%F0%B9%FC%BE%F7%BC%D2%20005.jpg";
            holder.image.setImageUrl(url, imageLoader);
        }
        else if(name.contains("채미용실")) {
            String url = "http://mulga.seoul.go.kr/inc/img_view.jsp?filename=%C3%A4%B9%CC%BF%EB%BD%C71.JPG";
            holder.image.setImageUrl(url, imageLoader);
        }
        else if(name.contains("아씨머리방")) {
            String url = "http://mulga.seoul.go.kr/inc/img_view.jsp?filename=%BE%C6%BE%BE%B9%CC%BF%EB%BD%C7.JPG";
            holder.image.setImageUrl(url, imageLoader);
        }
        else if(name.contains("머리하는집")) {
            String url = "http://mulga.seoul.go.kr/inc/img_view.jsp?filename=%B8%D3%B8%AE%C7%CF%B4%C2%C1%FD.JPG";
            holder.image.setImageUrl(url, imageLoader);
        }
        else if(name.contains("백양세탁소")) {
            String url = "http://mulga.seoul.go.kr/inc/img_view.jsp?filename=%B9%E9%BE%E7%BC%BC%C5%B9%BC%D2.JPG";
            holder.image.setImageUrl(url, imageLoader);
        }
        else if(name.contains("백리향")) {
            String url = "http://mulga.seoul.go.kr/inc/img_view.jsp?filename=%B9%E9%B8%AE%C7%E2.jpg";
            holder.image.setImageUrl(url, imageLoader);
        }
        else if(name.contains("럭키미용실")) {
            String url = "http://mulga.seoul.go.kr/inc/img_view.jsp?filename=%B7%B0%C5%B0%B9%CC%BF%EB%BD%C7.JPG";
            holder.image.setImageUrl(url, imageLoader);
        }
        else if(name.contains("대성식당")) {
            String url = "http://mulga.seoul.go.kr/inc/img_view.jsp?filename=%B4%EB%BC%BA%BD%C4%B4%E7.JPG";
            holder.image.setImageUrl(url, imageLoader);
        }
        else if(name.contains("약속다방")) {
            String url = "http://mulga.seoul.go.kr/inc/img_view.jsp?filename=%BE%E0%BC%D3.jpg";
            holder.image.setImageUrl(url, imageLoader);
        }
        else if(name.contains("우리식당")) {
            String url = "http://mulga.seoul.go.kr/inc/img_view.jsp?filename=08%BB%F3%B9%DD%B1%E2%20%B8%F0%B9%FC%BE%F7%BC%D2%20020.jpg";
            holder.image.setImageUrl(url, imageLoader);
        }
    }
    
    public int getItemCount() {
        return mFilteredList.size();
    }

    //카드뷰에서 아이템을 검색하기 위해 getFilter()사용한다.
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = mArrayList;
                } else {

                    ArrayList<GoodPriceListItem> filteredList = new ArrayList<>();

                    for (GoodPriceListItem goodPriceListItem : mArrayList) {

                        if (goodPriceListItem.getInfo().toUpperCase().contains(charString) || goodPriceListItem.getName().toUpperCase().contains(charString)
                                || goodPriceListItem.getAddress().toUpperCase().contains(charString)) {

                            filteredList.add(goodPriceListItem);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<GoodPriceListItem>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name, address, info, Rank, pride, way, phone, information;
        public NetworkImageView image;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            address = (TextView) itemView.findViewById(R.id.address);
            info = (TextView) itemView.findViewById(R.id.info);
            image = (NetworkImageView) itemView.findViewById(R.id.image);
            Rank = (TextView)itemView.findViewById(R.id.Rank);
            pride = (TextView)itemView.findViewById(R.id.pride);
            way = (TextView)itemView.findViewById(R.id.way);
            phone = (TextView)itemView.findViewById(R.id.phone);
            information = (TextView)itemView.findViewById(R.id.information);

        }

    }
}
