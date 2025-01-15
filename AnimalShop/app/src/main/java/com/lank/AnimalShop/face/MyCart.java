package com.lank.AnimalShop.face;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.lank.AnimalShop.R;
import com.lank.AnimalShop.adapter.itemCartAdapter;
import com.lank.AnimalShop.database.cartdb;
import com.lank.AnimalShop.object.Cart;
import com.lank.AnimalShop.object.NumberDialog;


import java.util.ArrayList;

public class MyCart extends AppCompatActivity implements NumberDialog.interfaceListener {
    ArrayList<Cart> li=new ArrayList<>();
    ListView linearLayout;
    itemCartAdapter adapter;
    cartdb cart;
    ImageButton btnhomecart,btnupdatecart;
    TextView tvsumcart;
    int x=0;

    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        anhxa();
        addData();
        setAdapter();
        registerForContextMenu(linearLayout);
    }

    private void anhxa() {
        tvsumcart=findViewById(R.id.tvsumcart);
        btnhomecart=findViewById(R.id.btnhomecart);
        btnhomecart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });
        btnupdatecart=findViewById(R.id.btnupdatecart);
        btnupdatecart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sum=0;
                for(Cart c:li){
                    sum=sum+c.getNumber()*c.getPrice();
                }
                tvsumcart.setText(String.valueOf(sum));
            }
        });
    }

    private void setAdapter() {
        if(li==null){
            return;
        }
        adapter=new itemCartAdapter(li,this,R.layout.item_cart);
        linearLayout.setAdapter(adapter);
    }

    private void addData() {
        linearLayout=findViewById(R.id.linercart);
        cart=new cartdb(this);

        li=cart.getList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.cart_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo i=(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int id= item.getItemId();
        if(id==R.id.btndeletecart){
            Toast.makeText(MyCart.this,String.valueOf(i.position),Toast.LENGTH_SHORT).show();
            cart.delete(new Cart(li.get(i.position).getId(),"ss",0,null,0));
            li.remove(i.position);
            adapter.notifyDataSetChanged();
        } else if (id==R.id.btnupdatecart) {
            x=li.get(i.position).getId();
            Toast.makeText(this,String.valueOf(x),Toast.LENGTH_SHORT).show();
            openDialog();
        }

        return super.onContextItemSelected(item);
    }

    private void openDialog() {
        NumberDialog dialog=new NumberDialog();
        dialog.show(getSupportFragmentManager(),"Nhập số lượng: ??");
    }

    @Override
    public void getNumber(String number) {
        Toast.makeText(this,String.valueOf(x),Toast.LENGTH_SHORT).show();
        cart.update(new Cart(x,null,0,null,Integer.valueOf(number)));
        li.get(id).setNumber(Integer.valueOf(number));
        adapter.notifyDataSetChanged();
        Toast.makeText(this,"Đã sửa thông tin !!",Toast.LENGTH_SHORT).show();
    }
}