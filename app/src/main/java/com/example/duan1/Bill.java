package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Bill extends AppCompatActivity {
    ImageView imgBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill);

        imgBack = findViewById(R.id.img_back_bill);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Bill.this, CartDrinkOrFood.class);
                startActivity(i);
            }
        });

    }
}
