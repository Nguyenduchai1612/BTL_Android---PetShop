package com.lank.AnimalShop.object;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.lank.AnimalShop.R;


public class NumberDialog extends AppCompatDialogFragment {
    private EditText editnumber;
    private interfaceListener listen;
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.layout_home_add,null);

        editnumber=view.findViewById(R.id.number_input_home_dialog);

        builder.setView(view).setTitle("Your Number")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String number=editnumber.getText().toString();
                        listen.getNumber(number);
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            listen=(interfaceListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"must implement interfaceListener");
        }
    }

    public interface interfaceListener{
        void getNumber(String number);
    }
}
