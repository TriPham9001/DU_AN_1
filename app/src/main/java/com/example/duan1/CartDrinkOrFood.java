package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CartDrinkOrFood extends AppCompatActivity {
    Button btnOder;
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oder);

        btnOder = findViewById(R.id.btnOder);


        imgBack = findViewById(R.id.img_back_oder);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CartDrinkOrFood.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnOder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CartDrinkOrFood.this, Bill.class);
                startActivity(i);
            }
        });

    }
}