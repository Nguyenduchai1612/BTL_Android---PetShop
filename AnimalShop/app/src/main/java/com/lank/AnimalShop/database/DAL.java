package com.lank.AnimalShop.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DAL extends SQLiteOpenHelper {
    private static final String Dataname="sqlite.db";
    private static final String Datapath="/databases/";
    protected SQLiteDatabase Data=null;
    private Context context;
    public DAL(Context context) {
        super(context, Dataname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Cursor getData(String sql){
        //Data=getReadableDatabase();
        return Data.rawQuery(sql,null);
    }

    public void queryData(String sql){
        //Data=getWritableDatabase();
        Data.execSQL(sql);
    }

    public boolean copyData(Context context){
        try{
            InputStream input=context.getAssets().open(Dataname);
            String output=context.getApplicationInfo().dataDir+Datapath+Dataname;
            OutputStream outputStream=new FileOutputStream(output);
            byte[]buff=new byte[1024];
            int length=0;
            while((length=input.read(buff))>0){
                outputStream.write(buff,0,length);
            }
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkData(Context context){
        File f = new File(context.getApplicationInfo().dataDir+Datapath+Dataname);

        if(!f.exists()){
            getReadableDatabase();
            if(copyData(context)){
                return true;
            }
            return false;
        }
        return true;
    }
}
