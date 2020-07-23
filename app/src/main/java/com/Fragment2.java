package com;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.service.autofill.VisibilitySetterAction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.teamwork.MainActivity;
import com.example.teamwork.R;

import static android.app.Activity.RESULT_OK;

public class Fragment2 extends Fragment {

    private static final int Permission_code = 1;

    ImageView imageView;
    Button button;


   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
//        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        final View rootView = inflater.inflate(R.layout.fragment2, parent, false);
        imageView = (ImageView)rootView.findViewById(R.id.imageView);
        button = (Button) rootView.findViewById(R.id.gallary);
        imageView.setVisibility(View.INVISIBLE);

       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               gallary(rootView);
           }
       });
        return rootView;
    }

    public void gallary(View view) {
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
           if (getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
               String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
               requestPermissions(permission,Permission_code);
           } else {
               pickImageFromGallary();
           }
       } else {
           pickImageFromGallary();
       }

   }

   private void pickImageFromGallary(){
       Intent intent = new Intent(Intent.ACTION_PICK);
       intent.setType("image/jpg");
       intent.setType("image/png");
       startActivityForResult(intent,1);
   }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case Permission_code: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallary();
                } else {
                    Toast.makeText(getActivity(), "failed", Toast.LENGTH_SHORT).show();
                }


            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1){
            imageView.setImageURI(data.getData());
            button.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
        }else{
            button.setVisibility(View.VISIBLE);
        }
    }
}
