package com.example.saborysteps;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CarActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCartItems;
    private TextView totalPriceTextView;
    private ShoppingCartAdapter cartAdapter;
    private List<CartItem> cartItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        recyclerViewCartItems = findViewById(R.id.recyclerViewCartItems);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);

        cartItemList = new ArrayList<>();
        loadCartItems();  // Load your cart items from the database or API

        cartAdapter = new ShoppingCartAdapter(cartItemList, new ShoppingCartAdapter.CartItemListener() {
            @Override
            public void onQuantityChanged() {
                updateTotalPrice();
            }
        });

        recyclerViewCartItems.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCartItems.setAdapter(cartAdapter);

        updateTotalPrice();
    }

    private void loadCartItems() {
        // Add some sample items to the cart
        cartItemList.add(new CartItem("Bulto papa", 60000.0, 1, R.drawable.papa));
        cartItemList.add(new CartItem("Bulto Maiz", 90000.0, 2, R.drawable.maiz));
        cartItemList.add(new CartItem("Bulto Zanahoria", 30000.0, 1, R.drawable.zanahoria));
    }

    private void updateTotalPrice() {
        double total = 0.0;
        for (CartItem item : cartItemList) {
            total += item.getPrice() * item.getQuantity();
        }
        totalPriceTextView.setText(String.format("Total: $%.2f", total));
    }
}
