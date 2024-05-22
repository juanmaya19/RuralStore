package com.example.saborysteps;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log; // Import the Log class for logging errors
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText lastNameEditText;
    private EditText documentEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button registerButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameEditText = findViewById(R.id.registerNameEditText);
        lastNameEditText = findViewById(R.id.registerLastNameEditText);
        documentEditText = findViewById(R.id.registerDocumentEditText);
        emailEditText = findViewById(R.id.registerEmailEditText);
        passwordEditText = findViewById(R.id.registerPasswordEditText);
        registerButton = findViewById(R.id.registerButton);

        dbHelper = new DatabaseHelper(this);

        registerButton.setOnClickListener(view -> {
            String name = nameEditText.getText().toString();
            String lastName = lastNameEditText.getText().toString();
            String document = documentEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            if (validateFields(name, lastName, document, email, password)) {
                String hashedPassword = hashPassword(password);
                if (hashedPassword != null) {
                    if (addUser(name, lastName, document, email, hashedPassword)) {
                        Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("RegisterActivity", "Hashing password failed"); // Log an error if hashing password fails
                    Toast.makeText(RegisterActivity.this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateFields(String name, String lastName, String document, String email, String password) {
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(document)
                || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!isValidEmail(email)) {
            Toast.makeText(RegisterActivity.this, "Invalid email address", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean isValidEmail(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean addUser(String name, String lastName, String document, String email, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("last_name", lastName);
        values.put("document", document);
        values.put("email", email);
        values.put("password", password);

        try {
            long result = db.insert("users", null, values);
            return result != -1;
        } catch (Exception e) {
            Log.e("RegisterActivity", "Error inserting user: " + e.getMessage()); // Log an error if inserting user fails
            return false;
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            Log.e("RegisterActivity", "Error hashing password: " + e.getMessage()); // Log an error if hashing password fails
            e.printStackTrace();
            return null;
        }
    }
}
