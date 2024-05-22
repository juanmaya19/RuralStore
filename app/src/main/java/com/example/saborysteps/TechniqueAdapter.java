package com.example.saborysteps;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TechniqueAdapter extends RecyclerView.Adapter<TechniqueAdapter.TechniqueViewHolder> {

    private List<Technique> techniques;
    private Context context;

    public TechniqueAdapter(Context context, List<Technique> techniques) {
        this.context = context;
        this.techniques = techniques;
    }

    @NonNull
    @Override
    public TechniqueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_technique, parent, false);
        return new TechniqueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TechniqueViewHolder holder, int position) {
        Technique technique = techniques.get(position);
        holder.titleTextView.setText(technique.getTitle());
        holder.descriptionTextView.setText(technique.getDescription());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(technique.getUrl()));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return techniques.size();
    }

    public static class TechniqueViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;

        public TechniqueViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.techniqueTitle);
            descriptionTextView = itemView.findViewById(R.id.techniqueDescription);
        }
    }
}