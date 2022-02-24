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

import Models.DetailsModel;
import Models.DetailsModel.Results;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    Context context;
    List<Results> detailsList;
    itemClickCallback itemClickCallback;

    public MoviesAdapter(Context context, List<Results> movieImageUrlList) {
        this.context = context;
        this.detailsList = movieImageUrlList;

    }
    public MoviesAdapter(Context context, List<Results> movieImageUrlList, itemClickCallback itemClickCallback) {
        this.context = context;
        this.detailsList = movieImageUrlList;
        this.itemClickCallback = itemClickCallback;

    }

    @NonNull
    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.discover_recycler_view_items, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.ViewHolder holder, int position) {
       Results details = detailsList.get(position);
        Glide.with(context).load(details.getImage_url())
                .into(holder.moviesImage);
        holder.moviesImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickCallback.onItemClicked(details);
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
