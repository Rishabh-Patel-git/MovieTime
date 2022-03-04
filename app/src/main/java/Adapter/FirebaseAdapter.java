package Adapter;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import Models.DetailsModel;


public class FirebaseAdapter extends FirebaseRecyclerAdapter<DetailsModel.Results,FirebaseAdapter.FirebaseViewHolder> {


    public FirebaseAdapter(@NonNull FirebaseRecyclerOptions<DetailsModel.Results> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull FirebaseViewHolder holder, int position, @NonNull DetailsModel.Results model) {

    }

    @NonNull
    @Override
    public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    public class FirebaseViewHolder {
    }


}
