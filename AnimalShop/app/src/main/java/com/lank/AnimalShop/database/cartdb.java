package com.lank.AnimalShop.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteStatement;

import com.lank.AnimalShop.object.Cart;

import java.util.ArrayList;

public class cartdb extends DAL{
    public cartdb(Context context) {
        super(context);
    }

    public ArrayList<Cart> getList(){
        Data=getReadableDatabase();
        Cursor cur=Data.rawQuery("select product.id,name,price,img,sl from Cart inner join product where product.id=Cart.idsp",null);//getData("select * from Product");
        cur.moveToFirst();
        Cart categoryDomain;
        ArrayList<Cart> list=new ArrayList<Cart>();
        while(cur.isAfterLast()==false){
            categoryDomain=new Cart(cur.getInt(0),cur.getString(1),cur.getInt(2),cur.getBlob(3),cur.getInt(4));
            list.add(categoryDomain);
            cur.moveToNext();
        }
        cur.close();
        return list;
    }

    public boolean insert(Cart categoryDomain){
        try{
            //openData();
            Data=getWritableDatabase();
            String sql="Insert into Cart values(1,?,?)";
            SQLiteStatement statement=Data.compileStatement(sql);
            statement.clearBindings();

            statement.bindString(1,String.valueOf(categoryDomain.getId()));
            statement.bindString(2,String.valueOf(categoryDomain.getNumber()));
            statement.executeInsert();
            //closeData();
            return true;
        }
        catch(SQLException sqlException){
            return false;
        }
    }

    public boolean update(Cart categoryDomain){
        try{
            //openData();
            Data=getWritableDatabase();
            String sql="UPDATE Cart set sl=? WHERE idsp=?";
            SQLiteStatement statement=Data.compileStatement(sql);
            statement.clearBindings();

            statement.bindString(1,String.valueOf(categoryDomain.getNumber()));
            statement.bindString(2,String.valueOf(categoryDomain.getId()));
            statement.executeUpdateDelete();
            //closeData();
            return true;
        }
        catch(SQLException sqlException){
            return false;
        }
    }

    public boolean delete(Cart categoryDomain){
        try{
            //openData();
            Data=getWritableDatabase();
            String sql="DELETE FROM Cart WHERE idsp=?";
            SQLiteStatement statement=Data.compileStatement(sql);
            statement.clearBindings();

            statement.bindString(1,String.valueOf(categoryDomain.getId()));
            statement.executeUpdateDelete();
            //closeData();
            return true;
        }
        catch(SQLException sqlException){
            return false;
        }
    }
}
