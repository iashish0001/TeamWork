package com.example.teamwork.modules;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.room.Room;

import com.example.teamwork.R;
import com.example.teamwork.data.UserDao;
import com.example.teamwork.data.UserDataBase;
import com.google.android.material.snackbar.Snackbar;

public class signInActivity  extends AppCompatActivity {
    EditText editTextEmail, editTextPassword;
    Button buttonLogin;
    UserDao db;
    UserDataBase dataBase;
    ConstraintLayout constraintLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_signin);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        constraintLayout = (ConstraintLayout) findViewById(R.id.snackbarId);

        dataBase = Room.databaseBuilder(this, UserDataBase.class, "mi-database.db")
                .allowMainThreadQueries()
                .build();

        db = dataBase.getUserDao();




        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                User user = db.getUser(email, password);
                if (user != null) {
                    Intent i = new Intent(signInActivity.this, home.class);
                    i.putExtra("Email", user.getEmail());
                    startActivity(i);
                    finish();

                    Snackbar snackbar = Snackbar
                            .make(constraintLayout, "SignIn Successfull", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }else{
                    Snackbar snackbar = Snackbar
                            .make(constraintLayout, "‘Login Failed. Please\n" +
                                    "check your credentials", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });

    }
}
