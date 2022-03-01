package Views.Discover;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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
import com.google.firebase.auth.FirebaseUser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import Adapter.MoviesAdapter;
import Adapter.addClickCallback;
import Adapter.itemClickCallback;
import Models.DetailsModel;
import Models.ImdbIds;
import Models.DetailsModel.Results;
import Network.ApiService;
import Network.RetrofitInstance;
import ViewModels.DiscoverViewModel;
import repository.DiscoverRepo;
import repository.FirebaseAuthRepo;
import repository.FirebaseRepo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscoverFragment extends Fragment  {

    private static final String TAG = "Discover Fragment";
    private MoviesAdapter movieAdapter, showAdapter;
    private FirebaseRepo firebaseRepo;
    private NavController navController;
    private DiscoverViewModel viewModel;
    private FragmentDiscoverBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDiscoverBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseAuthRepo firebaseAuthRepo = new FirebaseAuthRepo(getContext());
        firebaseAuthRepo.getFirebaseUser().observe(getViewLifecycleOwner(), new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                firebaseRepo = new FirebaseRepo(firebaseUser);
            }
        });
        navController = Navigation.findNavController(view);


        viewModel = new ViewModelProvider(requireActivity()).get(DiscoverViewModel.class);

        viewModel.getMovieUrls().observe(getViewLifecycleOwner(), new Observer<List<DetailsModel.Results>>() {
            @Override
            public void onChanged(List<DetailsModel.Results> results) {
                movieAdapter = new MoviesAdapter(getContext(), results,
                        new itemClickCallback() {
                            @Override
                            public void onItemClicked(Results details) {
                                details.isShow = false;
                                DiscoverFragmentDirections.ActionDiscoverFragmentToDetailsFragment action =
                                        DiscoverFragmentDirections.actionDiscoverFragmentToDetailsFragment(convertToByteString(details));

                                navController.navigate(action);
                            }
                        }, new addClickCallback() {
                    @Override
                    public void onAddButtonClicked(DetailsModel.Results movies) {
                        firebaseRepo.setWatchLaterMovies(movies);
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
                showAdapter = new MoviesAdapter(getContext(), results, new itemClickCallback() {
                    @Override
                    public void onItemClicked(Results details) {
                        details.isShow = true;
                        DiscoverFragmentDirections.ActionDiscoverFragmentToDetailsFragment action =
                                DiscoverFragmentDirections.actionDiscoverFragmentToDetailsFragment(convertToByteString(details));

                        navController.navigate(action);
                    }
                }, new addClickCallback() {
                    @Override
                    public void onAddButtonClicked(DetailsModel.Results movies) {
                        firebaseRepo.setWatchLaterShows(movies);
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

    public static String convertToByteString(Results results)  {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutput out = new ObjectOutputStream(bos)) {
            out.writeObject(results);
            final byte[] byteArray = bos.toByteArray();
            return Base64.getEncoder().encodeToString(byteArray);
        }catch (Exception e){
            return "";
        }
    }
}