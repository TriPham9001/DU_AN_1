package com.example.duan1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan1.DetailsDrinkOrFood;
import com.example.duan1.Fragment.HomeFragment;
import com.example.duan1.MainActivity;
import com.example.duan1.Model.ProductDrink;
import com.example.duan1.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MenuItemsDrinkAdapter extends RecyclerView.Adapter<MenuItemsDrinkAdapter.MenuItemsViewHolder> {

    List<ProductDrink> list;
    Context context;
    HomeFragment homeFragment;

    public MenuItemsDrinkAdapter(List<ProductDrink> list, Context context, HomeFragment homeFragment) {
        this.list = list;
        this.context = context;
        this.homeFragment = homeFragment;
    }

    public MenuItemsDrinkAdapter(List<ProductDrink> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemsViewHolder holder, final int position) {
        final ProductDrink productDrink = list.get(position);
        if(list != null){
            holder.txtNameDrink.setText(productDrink.getNameDrink());
            holder.txtIDDrink.setText(productDrink.getCodeDrink()+"");
            holder.txtPrice.setText(productDrink.getPriceDrink()+" $");
            holder.txtDesbribe.setText(productDrink.getDescribeDrink());
            Glide.with(context).load(list.get(position).getImagesDrink()).into(holder.imagesDrink);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    homeFragment.Itent(position);
                }
            });

        }
    }

    @NonNull
    @Override
    public MenuItemsDrinkAdapter.MenuItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_drink,parent,false);
        return new MenuItemsViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }else{
            return 0;
        }
    }

    public class MenuItemsViewHolder extends RecyclerView.ViewHolder{

        private ImageView imagesDrink;
        private TextView txtNameDrink,txtIDDrink,txtPrice,txtDesbribe;

        public MenuItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            imagesDrink = itemView.findViewById(R.id.imageDrink);
            txtNameDrink = itemView.findViewById(R.id.nameDrink);
            txtIDDrink = itemView.findViewById(R.id.IDDrink);
            txtPrice = itemView.findViewById(R.id.priceDrink);
            txtDesbribe = itemView.findViewById(R.id.DesbribeDrink);
        }
    }
}



