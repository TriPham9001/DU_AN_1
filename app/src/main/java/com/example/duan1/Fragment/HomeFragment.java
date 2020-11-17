package com.example.duan1.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.duan1.Adapter.MenuItemsAdapter;
import com.example.duan1.Adapter.MenuItemsDrinkAdapter;
import com.example.duan1.Adapter.MenuItemsFoodAdapter;
import com.example.duan1.Adapter.PopularAdapter;
import com.example.duan1.Adapter.RecommendedAdapter;
import com.example.duan1.DetailsDrinkOrFood;
import com.example.duan1.HomeAdmin;
import com.example.duan1.LoginActivity;
import com.example.duan1.MainActivity;
import com.example.duan1.Model.Popular;
import com.example.duan1.Model.ProductDrink;
import com.example.duan1.Model.ProductFood;
import com.example.duan1.Model.Recommended;
import com.example.duan1.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment {
    View view;
    RecyclerView recyclerViewDrink, recyclerViewFood;
    CircleImageView image;
    DatabaseReference databaseReference;
    ArrayList<ProductDrink> list;
    ArrayList<ProductFood> listFood;
    SearchView searchView;
    ProductDrink productDrink;
    ProductFood productFood;

    private void setContentView(int activity_main) {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_home,container,false);
        recyclerViewDrink = view.findViewById(R.id.recycler_drink);
        recyclerViewFood = view.findViewById(R.id.recycler_food);

        productDrink = new ProductDrink();
        productFood = new ProductFood();
        list = new ArrayList<>();
        listFood = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        if(list == null && listFood == null){
            databaseReference.setValue(productDrink);
            databaseReference.setValue(productFood);
        }
        else{
            LoadRecyclerDrink();
            LoadReycylerFood();
        }

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
    }

    public void LoadRecyclerDrink(){
        databaseReference.child("Drink").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                productDrink = snapshot.getValue(ProductDrink.class);
                list.add(productDrink);
                recyclerViewDrink.setLayoutManager(new LinearLayoutManager(getContext()));
                MenuItemsDrinkAdapter menuItemsDrinkAdapter = new MenuItemsDrinkAdapter(list, getContext(),HomeFragment.this);
                recyclerViewDrink.setHasFixedSize(true);
                recyclerViewDrink.setAdapter(menuItemsDrinkAdapter);
                menuItemsDrinkAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(), ""+list.size(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void LoadReycylerFood(){
        databaseReference.child("Food").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                productFood = snapshot.getValue(ProductFood.class);
                listFood.add(productFood);
                recyclerViewFood.setLayoutManager(new LinearLayoutManager(getContext()));
                MenuItemsFoodAdapter menuItemsFoodAdapter = new MenuItemsFoodAdapter(listFood, getContext(),HomeFragment.this);
                recyclerViewFood.setHasFixedSize(true);
                recyclerViewFood.setAdapter(menuItemsFoodAdapter);
                menuItemsFoodAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(), ""+listFood.size(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void Itent(int position){
        Intent i = new Intent(getContext(), DetailsDrinkOrFood.class);
        startActivity(i);
    }
}
