package com.example.duan1;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.Adapter.MenuItemsDrinkAdapter;
import com.example.duan1.Adapter.MenuItemsFoodAdapter;
import com.example.duan1.Model.ProductDrink;
import com.example.duan1.Model.ProductFood;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeAdmin extends AppCompatActivity {
    private static final int GALLER_ACTION_PICK_CODE = 1;
    Uri imageUri;
    RecyclerView recyclerViewDrink, recyclerViewFood;
    ImageView btnAddDrink,btnAddFood;
    EditText edtID,edtName,edtPrice,edtDescribe;
    CircleImageView image;
    DatabaseReference databaseReference;
    FirebaseStorage dataFirebaseStorage;
    ArrayList<ProductDrink> list;
    ArrayList<ProductFood> listFood;
    SearchView searchView;
    ProductDrink productDrink;
    ProductFood productFood;
    StorageReference storageRef;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_homeadmin);
        recyclerViewDrink = findViewById(R.id.recycler_drink);
        recyclerViewFood = findViewById(R.id.recycler_food);
        btnAddDrink = findViewById(R.id.img_add_drink);
        btnAddFood = findViewById(R.id.img_add_food);

        productDrink = new ProductDrink();
        productFood = new ProductFood();
        list = new ArrayList<>();
        listFood = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        dataFirebaseStorage = FirebaseStorage.getInstance();
        storageRef = dataFirebaseStorage.getReferenceFromUrl("gs://king-food-19cc4.appspot.com");

        if(list == null && listFood == null){
            databaseReference.setValue(productDrink);
            databaseReference.setValue(productFood);
        }
        else{
            LoadRecyclerDrink();
            LoadReycylerFood();
        }
        btnAddDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDescribe();
                checkFood();
                checkID();
                checkPrice();
                InsertBottomSheetDialog(false);
            }
        });
        btnAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDescribe();
                checkFood();
                checkID();
                checkPrice();
                InsertBottomSheetDialog(true);
            }
        });
    }

    public void LoadRecyclerDrink(){
        databaseReference.child("Drink").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                productDrink = snapshot.getValue(ProductDrink.class);
                list.add(productDrink);
                recyclerViewDrink.setLayoutManager(new LinearLayoutManager(HomeAdmin.this));
                MenuItemsDrinkAdapter menuItemsDrinkAdapter = new MenuItemsDrinkAdapter(list, HomeAdmin.this);
                recyclerViewDrink.setHasFixedSize(true);
                recyclerViewDrink.setAdapter(menuItemsDrinkAdapter);
                menuItemsDrinkAdapter.notifyDataSetChanged();
                Toast.makeText(HomeAdmin.this, ""+list.size(), Toast.LENGTH_SHORT).show();
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
                recyclerViewFood.setLayoutManager(new LinearLayoutManager(HomeAdmin.this));
                MenuItemsFoodAdapter menuItemsFoodAdapter = new MenuItemsFoodAdapter(listFood, HomeAdmin.this);
                recyclerViewFood.setHasFixedSize(true);
                recyclerViewFood.setAdapter(menuItemsFoodAdapter);
                menuItemsFoodAdapter.notifyDataSetChanged();
                Toast.makeText(HomeAdmin.this, ""+listFood.size(), Toast.LENGTH_SHORT).show();
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
    @Override
    protected void onStart() {
        super.onStart();
        if(searchView != null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    Search(s);
                    return true;
                }
            });
        }
    }

    public void Search(String str){
        ArrayList<ProductDrink> list = new ArrayList<>();
        for(ProductDrink productDrink : list){
            if(productDrink.getNameDrink().toLowerCase().contains(str.toLowerCase())){
                list.add(productDrink);
            }else if(productDrink.getCodeDrink().toLowerCase().contains(str.toLowerCase())){
                list.add(productDrink);
            }else if(productDrink.getDescribeDrink().toLowerCase().contains(str.toLowerCase())){
                list.add(productDrink);
            }else if(productDrink.getPriceDrink().toString().toLowerCase().contains(str.toLowerCase())){
                list.add(productDrink);
            }
        }
        MenuItemsDrinkAdapter menuItemsDrinkAdapter = new MenuItemsDrinkAdapter(list, HomeAdmin.this);
        recyclerViewDrink.setAdapter(menuItemsDrinkAdapter);
        recyclerViewDrink.setLayoutManager(new LinearLayoutManager(HomeAdmin.this));
        LinearLayoutManager layoutManagerMenuItems = new LinearLayoutManager(HomeAdmin.this);
        recyclerViewDrink.setLayoutManager(layoutManagerMenuItems);
    }

    public void InsertBottomSheetDialog(final Boolean isFood){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.add_food_drink);
        image = bottomSheetDialog.findViewById(R.id.img_add_new_drink);
        edtID = (EditText) bottomSheetDialog.findViewById(R.id.id_fd);
        edtName = (EditText) bottomSheetDialog.findViewById(R.id.name_fd);
        edtPrice = (EditText) bottomSheetDialog.findViewById(R.id.ed_price_fd);
        edtDescribe = (EditText) bottomSheetDialog.findViewById(R.id.ed_describe_fd);
        Button btnSave = (Button) bottomSheetDialog.findViewById(R.id.btn_save_fd);
        Button btnCancel = (Button) bottomSheetDialog.findViewById(R.id.btn_cancel_fd);

        edtID.setHint(isFood ? "Mã món ăn" : "Mã thức uống");
        edtName.setHint(isFood ? "Tên món ăn" : "Tên thức uống");
        edtDescribe.setHint(isFood ? "Mô tả món ăn" : "Mô tả thức uống");
        edtPrice.setHint(isFood ? "Giá tiền món ăn" : "Giá tiền thức uống");

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent , 1);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFood == false){
                    productDrink = new ProductDrink();
                    productDrink.setNameDrink(edtName.getText().toString());
                    productDrink.setPriceDrink(Double.parseDouble(edtPrice.getText().toString()));
                    productDrink.setDescribeDrink(edtDescribe.getText().toString());
                    productDrink.setCodeDrink(edtID.getText().toString());
                    databaseReference.child("Drink").push().setValue(productDrink);
                    bottomSheetDialog.dismiss();
                }else{
                    productFood = new ProductFood();
                    productFood.setNameFood(edtName.getText().toString());
                    productFood.setPriceFood(Double.parseDouble(edtPrice.getText().toString()));
                    productFood.setDescribeFood(edtDescribe.getText().toString());
                    productFood.setCodeFood(edtID.getText().toString());
                    databaseReference.child("Food").push().setValue(productFood);
                    bottomSheetDialog.dismiss();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.show();
    }
    private boolean checkFood(){
        String foodCheck = edtName.getText().toString().trim();

        if(foodCheck.isEmpty()){
            edtName.setError("Không để trống tên");
            return false;
        }
        else {
            edtName.setError(null);
            return true;
        }
    }
    private boolean checkPrice(){
        String priceCheck = edtPrice.getText().toString().trim();

        if(priceCheck.isEmpty()){
            edtPrice.setError("Không để trống giá tiền");
            return false;
        }
        else {
            edtPrice.setError(null);
            return true;
        }
    }
    private boolean checkDescribe(){
        String describeCheck = edtDescribe.getText().toString().trim();

        if(describeCheck.isEmpty()){
            edtDescribe.setError("Không để trống nội dung");
            return false;
        }
        else {
            edtDescribe.setError(null);
            return true;
        }
    }
    private boolean checkID(){
        String idCheck = edtID.getText().toString().trim();

        if(idCheck.isEmpty()){
            edtID.setError("Không để trống ID");
            return false;
        }
        else {
            edtID.setError(null);
            return true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null){
            imageUri = data.getData();
            image.setImageURI(imageUri);
        }
    }

}
