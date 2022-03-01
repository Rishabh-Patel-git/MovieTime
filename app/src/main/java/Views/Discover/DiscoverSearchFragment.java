package Views.Discover;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tvtimeclone.R;
import com.example.tvtimeclone.databinding.FragmentDiscoverBinding;
import com.example.tvtimeclone.databinding.FragmentDiscoverSearchBinding;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import Adapter.MoviesAdapter;
import Adapter.addClickCallback;
import Adapter.itemClickCallback;
import Models.DetailsModel.Results;
import ViewModels.DiscoverSearchViewModel;
import repository.FirebaseAuthRepo;
import repository.FirebaseRepo;

public class DiscoverSearchFragment extends Fragment {

    FragmentDiscoverSearchBinding binding;
    private MoviesAdapter searchAdapter;
    private DiscoverSearchViewModel viewModel;
    private NavController navController;
    private boolean isShow = false;
    private FirebaseAuthRepo authRepo;
    private FirebaseRepo firebaseRepo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDiscoverSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        authRepo = new FirebaseAuthRepo(getContext());
        authRepo.getFirebaseUser().observe(getViewLifecycleOwner(), new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                firebaseRepo = new FirebaseRepo(firebaseUser);
            }
        });

        viewModel = new ViewModelProvider(DiscoverSearchFragment.this).get(DiscoverSearchViewModel.class);
        binding.switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean show) {
                if (show) {
                    isShow = true;
                }

            }
        });
        binding.searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    if (isShow) {
                        getShows();
                    } else {
                        getMovies();

                    }
                    return true;
                }
                return false;
            }
        });

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_discoverSearchFragment_to_discoverFragment);
            }
        });
    }



    private void getMovies() {
        String name = binding.searchEditText.getText().toString();
        binding.searchProgress.setVisibility(View.VISIBLE);
        viewModel.getMovieSearchDetails(name)
                .observe(getViewLifecycleOwner(), new Observer<List<Results>>() {
                    @Override
                    public void onChanged(List<Results> results) {
                        searchAdapter = new MoviesAdapter(getContext(), results, new itemClickCallback() {
                            @Override
                            public void onItemClicked(Results details) {
                                details.isShow = isShow;
                                DiscoverSearchFragmentDirections.ActionDiscoverSearchFragmentToDetailsFragment action =
                                        DiscoverSearchFragmentDirections.actionDiscoverSearchFragmentToDetailsFragment(DiscoverFragment.convertToByteString(details));
                                navController.navigate(action);
                            }
                        }, new addClickCallback() {
                            @Override
                            public void onAddButtonClicked(Results movies) {

                                    firebaseRepo.setWatchLaterMovies(movies);
                            }
                        });

                        binding.searchRecyclerView.setAdapter(searchAdapter);
                        binding.searchProgress.setVisibility(View.GONE);
                        binding.searchRecyclerView.setVisibility(View.VISIBLE);

                    }
                });

    }

    private void getShows() {
        String name = binding.searchEditText.getText().toString();
        binding.searchProgress.setVisibility(View.VISIBLE);
        viewModel.getShowSearchDetails(name).observe(getViewLifecycleOwner(), new Observer<List<Results>>() {
            @Override
            public void onChanged(List<Results> results) {
                searchAdapter = new MoviesAdapter(getContext(), results, new itemClickCallback() {
                    @Override
                    public void onItemClicked(Results details) {
                        details.isShow = isShow;

                        DiscoverSearchFragmentDirections.ActionDiscoverSearchFragmentToDetailsFragment action =
                                DiscoverSearchFragmentDirections.actionDiscoverSearchFragmentToDetailsFragment(DiscoverFragment.convertToByteString(details));

                        navController.navigate(action);
                    }
                }, new addClickCallback() {
                    @Override
                    public void onAddButtonClicked(Results movies) {
                        firebaseRepo.setWatchLaterShows(movies);
                    }
                });
                binding.searchProgress.setVisibility(View.GONE);
                binding.searchRecyclerView.setVisibility(View.VISIBLE);
                binding.searchRecyclerView.setAdapter(searchAdapter);
            }
        });

    }


}