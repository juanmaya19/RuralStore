package com.example.saborysteps;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;
import java.util.List;

public class PlagasActivity extends AppCompatActivity {

    private List<String> categorias;
    private List<PlagaEnfermedad> plagaEnfermedadList;
    private ArrayAdapter<String> categoriasAdapter;
    private PlagaEnfermedadAdapter plagaEnfermedadAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plagas);

        // Inicializar listas de datos
        categorias = new ArrayList<>();
        plagaEnfermedadList = new ArrayList<>();

        // Agregar datos de ejemplo (reemplaza con tus propios datos)
        categorias.add("Plagas de cultivos");
        categorias.add("Enfermedades en animales");

        plagaEnfermedadList.add(new PlagaEnfermedad("Pulgón", "Pequeños insectos chupadores que se alimentan de la savia de las plantas, causando deformaciones y debilitamiento", R.drawable.pulgon, "www.koppert.com.co/plagas-en-plantas/pulgones/"));
        plagaEnfermedadList.add(new PlagaEnfermedad("Oruga cortadora", "Orugas que cortan las hojas y tallos de las plantas, causando daños significativos en cultivos", R.drawable.oruga, "www.koppert.com.co/plagas-en-plantas/pulgones/"));
        plagaEnfermedadList.add(new PlagaEnfermedad("Ácaros", "Pequeños artrópodos que se alimentan de las células de las plantas, provocando amarillamiento y deformación en las hojas", R.drawable.acaro, "www.koppert.com.co/plagas-en-plantas/pulgones/"));
        plagaEnfermedadList.add(new PlagaEnfermedad("Mosca blanca", "Insectos voladores que se alimentan de la savia de las plantas y transmiten enfermedades virales", R.drawable.moscab, "www.koppert.com.co/plagas-en-plantas/pulgones/"));
        plagaEnfermedadList.add(new PlagaEnfermedad("Gusano cogollero", "Larvas que se alimentan de las hojas y brotes jóvenes de las plantas, afectando su crecimiento y desarrollo", R.drawable.gusanoc, "www.koppert.com.co/plagas-en-plantas/pulgones/"));
        plagaEnfermedadList.add(new PlagaEnfermedad("Trips", "Insectos diminutos que perforan las células de las plantas para alimentarse, causando manchas y deformidades en las hojas", R.drawable.trips, "www.koppert.com.co/plagas-en-plantas/pulgones/"));
        plagaEnfermedadList.add(new PlagaEnfermedad("Escarabajo de la patata", "Insecto que se alimenta de las hojas y tubérculos de las plantas de patata, ocasionando daños graves en los cultivos", R.drawable.escarabajo, "www.koppert.com.co/plagas-en-plantas/pulgones/"));
        plagaEnfermedadList.add(new PlagaEnfermedad("Hormigas cortadoras", "Hormigas que cortan trozos de hojas para llevar a sus hormigueros, afectando la salud de las plantas", R.drawable.hormigas, "www.koppert.com.co/plagas-en-plantas/pulgones/"));
        plagaEnfermedadList.add(new PlagaEnfermedad("Mildiu", "Enfermedad fúngica que provoca manchas blancas en las hojas y afecta el crecimiento de las plantas", R.drawable.mildiu, "www.koppert.com.co/plagas-en-plantas/pulgones/"));
        plagaEnfermedadList.add(new PlagaEnfermedad("Royas", "Enfermedades fúngicas que causan manchas anaranjadas o amarillas en las hojas, debilitando la planta", R.drawable.roya, "www.koppert.com.co/plagas-en-plantas/pulgones/"));
        plagaEnfermedadList.add(new PlagaEnfermedad("Pulgón negro", "Variante del pulgón común que afecta especialmente a plantas como rosales, provocando deformaciones en brotes y hojas", R.drawable.pulgonn, "www.koppert.com.co/plagas-en-plantas/pulgones/"));
        plagaEnfermedadList.add(new PlagaEnfermedad("Tuta absoluta", "Polilla que ataca tomates y otros cultivos de la familia de las solanáceas, causando daños en frutos y hojas", R.drawable.tuta, "www.koppert.com.co/plagas-en-plantas/pulgones/"));
        plagaEnfermedadList.add(new PlagaEnfermedad("Cochinilla", "Insecto que se adhiere a tallos y hojas, succionando la savia y dejando una secreción pegajosa que favorece el desarrollo de hongos", R.drawable.cochinilla, "www.koppert.com.co/plagas-en-plantas/pulgones/"));
        plagaEnfermedadList.add(new PlagaEnfermedad("Moscas de la fruta", "Insectos que ponen huevos en frutas maduras, provocando la descomposición de las mismas y pérdidas en la producción", R.drawable.moscaf, "www.koppert.com.co/plagas-en-plantas/pulgones/"));
        // Agrega más datos según sea necesario

        // Configurar el adaptador para la lista de categorías
        ListView categoryListView = findViewById(R.id.categoryList);
        categoriasAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categorias);
        categoryListView.setAdapter(categoriasAdapter);

        // Configurar el adaptador para las tarjetas informativas
        RecyclerView cardsRecyclerView = findViewById(R.id.cardsRecyclerView);
        plagaEnfermedadAdapter = new PlagaEnfermedadAdapter(plagaEnfermedadList);
        cardsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cardsRecyclerView.setAdapter(plagaEnfermedadAdapter);

        // Configurar la barra de búsqueda
        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Lógica para buscar y filtrar datos según la búsqueda
                buscarPlagasEnfermedades(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Lógica para buscar y filtrar datos en tiempo real mientras se escribe
                buscarPlagasEnfermedades(newText);
                return true;
            }
        });
    }

    // Método para buscar y filtrar plagas/enfermedades según el término de búsqueda
    private void buscarPlagasEnfermedades(String query) {
        List<PlagaEnfermedad> resultado = new ArrayList<>();
        for (PlagaEnfermedad pe : plagaEnfermedadList) {
            if (pe.getNombre().toLowerCase().contains(query.toLowerCase())) {
                resultado.add(pe);
            }
        }
        plagaEnfermedadAdapter.filtrarLista(resultado);
    }

    // Clase modelo para representar una plaga/enfermedad
    private static class PlagaEnfermedad {
        private String nombre;
        private String descripcion;
        private int imagen;
        private String enlace;

        public PlagaEnfermedad(String nombre, String descripcion, int imagen, String enlace) {
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.imagen = imagen;
            this.enlace = enlace;
        }

        public String getNombre() {
            return nombre;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public int getImagen() {
            return imagen;
        }
        public String getEnlace() {
            return enlace;
        }
    }

    // Adaptador personalizado para las tarjetas informativas
    private static class PlagaEnfermedadAdapter extends RecyclerView.Adapter<PlagaEnfermedadAdapter.ViewHolder> {
        private List<PlagaEnfermedad> listaPlagaEnfermedad;

        public PlagaEnfermedadAdapter(List<PlagaEnfermedad> listaPlagaEnfermedad) {
            this.listaPlagaEnfermedad = listaPlagaEnfermedad;
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView nombreTextView;
            private final TextView descripcionTextView;
            private final ImageView imagenImageView;

            public ViewHolder(View view) {
                super(view);
                nombreTextView = view.findViewById(R.id.nombrePlaga);
                descripcionTextView = view.findViewById(R.id.descripcionPlaga);
                imagenImageView = view.findViewById(R.id.imagenPlaga);
            }

            public TextView getNombreTextView() {
                return nombreTextView;
            }

            public TextView getDescripcionTextView() {
                return descripcionTextView;
            }

            public ImageView getImagenImageView() {
                return imagenImageView;
            }
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_plaga_enfermedad, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            PlagaEnfermedad plagaEnfermedad = listaPlagaEnfermedad.get(position);
            holder.getNombreTextView().setText(plagaEnfermedad.getNombre());
            holder.getDescripcionTextView().setText(plagaEnfermedad.getDescripcion());
            holder.getImagenImageView().setImageResource(plagaEnfermedad.getImagen());

            // Manejar clics en las tarjetas (opcional)
            holder.itemView.setOnClickListener(v -> {
                Toast.makeText(v.getContext(), "Has seleccionado: " + plagaEnfermedad.getNombre(), Toast.LENGTH_SHORT).show();
                // Aquí puedes agregar la lógica para mostrar más detalles de la plaga/enfermedad seleccionada
            });
        }

        @Override
        public int getItemCount() {
            return listaPlagaEnfermedad.size();
        }

        // Método para filtrar la lista según el término de búsqueda
        public void filtrarLista(List<PlagaEnfermedad> listaFiltrada) {
            listaPlagaEnfermedad = listaFiltrada;
            notifyDataSetChanged();
        }
    }
}

