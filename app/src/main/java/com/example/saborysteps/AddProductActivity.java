package com.example.saborysteps;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class AddProductActivity extends Activity {

    private static final int PICK_IMAGE = 100;
    private ImageView imageView;
    private EditText editTextName, editTextQuantity, editTextPrice, editTextLocation;
    private Button buttonAdd, buttonChooseImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        imageView = findViewById(R.id.imageView);
        editTextName = findViewById(R.id.editTextName);
        editTextQuantity = findViewById(R.id.editTextQuantity);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextLocation = findViewById(R.id.editTextLocation);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonChooseImage = findViewById(R.id.buttonChooseImage);

        buttonChooseImage.setOnClickListener(v -> openGallery());

        buttonAdd.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String quantity = editTextQuantity.getText().toString();
            double price = Double.parseDouble(editTextPrice.getText().toString());
            String location = editTextLocation.getText().toString();

            Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] image = stream.toByteArray();

            DBHelper dbHelper = new DBHelper(this);
            dbHelper.addProduct(name, quantity, price, location, image);
            Toast.makeText(this, "Producto agregado", Toast.LENGTH_SHORT).show();

            setResult(RESULT_OK);
            finish();
        });
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageView.setImageURI(data.getData());
        }
    }
}
