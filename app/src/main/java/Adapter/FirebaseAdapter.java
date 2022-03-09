package Adapter;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tvtimeclone.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

import Models.DetailsModel;


public class FirebaseAdapter extends FirebaseRecyclerAdapter<DetailsModel.Results, FirebaseAdapter.FirebaseViewHolder> {

    Context context;
    itemClickCallback itemClickCallback;
    ProgressBar progressBar;
    DatabaseReference mbase;


    public FirebaseAdapter(@NonNull FirebaseRecyclerOptions<DetailsModel.Results> options,
                           Context context,
                           itemClickCallback itemClickCallback, DatabaseReference mbase, ProgressBar progressBar) {
        super(options);
        this.context = context;
        this.itemClickCallback = itemClickCallback;
        this.progressBar = progressBar;
        this.mbase = mbase;
    }

    @Override
    public void onDataChanged() {
        super.onDataChanged();
//        if (getItemCount() == 0) {
//           // Toast.makeText(context, "add some movies ", Toast.LENGTH_SHORT).show();
//        }
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onBindViewHolder(@NonNull FirebaseViewHolder holder, int position, @NonNull DetailsModel.Results model) {
        holder.moviesButton.setImageResource(R.drawable.ic_baseline_remove_24);
        Glide.with(context).load(model.getImage_url())
                .into(holder.moviesImage);
        holder.moviesImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickCallback.onItemClicked(model);
            }
        });

        holder.moviesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mbase.child(model.getImdb_id()).removeValue();
                Toast.makeText(context, model.getTitle() + " removed", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @NonNull
    @Override
    public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.discover_recycler_view_items, parent, false);
        return new FirebaseViewHolder(v);
    }

    public class FirebaseViewHolder extends RecyclerView.ViewHolder {
        ImageView moviesImage;
        ImageButton moviesButton;

        public FirebaseViewHolder(@NonNull View itemView) {
            super(itemView);
            moviesImage = itemView.findViewById(R.id.movies_image_view);
            moviesButton = itemView.findViewById(R.id.movies_add_button);
        }
    }


}
