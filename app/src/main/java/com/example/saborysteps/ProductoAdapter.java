package com.example.saborysteps;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private List<Producto> listaProductos;

    public ProductoAdapter(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = listaProductos.get(position);
        holder.nombreTextView.setText(producto.getNombre());
        holder.cantidadTextView.setText(producto.getCantidad());
        holder.precioTextView.setText(String.valueOf(producto.getPrecio()));
        holder.ubicacionTextView.setText(producto.getUbicacion());
        holder.imagenImageView.setImageBitmap(BitmapFactory.decodeByteArray(producto.getImagen(), 0, producto.getImagen().length));
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTextView, cantidadTextView, precioTextView, ubicacionTextView;
        ImageView imagenImageView;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
            cantidadTextView = itemView.findViewById(R.id.cantidadTextView);
            precioTextView = itemView.findViewById(R.id.precioTextView);
            ubicacionTextView = itemView.findViewById(R.id.ubicacionTextView);
            imagenImageView = itemView.findViewById(R.id.imagenImageView);
        }
    }

    public void setProductos(List<Producto> productos) {
        this.listaProductos = productos;
        notifyDataSetChanged();
    }
}
