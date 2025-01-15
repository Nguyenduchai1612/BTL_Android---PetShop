package com.lank.AnimalShop.face;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;


import com.lank.AnimalShop.R;
import com.lank.AnimalShop.adapter.itemAdapter;
import com.lank.AnimalShop.database.productdb;
import com.lank.AnimalShop.object.Product;
import com.lank.AnimalShop.object.Update_Add_Dialog;


import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity implements Update_Add_Dialog.interfaceListener {
    ArrayList<Product> li=new ArrayList<Product>();
    itemAdapter adapter;
    GridView gridView;
    ImageButton btnAdd, btnhomecart;
    productdb db;
    boolean type=false;
    int pos=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        anhxa();
        addData();
        setAdapter();
    }

    private void setAdapter() {
        if(li==null)
            return;
        adapter=new itemAdapter(li,this,R.layout.item_layout);
        gridView.setAdapter(adapter);

        registerForContextMenu(gridView);
    }

    private void addData() {
        li=db.getList();
    }

    private void anhxa() {
        db=new productdb(this);
        gridView=findViewById(R.id.gvproductadmin);
        btnAdd=findViewById(R.id.btnAddAdmin);
        btnhomecart=findViewById(R.id.btnhomecart);
        btnhomecart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type=true;
                openDialog(0);
            }
        });
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
        pos=i.position;
        if(id==R.id.btndeletecart){
            Toast.makeText(AdminActivity.this,String.valueOf(i.position),Toast.LENGTH_SHORT).show();
            db.delete(new Product(li.get(i.position).getId(),null,0,null));
            li.remove(i.position);
            adapter.notifyDataSetChanged();
        } else if (id==R.id.btnupdatecart) {
            openDialog(li.get(i.position).getId());
        }
        return super.onContextItemSelected(item);
    }

    private void openDialog(int x) {
        Update_Add_Dialog dialog=new Update_Add_Dialog(x);
        dialog.show(getSupportFragmentManager(),"Nhập số lượng: ??");
    }

    @Override
    public void addProduct(Product product) {
        try {
            if (type) {
                db.insert(product);
                li.add(product);
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Đã thêm Pets !!", Toast.LENGTH_SHORT).show();
            } else {
                db.update(product);
                li.set(pos, product);
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Đã sửa thông tin !!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Lỗi thông tin !!", Toast.LENGTH_SHORT).show();
        }
        type = false;
    }
}