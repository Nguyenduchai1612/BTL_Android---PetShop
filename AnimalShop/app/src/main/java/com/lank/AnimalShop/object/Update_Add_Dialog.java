package com.lank.AnimalShop.object;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;


import com.lank.AnimalShop.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Update_Add_Dialog extends AppCompatDialogFragment {
    private EditText editName;
    private EditText editPrice;
    private ImageView igvProduct;
    private Button btnUpload;
    private interfaceListener listener;
    int x=0;

    public Update_Add_Dialog(int x) {
        this.x=x;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.update_add_layout,null);

        editName=view.findViewById(R.id.inputnamedialog);
        editPrice=view.findViewById(R.id.inputpricedialog);
        igvProduct=view.findViewById(R.id.igvproductupload);
        btnUpload=view.findViewById(R.id.btnuploaddialog);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUpload();
            }
        });

        builder.setView(view)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name=editName.getText().toString();
                        int price=Integer.valueOf(editPrice.getText().toString());
                        BitmapDrawable bitmapDrawable= (BitmapDrawable) igvProduct.getDrawable();
                        Bitmap bit=bitmapDrawable.getBitmap();
                        ByteArrayOutputStream byteout=new ByteArrayOutputStream();
                        bit.compress(Bitmap.CompressFormat.PNG,100,byteout);
                        byte[] out= byteout.toByteArray();
                        listener.addProduct(new Product(x,name,price,out));
                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try{
            listener= (interfaceListener) context;
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString()+" must not implement interfaceListener");
        }
    }

    public interface interfaceListener{
        public void addProduct(Product product);
    }

    public void openUpload() {
        Intent i=new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        startActivityForResult(i,101);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==101 && resultCode==RESULT_OK && data!=null){
            Uri uri=data.getData();
            try{
                InputStream ip=getContext().getContentResolver().openInputStream(uri);
                Bitmap bit= BitmapFactory.decodeStream(ip);
                igvProduct.setImageBitmap(bit);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
