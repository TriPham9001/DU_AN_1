package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1.Adapter.MenuItemsDrinkAdapter;
import com.example.duan1.Adapter.MenuItemsFoodAdapter;
import com.example.duan1.DAO.FavoriteDAO;
import com.example.duan1.Model.Favorite;
import com.example.duan1.Model.ProductDrink;
import com.example.duan1.Model.ProductFood;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailsDrink extends AppCompatActivity {
    ArrayList<ProductFood> listFood;
    ArrayList<ProductDrink> listDrink;
    ArrayList<Favorite> listFavorite;
    ProductDrink productDrink;
    ProductFood productFood;
    TextView txtName, txtPrice, txtDescribe;
    ImageView image, imgLike;
    MenuItemsFoodAdapter menuItemsFoodAdapter;
    MenuItemsDrinkAdapter menuItemsDrinkAdapter;
    RatingBar ratingBar;
    Button btnAddCart;

    //
    String nameDrink, describeDrink, imagesDrink;
    Double priceDrink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foodordrink_detail);
        txtName = findViewById(R.id.tvFoodName);
        txtPrice = findViewById(R.id.tvPrice);
        txtDescribe = findViewById(R.id.tvDescribe);
        image = findViewById(R.id.imgFoodImage);
        btnAddCart = findViewById(R.id.btnAddToCart);
        ratingBar = findViewById(R.id.ratingBar);
        imgLike = findViewById(R.id.imgLike);

        productFood = new ProductFood();
        productDrink = new ProductDrink();
        listFood = new ArrayList<>();
        listDrink = new ArrayList<>();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nameDrink = extras.getString("nameDrink");
            describeDrink = extras.getString("describeDrink");
            imagesDrink = extras.getString("imagesDrink");
            priceDrink = extras.getDouble("priceDrink");
        }

        txtName.setText(nameDrink);
        txtPrice.setText(String.valueOf(priceDrink));
        txtDescribe.setText(describeDrink);
        Picasso.with(getApplicationContext()).load(imagesDrink).into(image);

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsDrink.this, CartDrinkOrFood.class);
                intent.putExtra("name", nameDrink);
                intent.putExtra("price", priceDrink);
                intent.putExtra("images", imagesDrink);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


        //imgLike
        imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extrass = getIntent().getExtras();
                nameDrink = extrass.getString("nameDrink");
                describeDrink = extrass.getString("describeDrink");
                imagesDrink = extrass.getString("imagesDrink");
                priceDrink = extrass.getDouble("priceDrink");

                Favorite favorite = new Favorite(nameDrink, priceDrink, imagesDrink, describeDrink);
                FavoriteDAO favoriteDAO = new FavoriteDAO(DetailsDrink.this);
                favoriteDAO.Insert(favorite);
            }
        });
    }
}