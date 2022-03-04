package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tvtimeclone.R;

import java.util.List;

import Models.DetailsModel.Results;

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.ViewHolder> {
    Context context;
    List<Results> detailsList;
    itemClickCallback itemClickCallback;
    addClickCallback clickCallback;


    public DiscoverAdapter(Context context, List<Results> detailsList, itemClickCallback itemClickCallback) {
        this.context = context;
        this.detailsList = detailsList;
        this.itemClickCallback = itemClickCallback;

    }
    public DiscoverAdapter(Context context, List<Results> detailsList, itemClickCallback itemClickCallback, addClickCallback clickCallback) {
        this.context = context;
        this.detailsList = detailsList;
        this.itemClickCallback = itemClickCallback;
        this.clickCallback = clickCallback;

    }

    @NonNull
    @Override
    public DiscoverAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.discover_recycler_view_items, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscoverAdapter.ViewHolder holder, int position) {
       Results details = detailsList.get(position);
        Glide.with(context).load(details.getImage_url())
                .into(holder.moviesImage);
        holder.moviesImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickCallback.onItemClicked(details);
            }
        });

        holder.moviesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickCallback.onAddButtonClicked(details);
            }
        });
    }

    @Override
    public int getItemCount() {
        return detailsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView moviesImage;
        ImageButton moviesButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            moviesImage = itemView.findViewById(R.id.movies_image_view);
            moviesButton = itemView.findViewById(R.id.movies_add_button);

        }


    }
}
