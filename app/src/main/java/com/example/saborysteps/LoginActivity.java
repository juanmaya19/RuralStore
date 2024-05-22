package com.example.saborysteps;

// LoginActivity.java

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEmailEditText;
    private EditText loginPasswordEditText;
    private Button loginButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializar vistas
        loginEmailEditText = findViewById(R.id.loginEmailEditText);
        loginPasswordEditText = findViewById(R.id.loginPasswordEditText);
        loginButton = findViewById(R.id.loginButton);

        // Inicializar DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Configurar el listener del botón de inicio de sesión
        loginButton.setOnClickListener(view -> {
            String email = loginEmailEditText.getText().toString().trim();
            String password = loginPasswordEditText.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                if (dbHelper.validateUser(email, password, LoginActivity.this)) {
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainUserActivity.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Log.e("LoginActivity", "Error en inicio de sesión: " + e.getMessage(), e);
                Toast.makeText(LoginActivity.this, "An error occurred. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
