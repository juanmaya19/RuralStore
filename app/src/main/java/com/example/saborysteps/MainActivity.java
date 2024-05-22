package com.example.saborysteps;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button registerButton;
    private Button loginButton;
    private RecyclerView recyclerViewProductos;
    private ProductoAdapter productoAdapter;
    private List<Producto> listaProductos;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private DBHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);

        registerButton = findViewById(R.id.registerButton);
        loginButton = findViewById(R.id.loginButton);

        // Configuración del ActionBar y DrawerLayout
        setupActionBarAndDrawerLayout();

        // Configuración de los botones y RecyclerView
        setupButtonsAndRecyclerView();

        registerButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, RegisterActivity.class)));
        loginButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, LoginActivity.class)));
    }

    private void setupActionBarAndDrawerLayout() {
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
    }

    private void setupButtonsAndRecyclerView() {
        Button btnConsultarMetodos = findViewById(R.id.btnConsultarMetodos);
        Button btnPlagasEnfermedades = findViewById(R.id.btnPlagasEnfermedades);
        Button btnForoChat = findViewById(R.id.btnForoChat);
        Button btnComprar = findViewById(R.id.btnComprar);

        recyclerViewProductos = findViewById(R.id.recyclerViewProductos);
        recyclerViewProductos.setLayoutManager(new LinearLayoutManager(this));

        // Cargar lista de productos desde la base de datos
        listaProductos = cargarProductosDesdeDB();

        // Configurar el adaptador
        productoAdapter = new ProductoAdapter(listaProductos);
        recyclerViewProductos.setAdapter(productoAdapter);

        // Configurar los listeners de los botones
        btnComprar.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CompraActivity.class)));
        btnConsultarMetodos.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SiembraActivity.class)));
        btnPlagasEnfermedades.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, PlagasActivity.class)));
        btnForoChat.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, PostActivity.class)));
    }

    private List<Producto> cargarProductosDesdeDB() {
        List<Producto> productos = new ArrayList<>();
        Cursor cursor = dbHelper.getAllProducts();

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_ID));
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_NAME));
                String cantidad = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_QUANTITY));
                double precio = cursor.getDouble(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_PRICE));
                String ubicacion = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_LOCATION));
                byte[] imagen = cursor.getBlob(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_IMAGE));

                Producto producto = new Producto(id, nombre, cantidad, precio, ubicacion, imagen);
                productos.add(producto);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return productos;
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

    private boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_comprar_productos:
                startActivity(new Intent(MainActivity.this, CarActivity.class));
                break;
            case R.id.nav_consultar_metodos:
                startActivity(new Intent(MainActivity.this, SiembraActivity.class));
                break;
            case R.id.nav_plagas_enfermedades:
                startActivity(new Intent(MainActivity.this, PlagasActivity.class));
                break;
            case R.id.nav_foro_chat:
                startActivity(new Intent(MainActivity.this, PostActivity.class));
                break;
            case R.id.nav_register:
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                break;
            case R.id.nav_login:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
        }
        drawerLayout.closeDrawers();
        return true;
    }
}
