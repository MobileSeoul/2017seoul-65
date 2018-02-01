package com.example.sws.gongmoex1.Mulga;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sws.gongmoex1.R;

import java.util.ArrayList;

/**
 * Created by Hoon on 2017-09-04.
 */

public class Mulga_Adapter extends RecyclerView.Adapter<Mulga_Adapter.ViewHolder> implements Filterable{
    private Context context;
    private ArrayList<ViewItem_mulga> mArrayList, mFilteredList;
    private int itemLayout;

    public Mulga_Adapter(Context context, ArrayList<ViewItem_mulga> items, int itemLayout){
        this.context = context;
        this.mArrayList = items;
        this.mFilteredList = items;
        this.itemLayout = itemLayout;
    }

    public void updateList(ArrayList<ViewItem_mulga> items){
        this.mArrayList = items;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Mulga_Adapter.ViewHolder holder, int position) {

        holder.mkt_Name.setText(mFilteredList.get(position).getMktName());
        holder.Product_Name.setText(mFilteredList.get(position).getProduct_Name());
        holder.Product_Price.setText(mFilteredList.get(position).getProduct_Price());
        holder.Gu_NAme.setText(mFilteredList.get(position).getGu_name());
        holder.unit.setText(mFilteredList.get(position).getUnit());
        holder.Date.setText(mFilteredList.get(position).getDate());
        holder.Info.setText(mFilteredList.get(position).getInfo());

        String name = mFilteredList.get(position).getProduct_Name();
        String received_name = mFilteredList.get(position).getProduct_Name();

        if (name.contains("사과")){
            holder.imageView3.setImageResource(R.drawable.apple);
            holder.imageView3.setColorFilter(Color.parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY);
        }else if (name.contains("배(")){
            holder.imageView3.setImageResource(R.drawable.pear);
            holder.imageView3.setColorFilter(Color.parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY);
        }else if (name.contains("배추")){
            holder.imageView3.setImageResource(R.drawable.cabbage);
            holder.imageView3.setColorFilter(Color.parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY);
        }else if (name.contains("무")){
            holder.imageView3.setImageResource(R.drawable.radish);
            holder.imageView3.setColorFilter(Color.parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY);
        }else if (name.contains("양파")){
            holder.imageView3.setImageResource(R.drawable.onion);
            holder.imageView3.setColorFilter(Color.parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY);
        }else if (name.contains("상추")){
            holder.imageView3.setImageResource(R.drawable.lettuce);
            holder.imageView3.setColorFilter(Color.parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY);
        }else if (name.contains("오이")){
            holder.imageView3.setImageResource(R.drawable.cucumber);
            holder.imageView3.setColorFilter(Color.parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY);
        }else if (name.contains("호박")){
            holder.imageView3.setImageResource(R.drawable.pumpkin);
            holder.imageView3.setColorFilter(Color.parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY);
        }else if (name.contains("쇠고기")){
            holder.imageView3.setImageResource(R.drawable.beef);
            holder.imageView3.setColorFilter(Color.parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY);
        }else if (name.contains("돼지고기")){
            holder.imageView3.setImageResource(R.drawable.pork);
            holder.imageView3.setColorFilter(Color.parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY);
        }else if (name.contains("닭고기")){
            holder.imageView3.setImageResource(R.drawable.chicken);
            holder.imageView3.setColorFilter(Color.parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY);
        }else if (name.contains("달걀")){
            holder.imageView3.setImageResource(R.drawable.egg);
            holder.imageView3.setColorFilter(Color.parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY);
        }else if (name.contains("조기")){
            holder.imageView3.setImageResource(R.drawable.croaker);
            holder.imageView3.setColorFilter(Color.parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY);
        }else if (name.contains("명태")) {
            holder.imageView3.setImageResource(R.drawable.pollock);
            holder.imageView3.setColorFilter(Color.parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY);
        }else if (name.contains("동태")){
            holder.imageView3.setImageResource(R.drawable.pollock);
            holder.imageView3.setColorFilter(Color.parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY);
        }else if (name.contains("오징어")){
            holder.imageView3.setImageResource(R.drawable.squid);
            holder.imageView3.setColorFilter(Color.parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY);
        }else if (name.contains("고등어")){
            holder.imageView3.setImageResource(R.drawable.mackerel);
            holder.imageView3.setColorFilter(Color.parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY);
        }if(name.equals("배")) {
            holder.imageView3.setImageResource(R.drawable.pear);
            holder.imageView3.setColorFilter(Color.parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = mArrayList;
                } else {

                    ArrayList<ViewItem_mulga> filteredList = new ArrayList<>();

                    for (ViewItem_mulga viewItem_mulga : mArrayList) {

                        if (viewItem_mulga.getProduct_Name().toUpperCase().contains(charString) || viewItem_mulga.getGu_name().toUpperCase().contains(charString)
                                || viewItem_mulga.getMktName().toUpperCase().contains(charString)) {

                            filteredList.add(viewItem_mulga);
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
                mFilteredList = (ArrayList<ViewItem_mulga>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mkt_Name, Product_Name, Product_Price, Gu_NAme, unit, Date, Info;
        public CardView cardView;
        ImageView imageView3;

        public ViewHolder(View itemView){
            super(itemView);

            cardView=(CardView)itemView.findViewById(R.id.card_view);
            mkt_Name = (TextView)itemView.findViewById(R.id.mkt_Name);
            Product_Name = (TextView)itemView.findViewById(R.id.Product_Name);
            Product_Price = (TextView)itemView.findViewById(R.id.Product_Price);
            Gu_NAme = (TextView)itemView.findViewById(R.id.Gu_Name);
            unit = (TextView)itemView.findViewById(R.id.unit);
            Date = (TextView)itemView.findViewById(R.id.date);
            Info = (TextView)itemView.findViewById(R.id.info);
            imageView3 = (ImageView)itemView.findViewById(R.id.imageView3);
            imageView3.setColorFilter(Color.parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY);
        }


    }
}
