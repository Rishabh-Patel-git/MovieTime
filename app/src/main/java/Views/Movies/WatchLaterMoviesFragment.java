package Views.Movies;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tvtimeclone.R;
import com.example.tvtimeclone.databinding.WatchLaterMoviesFragmentBinding;

import java.util.ArrayList;

import Adapter.MoviesAdapter;
import Adapter.itemClickCallback;
import Models.DetailsModel;
import ViewModels.WatchLaterMoviesViewModel;

public class WatchLaterMoviesFragment extends Fragment {

    private WatchLaterMoviesViewModel mViewModel;
    WatchLaterMoviesFragmentBinding binding;
    private MoviesAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = WatchLaterMoviesFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WatchLaterMoviesViewModel.class);
//        adapter = new MoviesAdapter(getContext(), /*todo: */,
//                new itemClickCallback() {
//            @Override
//            public void onItemClicked(DetailsModel.Results details) {
//
//            }
//        });

        binding.watchLaterRecyclerView.setAdapter(adapter);

    }
}