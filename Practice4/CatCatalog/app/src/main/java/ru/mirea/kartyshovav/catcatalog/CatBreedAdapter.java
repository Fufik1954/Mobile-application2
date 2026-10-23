package ru.mirea.kartyshovav.catcatalog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.kartyshovav.catcatalog.domain.models.CatBreed;

public class CatBreedAdapter extends RecyclerView.Adapter<CatBreedAdapter.CatBreedViewHolder> {

    private List<CatBreed> catBreeds = new ArrayList<>();

    public void setItems(List<CatBreed> breeds) {
        this.catBreeds = breeds;
        notifyDataSetChanged(); // сообщаем RecyclerView, что данные изменились
    }

    @NonNull
    @Override
    public CatBreedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cat_breed, parent, false);
        return new CatBreedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatBreedViewHolder holder, int position) {
        CatBreed breed = catBreeds.get(position);
        holder.bind(breed);
    }

    @Override
    public int getItemCount() {
        return catBreeds.size();
    }

    public static class CatBreedViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageViewCat;
        private final TextView textViewName;
        private final TextView textViewId;
        private final TextView textViewDescription;

        public CatBreedViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewCat = itemView.findViewById(R.id.imageViewCat);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewId = itemView.findViewById(R.id.textViewId);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
        }

        public void bind(CatBreed breed) {
            textViewName.setText(breed.getName());
            textViewId.setText("ID: " + breed.getId());
            textViewDescription.setText(breed.getDescription());

            imageViewCat.setImageResource(R.mipmap.ic_launcher);
        }
    }
}