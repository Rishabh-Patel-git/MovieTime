package Views.Shows;

import androidx.lifecycle.Observer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tvtimeclone.databinding.WatchedShowsFragmentBinding;

import java.util.List;

import Adapter.DiscoverAdapter;
import Adapter.itemClickCallback;
import Models.DetailsModel;
import Models.DetailsModel.Results;
import ViewModels.ShowsFragmentViewModel;

public class WatchedShows extends Fragment {

   private ShowsFragmentViewModel viewModel;
    private DiscoverAdapter adapter;
    private WatchedShowsFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = WatchedShowsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(ShowsFragmentViewModel.class);

        viewModel.getWatchedShows().observe(getViewLifecycleOwner(), new Observer<List<DetailsModel.Results>>() {
            @Override
            public void onChanged(List<Results> results) {

                adapter =new DiscoverAdapter(getContext(), results, new itemClickCallback() {
                    @Override
                    public void onItemClicked(Results details) {

                    }
                });
                binding.watchedShowRecycler.setAdapter(adapter);
            }
        });

    }
}