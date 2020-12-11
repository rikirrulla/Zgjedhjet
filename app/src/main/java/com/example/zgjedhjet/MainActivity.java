package com.example.zgjedhjet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ConstraintLayout constraintLayout;
    ImageView logout;
    AlertDialog.Builder builder;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logout = findViewById(R.id.idLogout);
        builder = new AlertDialog.Builder(this);

/*
        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }*/

        bottomNavigationView = findViewById(R.id.idNavigationBarView);
        constraintLayout = findViewById(R.id.layoutHeader);
        view = findViewById(R.id.viewDivider);



        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.viewDivider,new HomeFragment()).commit();
        }


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.idKryefaqjaMenu:
                        fragment = new HomeFragment();
                        Drawable drawable1 = ResourcesCompat.getDrawable(getResources(), R.drawable.header_backgroud, null);
                        ViewCompat.setBackground(constraintLayout, drawable1);
                        break;
                    case R.id.idZgjedhjetMenu:
                        fragment = new PollingFragment();
                        Drawable drawable2 = ResourcesCompat.getDrawable(getResources(), R.drawable.bgapps, null);
                        ViewCompat.setBackground(constraintLayout, drawable2);
                        break;
                    case R.id.idNjoftimetaMenu:
                        fragment = new NotifitacionFragment();
                        Drawable drawable3 = ResourcesCompat.getDrawable(getResources(), R.drawable.bgapps, null);
                        ViewCompat.setBackground(constraintLayout, drawable3);
                        break;
                    case R.id.idLlogariaMenu:
                        fragment = new ProfileFragment();
                        Drawable drawable4 = ResourcesCompat.getDrawable(getResources(), R.drawable.bgapps, null);
                        ViewCompat.setBackground(constraintLayout, drawable4);
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.viewDivider,fragment).commit();

                return true;
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutM();
            }
        });


    }

    private void logoutM() {

        builder.setMessage("Deshironi te dilni nga aplikacioni?")
                .setCancelable(false)
                .setPositiveButton("Po", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        Intent i = new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(i);
                    }
                })
                .setNegativeButton("Jo", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.setTitle("Dilni");
        alert.show();


    }



}
