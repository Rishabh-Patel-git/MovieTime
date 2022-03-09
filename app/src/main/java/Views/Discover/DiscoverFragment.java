package Views.Discover;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.tvtimeclone.R;
import com.example.tvtimeclone.databinding.FragmentDiscoverBinding;

import java.util.List;

import Adapter.DiscoverAdapter;
import Adapter.addClickCallback;
import Adapter.itemClickCallback;
import Models.DetailsModel;
import Models.DetailsModel.Results;
import ViewModels.DiscoverViewModel;
import utils.SerializationUtils;

public class DiscoverFragment extends Fragment  {

    private static final String TAG = "Discover Fragment";
    private DiscoverAdapter movieAdapter, showAdapter;
    private NavController navController;
    private DiscoverViewModel viewModel;
    private FragmentDiscoverBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDiscoverBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        navController = Navigation.findNavController(view);


        viewModel = new ViewModelProvider(requireActivity()).get(DiscoverViewModel.class);

        viewModel.getMovieUrls().observe(getViewLifecycleOwner(), new Observer<List<DetailsModel.Results>>() {
            @Override
            public void onChanged(List<DetailsModel.Results> results) {
                movieAdapter = new DiscoverAdapter(getContext(), results,
                        new itemClickCallback() {
                            @Override
                            public void onItemClicked(Results details) {
                                details.isShow = false;
                                DiscoverFragmentDirections.ActionDiscoverFragmentToDetailsFragment action =
                                        DiscoverFragmentDirections.actionDiscoverFragmentToDetailsFragment(SerializationUtils.convertToByteString(details));

                                navController.navigate(action);
                            }
                        }, new addClickCallback() {
                    @Override
                    public void onAddButtonClicked(DetailsModel.Results movies) {
                       viewModel.setWatchedLaterMovies(movies);
                        Toast.makeText(getContext(),movies.getTitle()+" added to watch Later",Toast.LENGTH_SHORT).show();
                    }
                });
                binding.discoverMoviesRecyclerView.setHasFixedSize(true);
                binding.discoverMoviesRecyclerView.setAdapter(movieAdapter);
                binding.progressBar.setVisibility(View.GONE);
                binding.discoverMoviesRecyclerView.setVisibility(View.VISIBLE);
            }

        });


        viewModel.getShowUrls().observe(getViewLifecycleOwner(), new Observer<List<DetailsModel.Results>>() {
            @Override
            public void onChanged(List<DetailsModel.Results> results) {
                showAdapter = new DiscoverAdapter(getContext(), results, new itemClickCallback() {
                    @Override
                    public void onItemClicked(Results details) {
                        details.isShow = true;
                        DiscoverFragmentDirections.ActionDiscoverFragmentToDetailsFragment action =
                                DiscoverFragmentDirections.actionDiscoverFragmentToDetailsFragment(SerializationUtils.convertToByteString(details));

                        navController.navigate(action);
                    }
                }, new addClickCallback() {
                    @Override
                    public void onAddButtonClicked(DetailsModel.Results movies) {
                       viewModel.setWatchLaterShows(movies);
                        Toast.makeText(getContext(),movies.getTitle()+" added to watch Later",Toast.LENGTH_SHORT).show();

                    }
                });
                binding.progressBar1.setVisibility(View.GONE);
                binding.discoverShowsRecyclerView.setVisibility(View.VISIBLE);
                binding.discoverShowsRecyclerView.setAdapter(showAdapter);
            }
        });

        binding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_discoverFragment_to_discoverSearchFragment);
            }
        });


    }


}