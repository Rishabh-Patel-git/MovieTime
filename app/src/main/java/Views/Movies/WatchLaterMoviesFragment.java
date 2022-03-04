package Views.Movies;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tvtimeclone.databinding.WatchLaterMoviesFragmentBinding;

import java.util.List;

import Adapter.DiscoverAdapter;
import Adapter.itemClickCallback;
import Models.DetailsModel;
import ViewModels.MoviesFragmentViewModel;

public class WatchLaterMoviesFragment extends Fragment {

    private MoviesFragmentViewModel mViewModel;
    WatchLaterMoviesFragmentBinding binding;
    private DiscoverAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = WatchLaterMoviesFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(MoviesFragmentViewModel.class);

        mViewModel.getWatchLaterMovies().observe(getViewLifecycleOwner(), new Observer<List<DetailsModel.Results>>() {
            @Override
            public void onChanged(List<DetailsModel.Results> results) {
                adapter = new DiscoverAdapter(getContext(), results,
                        new itemClickCallback() {
                            @Override
                            public void onItemClicked(DetailsModel.Results details) {

                            }
                        });
                binding.watchLaterRecyclerView.setAdapter(adapter);
            }
        });

    }
}