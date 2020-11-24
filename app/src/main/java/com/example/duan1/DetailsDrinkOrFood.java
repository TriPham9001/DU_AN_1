package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1.Model.ProductDrink;
import com.example.duan1.Model.ProductFood;

import java.util.ArrayList;

public class DetailsDrinkOrFood extends AppCompatActivity {
    ArrayList<ProductFood> listFood;
    ProductFood productFood;
    public static final String KEY_VALUE = "KEY_VALUE";
    ProductDrink productDrink;
    RatingBar ratingBar;
    Button btnAddCart;
    TextView txtNameFood, txtPriceFood, txtDescribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foodordrink_detail);
        txtNameFood = findViewById(R.id.tvFoodName);
        txtPriceFood = findViewById(R.id.tvPrice);
        txtDescribe = findViewById(R.id.tvDescribe);
        btnAddCart = findViewById(R.id.btnAddToCart);
        ratingBar = findViewById(R.id.ratingBar);
        Boolean isFood = getIntent().getBooleanExtra("Food", true);
        Log.d("KetQua", isFood.toString());
        if (isFood) {
            productFood = (ProductFood) getIntent().getSerializableExtra("KEY_VALUE");

        } else {
            productDrink = (ProductDrink) getIntent().getSerializableExtra("KEY_VALUE");
        }

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailsDrinkOrFood.this, CartDrinkOrFood.class);
                startActivity(i);
            }
        });


    }
}