package com.example.duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.duan1.Model.ProductFood;

import java.util.ArrayList;

public class DetailsDrinkOrFood extends AppCompatActivity {
    ArrayList<ProductFood> listFood;
    ProductFood productFood;
    TextView txtNameFood,txtPriceFood,txtDescribe;
    RatingBar ratingBar;
    Button btnAddCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foodordrink_detail);
        txtNameFood = findViewById(R.id.tvFoodName);
        txtPriceFood = findViewById(R.id.tvPrice);
        txtDescribe = findViewById(R.id.tvDescribe);
        btnAddCart = findViewById(R.id.btnAddToCart);
        ratingBar = findViewById(R.id.ratingBar);

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailsDrinkOrFood.this,CartDrinkOrFood.class);
                startActivity(i);
            }
        });
    }
}