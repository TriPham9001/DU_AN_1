package com.example.duan1;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.duan1.Fragment.BillFragment;
import com.example.duan1.Fragment.Favorite.FavoriteFragment;
import com.example.duan1.Fragment.HomeFragment;
import com.example.duan1.Fragment.PersonFragment;
import com.example.duan1.Fragment.ShopFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {
    DatabaseReference mDatabase;
    BottomNavigationView nav_bottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nav_bottom = findViewById(R.id.nav_menu_bottom);
        nav_bottom.setOnNavigationItemSelectedListener(navListener);

        nav_bottom.getMenu().findItem(R.id.nav_home).setChecked(true);

        if(savedInstanceState == null){
            loadFragment(new HomeFragment());
        }
    }

   private BottomNavigationView.OnNavigationItemSelectedListener navListener=
           new BottomNavigationView.OnNavigationItemSelectedListener() {
               @Override
               public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                   Fragment selectedFragment = null;

                   switch (item.getItemId()){
                       case R.id.nav_home:
                           selectedFragment = new HomeFragment();
                           break;
                       case R.id.nav_shopping:
                           selectedFragment = new ShopFragment();
                           break;
                       case R.id.nav_bill:
                           selectedFragment = new BillFragment();
                           break;
                       case R.id.nav_favorite:
                           selectedFragment = new FavoriteFragment();
                           break;
                       case R.id.nav_person:
                           selectedFragment = new PersonFragment();
                           break;
                   }
                   getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                           selectedFragment).commit();
                   return true;
               }
   };

   private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
   }
}