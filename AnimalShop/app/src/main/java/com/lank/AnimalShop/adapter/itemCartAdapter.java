package com.lank.AnimalShop.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.lank.AnimalShop.R;
import com.lank.AnimalShop.object.Cart;


import java.util.ArrayList;

public class itemCartAdapter extends BaseAdapter {
    private ArrayList<Cart> li;
    private Context context;
    private int layout;

    public itemCartAdapter(ArrayList<Cart> li, Context context, int layout) {
        this.li = li;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return li.size();
    }

    @Override
    public Object getItem(int i) {
        return li.get(i);
    }

    @Override
    public long getItemId(int i) {
        return li.get(i).getId();
    }

    private class ViewHolder{
        ImageView imageView;
        TextView name;
        TextView price;
        TextView number;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);
            holder.imageView=view.findViewById(R.id.imgcart);
            holder.name=view.findViewById(R.id.tvnamecart);
            holder.price=view.findViewById(R.id.tvpricecart);
            holder.number=view.findViewById(R.id.tvnumbercart);
            view.setTag(holder);
        }
        else{
            holder= (ViewHolder) view.getTag();
        }


        byte[] x=li.get(i).getImg();
        Bitmap bit= BitmapFactory.decodeByteArray(x,0,x.length);
        holder.imageView.setImageBitmap(bit);
        //holder.imageView.setImageResource(R.drawable.person);
        holder.name.setText(li.get(i).getName());
        holder.price.setText(String.valueOf(li.get(i).getPrice()));
        holder.number.setText(String.valueOf(li.get(i).getNumber()));
        return view;
    }
}
