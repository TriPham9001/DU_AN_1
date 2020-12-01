package com.example.duan1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.Model.Cart;
import com.example.duan1.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;
    private List<Cart> list;

    public CartAdapter(List<Cart> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_oder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Cart cart = list.get(position);
        if (list != null) {
            holder.txtName.setText(cart.getName());
            holder.txtPrice.setText(cart.getPrice() + "");
            Picasso.with(context).load(cart.getImages()).into(holder.image);
        }
        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(holder.txtNumber.getText().toString());
                int plus = number + 1;
                holder.txtNumber.setText("" + plus);
                if (plus == 100) {
                    plus = 99;
                    holder.txtNumber.setText("" + plus);
                }
            }
        });
        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(holder.txtNumber.getText().toString());
                int subtract = number - 1;
                holder.txtNumber.setText("" + subtract);
                if (subtract == 0) {
                    subtract = 1;
                    holder.txtNumber.setText("" + subtract);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView image;
        private TextView txtName, txtPrice, txtNumber;
        private AppCompatButton btnPlus, btnMinus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img_food_oder);
            txtName = itemView.findViewById(R.id.name);
            txtPrice = itemView.findViewById(R.id.price);
            txtNumber = itemView.findViewById(R.id.number);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnMinus = itemView.findViewById(R.id.btnMinus);
        }
    }
}
