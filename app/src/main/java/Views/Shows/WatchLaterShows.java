package Views.Shows;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tvtimeclone.databinding.WatchLaterShowsFragmentBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Adapter.EmptyCallback;
import Adapter.FirebaseAdapter;
import Adapter.itemClickCallback;
import Models.DetailsModel;
import utils.SerializationUtils;

public class WatchLaterShows extends Fragment {

    private WatchLaterShowsFragmentBinding binding;
    private FirebaseAdapter adapter;
    private DatabaseReference mbase;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = WatchLaterShowsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController nav = Navigation.findNavController(getParentFragment().getView());


        mbase = FirebaseDatabase.getInstance().
                getReference().child("Users").
                child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Watch Later Shows");

        FirebaseRecyclerOptions<DetailsModel.Results> options
                = new FirebaseRecyclerOptions.Builder<DetailsModel.Results>()
                .setQuery(mbase, DetailsModel.Results.class)
                .build();

        adapter = new FirebaseAdapter(options, getContext(), new itemClickCallback() {
            @Override
            public void onItemClicked(DetailsModel.Results details) {
                details.isShow = true;
                ShowsFragmentDirections.ActionShowsFragmentToDetailsFragment action
                        = ShowsFragmentDirections.actionShowsFragmentToDetailsFragment(SerializationUtils.convertToByteString(details));
                nav.navigate(action);
            }
        }, mbase, new EmptyCallback() {
            @Override
            public void onEmptyCall(int itemCount) {
                binding.progressBar.setVisibility(View.GONE);
                binding.noItemImage.setVisibility(itemCount == 0 ? View.VISIBLE : View.GONE);
                binding.noItemText.setVisibility(itemCount == 0 ? View.VISIBLE : View.GONE);
            }
        });


        binding.watchLShowsRecycler.setVisibility(View.VISIBLE);
        binding.watchLShowsRecycler.setAdapter(adapter);
        adapter.startListening();

    }
}