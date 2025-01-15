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
import com.lank.AnimalShop.object.Product;


import java.util.ArrayList;

public class itemAdapter extends BaseAdapter {
    private ArrayList<Product> li;
    private Context context;
    private int layout;

    public itemAdapter(ArrayList<Product> li, Context context, int layout) {
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
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout, null);
            holder.imageView=view.findViewById(R.id.imgitem);
            holder.name=view.findViewById(R.id.tvnameitem);
            holder.price=view.findViewById(R.id.tvpriceitem);
            view.setTag(holder);
        }
        else{
            holder=(ViewHolder) view.getTag();
        }

        Product product=li.get(i);

        byte[] x=product.getImage();
        Bitmap bit= BitmapFactory.decodeByteArray(x,0,x.length);
        holder.imageView.setImageBitmap(bit);
        //holder.imageView.setImageResource(R.drawable.person);
        holder.name.setText(product.getName());
        holder.price.setText(String.valueOf(product.getPrice()));

        return view;
    }
}
