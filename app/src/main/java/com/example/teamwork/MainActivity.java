package com.example.teamwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.teamwork.modules.RegisterActivity;
import com.example.teamwork.modules.home;
import com.example.teamwork.modules.signInActivity;

public class MainActivity extends AppCompatActivity {

    Button signinButton;
    Button signupButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signinButton = findViewById(R.id.signinButton);
        signupButton = findViewById(R.id.signupButton);

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signIn = new Intent(MainActivity.this, signInActivity.class);
                MainActivity.this.startActivity(signIn);

            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUp = new Intent(MainActivity.this , RegisterActivity.class);
                //Intent test = new Intent(MainActivity.this, home.class);
                MainActivity.this.startActivity(signUp);
            }
        });
    }
}