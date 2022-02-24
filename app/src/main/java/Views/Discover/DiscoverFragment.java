package Views.Discover;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvtimeclone.R;
import com.example.tvtimeclone.databinding.FragmentDetailsBinding;
import com.example.tvtimeclone.databinding.FragmentDiscoverBinding;

import java.util.ArrayList;
import java.util.List;

import Adapter.MoviesAdapter;
import Adapter.itemClickCallback;
import Models.ImdbIds;
import Models.DetailsModel;
import Network.ApiService;
import Network.RetrofitInstance;
import ViewModels.DiscoverViewModel;
import repository.DiscoverRepo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscoverFragment extends Fragment implements itemClickCallback {

    private MoviesAdapter movieAdapter, showAdapter;

    private NavController navController;

     private DiscoverViewModel viewModel;
     FragmentDiscoverBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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
                movieAdapter = new MoviesAdapter(getContext(), results,DiscoverFragment.this);
               binding.discoverMoviesRecyclerView.setAdapter(movieAdapter);
                binding.progressBar.setVisibility(View.GONE);
                binding.discoverMoviesRecyclerView.setVisibility(View.VISIBLE);
            }

        });


        viewModel.getShowUrls().observe(getViewLifecycleOwner(), new Observer<List<DetailsModel.Results>>() {
            @Override
            public void onChanged(List<DetailsModel.Results> results) {
                showAdapter = new MoviesAdapter(getContext(), results,DiscoverFragment.this);
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
        binding.trendingMoviesText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_discoverFragment_to_detailsFragment);
            }
        });

    }


    @Override
    public void onItemClicked(DetailsModel.Results details) {
//        viewModel.setItemDetails(details);
            DiscoverFragmentDirections.ActionDiscoverFragmentToDetailsFragment action =  DiscoverFragmentDirections.actionDiscoverFragmentToDetailsFragment(details.toString());

        navController.navigate(action);
    }
}