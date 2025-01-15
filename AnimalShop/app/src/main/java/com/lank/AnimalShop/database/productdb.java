package com.lank.AnimalShop.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteStatement;

import com.lank.AnimalShop.object.Product;

import java.util.ArrayList;

public class productdb extends DAL{
    public productdb(Context context) {
        super(context);
    }

    public ArrayList<Product> getList(){
        Data=getReadableDatabase();
        Cursor cur=Data.rawQuery("select * from Product",null);//getData("select * from Product");
        cur.moveToFirst();
        Product categoryDomain;
        ArrayList<Product> list=new ArrayList<Product>();
        while(cur.isAfterLast()==false){
            categoryDomain=new Product(cur.getInt(0),cur.getString(1),cur.getInt(2),cur.getBlob(3));
            list.add(categoryDomain);
            cur.moveToNext();
        }
        cur.close();
        return list;
    }

    public boolean insert(Product categoryDomain){
        try{
            //openData();
            Data=getWritableDatabase();
            String sql="Insert into Product values(null,?,?,?)";
            SQLiteStatement statement=Data.compileStatement(sql);
            statement.clearBindings();

            statement.bindString(1,categoryDomain.getName());
            statement.bindString(2,String.valueOf(categoryDomain.getPrice()));
            statement.bindBlob(3,categoryDomain.getImage());
            statement.executeInsert();
            //closeData();
            return true;
        }
        catch(SQLException sqlException){
            return false;
        }
    }

    public boolean update(Product categoryDomain){
        try{
            //openData();
            Data=getWritableDatabase();
            String sql="UPDATE Product set name=?,price=?, img=? WHERE id=?";
            SQLiteStatement statement=Data.compileStatement(sql);
            statement.clearBindings();

            statement.bindString(1,categoryDomain.getName());
            statement.bindString(2,String.valueOf(categoryDomain.getPrice()));
            statement.bindBlob(3,categoryDomain.getImage());
            statement.bindString(4,String.valueOf(categoryDomain.getId()));
            statement.executeUpdateDelete();
            //closeData();
            return true;
        }
        catch(SQLException sqlException){
            return false;
        }
    }

    public boolean delete(Product categoryDomain){
        try{
            //openData();
            Data=getWritableDatabase();
            String sql="DELETE FROM Product WHERE id=?";
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
