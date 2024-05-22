package com.example.saborysteps;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.List;

public class MainUserActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TextView welcomeTextView;
    private Button editProfileButton;
    private Button logoutButton;
    private Button btnForoChat;
    private Button btnComprar;
    private Button btnConsultarMetodos;
    private Button btnPlagasEnfermedades;
    private Button btnAgregarProducto;
    private RecyclerView recyclerViewProductos;
    private ProductoAdapter productoAdapter;
    private List<Producto> listaProductos;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        dbHelper = new DBHelper(this);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        editProfileButton = findViewById(R.id.editProfileButton);
        logoutButton = findViewById(R.id.logoutButton);
        btnForoChat = findViewById(R.id.btnForoChat);
        btnComprar = findViewById(R.id.btnComprar);
        btnConsultarMetodos = findViewById(R.id.btnConsultarMetodos);
        btnPlagasEnfermedades = findViewById(R.id.btnPlagasEnfermedades);
        btnAgregarProducto = findViewById(R.id.btnAgregarProducto);
        recyclerViewProductos = findViewById(R.id.recyclerViewProductos);

        setupActionBarAndDrawerLayout();
        setupButtonsAndRecyclerView();

        // Get the username from the intent
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");



        // Set up the navigation view
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        // Set onClick listeners for buttons
        editProfileButton.setOnClickListener(view -> {
            // Implement edit profile functionality here
            Toast.makeText(MainUserActivity.this, "Edit Profile clicked", Toast.LENGTH_SHORT).show();
            // startActivity(new Intent(MainUserActivity.this, EditProfileActivity.class)); // Uncomment if EditProfileActivity exists
        });

        logoutButton.setOnClickListener(view -> {
            // Redirect to login screen
            startActivity(new Intent(MainUserActivity.this, LoginActivity.class));
            finish();
        });

        btnForoChat.setOnClickListener(view -> {
            // Implement functionality for Foro/Chat button
            startActivity(new Intent(MainUserActivity.this, PostActivity.class));
        });

        btnComprar.setOnClickListener(view -> {
            // Implement functionality for Comprar button
            startActivity(new Intent(MainUserActivity.this, CarActivity.class) );
        });

        btnConsultarMetodos.setOnClickListener(view -> {
            // Implement functionality for Consultar Metodos button
            startActivity(new Intent(MainUserActivity.this,SiembraActivity.class));
        });

        btnPlagasEnfermedades.setOnClickListener(view -> {
            // Implement functionality for Plagas y Enfermedades button
            startActivity(new Intent(MainUserActivity.this, PlagasActivity.class));
        });

        btnAgregarProducto.setOnClickListener(view -> {
            startActivityForResult(new Intent(MainUserActivity.this, AddProductActivity.class), 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            loadProductsFromDatabase();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(navigationView)) {
                drawerLayout.closeDrawers();
            } else {
                drawerLayout.openDrawer(navigationView);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_profile:
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                startActivity(new Intent(MainUserActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.nav_comprar_productos:
                startActivity(new Intent(MainUserActivity.this, CompraActivity.class));
                break;
            case R.id.nav_consultar_metodos:
                startActivity(new Intent(MainUserActivity.this, SiembraActivity.class));
                break;
            case R.id.nav_plagas_enfermedades:
                startActivity(new Intent(MainUserActivity.this, PlagasActivity.class));
                break;
            case R.id.nav_foro_chat:
                startActivity(new Intent(MainUserActivity.this, PostActivity.class));
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupActionBarAndDrawerLayout() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
    }

    private void setupButtonsAndRecyclerView() {
        listaProductos = new ArrayList<>();
        productoAdapter = new ProductoAdapter(listaProductos);
        recyclerViewProductos.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewProductos.setAdapter(productoAdapter);

        loadProductsFromDatabase();
    }

    private void loadProductsFromDatabase() {
        listaProductos.clear();
        Cursor cursor = dbHelper.getAllProducts();
        if (cursor.moveToFirst()) {
            do {
                Producto producto = new Producto(
                        cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_QUANTITY)),
                        cursor.getDouble(cursor.getColumnIndex(DBHelper.COLUMN_PRICE)),
                        cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_LOCATION)),
                        cursor.getBlob(cursor.getColumnIndex(DBHelper.COLUMN_IMAGE))
                );
                listaProductos.add(producto);
            } while (cursor.moveToNext());
        }
        cursor.close();
        productoAdapter.setProductos(listaProductos);
    }
}
