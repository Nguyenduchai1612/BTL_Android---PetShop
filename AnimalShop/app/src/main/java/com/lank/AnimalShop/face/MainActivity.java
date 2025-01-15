package com.lank.AnimalShop.face;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.lank.AnimalShop.R;
import com.lank.AnimalShop.database.DAL;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText name=findViewById(R.id.edittextlogin);
        EditText pass=findViewById(R.id.edittextlogin2);
        Button login=findViewById(R.id.btnloggin);

        loadDatabase();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name2=name.getText().toString();
                String pass2=pass.getText().toString();
                if(name2.equals("admin") && pass2.equals("admin")){
                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                }
                else{
                    Toast.makeText(MainActivity.this,"Đăng Nhập Thất Bại !",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void loadDatabase() {
        DAL userdatabase=new DAL(this);
        if(userdatabase.checkData(this)){
            Toast.makeText(this,"Pet House Xin Chào !!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Không tìm thấy dữ liệu",Toast.LENGTH_SHORT).show();
            return;
        }
    }
}