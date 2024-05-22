package com.example.saborysteps;
    import android.annotation.SuppressLint;
    import android.os.Bundle;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;



    import java.util.ArrayList;




    public class CompraActivity extends AppCompatActivity {

        private RecyclerView recyclerView;
        private Producto productoAdapter;
        private ArrayList<Producto> listaProductos;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_compra);


        }
    }

