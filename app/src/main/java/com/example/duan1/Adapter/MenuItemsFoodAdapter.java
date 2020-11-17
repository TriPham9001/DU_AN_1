package com.example.duan1.Adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.duan1.Model.ProductFood;
import com.example.duan1.R;

import java.util.List;

public class MenuItemsFoodAdapter extends RecyclerView.Adapter<MenuItemsFoodAdapter.MenuItemsViewHolder> {

    List<ProductFood> list;
    Context context;
    HomeFragment homeFragment;

    public MenuItemsFoodAdapter(List<ProductFood> list, Context context, HomeFragment homeFragment) {
        this.list = list;
        this.context = context;
        this.homeFragment = homeFragment;
    }

    public MenuItemsFoodAdapter(List<ProductFood>list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemsViewHolder holder, final int position) {
        final ProductFood productFood = list.get(position);
        if(list != null){
            holder.txtNameFood.setText(productFood.getNameFood());
            holder.txtIDFood.setText(productFood.getCodeFood()+"");
            holder.txtPrice.setText(productFood.getPriceFood()+" $");
            holder.txtDesbribe.setText(productFood.getDescribeFood());
            Glide.with(context).load(list.get(position).getImagesFood()).into(holder.imagesFood);

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
    public MenuItemsFoodAdapter.MenuItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_food,parent,false);
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

        private ImageView imagesFood;
        private TextView txtNameFood,txtIDFood,txtPrice,txtDesbribe;

        public MenuItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            imagesFood = itemView.findViewById(R.id.imageFood);
            txtNameFood = itemView.findViewById(R.id.nameFood);
            txtIDFood = itemView.findViewById(R.id.IDFood);
            txtPrice = itemView.findViewById(R.id.priceFood);
            txtDesbribe = itemView.findViewById(R.id.DesbribeFood);
        }
    }
}



