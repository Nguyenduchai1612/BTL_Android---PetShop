package com.lank.AnimalShop.face;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.lank.AnimalShop.R;
import com.lank.AnimalShop.adapter.itemAdapter;
import com.lank.AnimalShop.database.cartdb;
import com.lank.AnimalShop.database.productdb;
import com.lank.AnimalShop.object.Cart;
import com.lank.AnimalShop.object.NumberDialog;
import com.lank.AnimalShop.object.Product;


import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements NumberDialog.interfaceListener{

    ArrayList<Product> li=new ArrayList<Product>();
    itemAdapter adapter;
    GridView gridView;
    BottomNavigationView bottomNavigationView;
    productdb db;
    cartdb cart;
    Cart c=new Cart(0,null,0,null,0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        gridView = findViewById(R.id.gvitemhome);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        addData();
        setadapter();
        functionBottom();
    }

    private void functionBottom() {
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.cart_nav) {
                addData();
                setadapter();
                startActivity(new Intent(HomeActivity.this, MyCart.class));
            }
            if (id == R.id.profile_nav)
                startActivity(new Intent(HomeActivity.this, AdminActivity.class));
            if (id == R.id.exit_nav) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
            return true;
        });
    }

    private void setadapter() {
        adapter = new itemAdapter(li, this, R.layout.item_layout);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            c.setId(li.get(position).getId());
            openDialog();
        });
    }

    private void addData() {
        db = new productdb(this);
        cart = new cartdb(this);
        li = db.getList();
        if (li == null)
            return;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo i = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getItemId() == R.id.addcart) {
            c.setId(li.get(i.position).getId());
            openDialog();
        }
        return super.onContextItemSelected(item);
    }

    private void openDialog() {
        NumberDialog dialog = new NumberDialog();
        dialog.show(getSupportFragmentManager(), "Nhập số lượng: ??");
    }

    @Override
    public void getNumber(String number) {
        c.setNumber(Integer.valueOf(number));
        cart.insert(c);
        Toast.makeText(this, "Thêm vào giỏ hàng thành công!", Toast.LENGTH_SHORT).show();
    }
}