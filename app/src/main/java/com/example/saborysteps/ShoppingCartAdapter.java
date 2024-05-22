package com.example.saborysteps;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.CartViewHolder> {

    private List<CartItem> cartItemList;
    private CartItemListener cartItemListener;

    public ShoppingCartAdapter(List<CartItem> cartItemList, CartItemListener cartItemListener) {
        this.cartItemList = cartItemList;
        this.cartItemListener = cartItemListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartItemList.get(position);
        holder.productNameTextView.setText(item.getName());
        holder.productPriceTextView.setText(String.format("Price: $%.2f", item.getPrice()));
        holder.productQuantityTextView.setText(String.valueOf(item.getQuantity()));
        holder.productImageView.setImageResource(item.getImageResId());

        holder.increaseQuantityButton.setOnClickListener(v -> {
            item.setQuantity(item.getQuantity() + 1);
            holder.productQuantityTextView.setText(String.valueOf(item.getQuantity()));
            cartItemListener.onQuantityChanged();
        });

        holder.decreaseQuantityButton.setOnClickListener(v -> {
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
                holder.productQuantityTextView.setText(String.valueOf(item.getQuantity()));
                cartItemListener.onQuantityChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {

        ImageView productImageView;
        TextView productNameTextView;
        TextView productPriceTextView;
        TextView productQuantityTextView;
        Button increaseQuantityButton;
        Button decreaseQuantityButton;

        CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.productImageView);
            productNameTextView = itemView.findViewById(R.id.productNameTextView);
            productPriceTextView = itemView.findViewById(R.id.productPriceTextView);
            productQuantityTextView = itemView.findViewById(R.id.productQuantityTextView);
            increaseQuantityButton = itemView.findViewById(R.id.increaseQuantityButton);
            decreaseQuantityButton = itemView.findViewById(R.id.decreaseQuantityButton);
        }
    }

    interface CartItemListener {
        void onQuantityChanged();
    }
}

