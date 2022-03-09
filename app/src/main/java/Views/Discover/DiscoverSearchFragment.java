package Views.Discover;



import android.os.Bundle;

import ViewModels.SearchViewModelFactory;
import utils.SerializationUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;

import android.widget.TextView;
import android.widget.Toast;

import com.example.tvtimeclone.R;
import com.example.tvtimeclone.databinding.FragmentDiscoverSearchBinding;
import java.util.List;
import Adapter.DiscoverAdapter;
import Adapter.addClickCallback;
import Adapter.itemClickCallback;
import Models.DetailsModel.Results;
import ViewModels.DiscoverSearchViewModel;


public class DiscoverSearchFragment extends Fragment {

    FragmentDiscoverSearchBinding binding;
    private DiscoverAdapter searchAdapter;
    private DiscoverSearchViewModel viewModel;
    private NavController navController;
    private boolean isShow = false;

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


        viewModel = new ViewModelProvider(requireActivity(),
                new SearchViewModelFactory(getContext())).get(DiscoverSearchViewModel.class);


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
                        searchAdapter = new DiscoverAdapter(getContext(), results, new itemClickCallback() {
                            @Override
                            public void onItemClicked(Results details) {
                                details.isShow = isShow;
                                DiscoverSearchFragmentDirections.ActionDiscoverSearchFragmentToDetailsFragment action =
                                        DiscoverSearchFragmentDirections.actionDiscoverSearchFragmentToDetailsFragment(SerializationUtils.convertToByteString(details));
                                navController.navigate(action);
                            }
                        }, new addClickCallback() {
                            @Override
                            public void onAddButtonClicked(Results movies) {

                                    viewModel.setWatchLaterMovies(movies);
                                Toast.makeText(getContext(),movies.getTitle()+" added to watch Later",Toast.LENGTH_SHORT).show();

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
                searchAdapter = new DiscoverAdapter(getContext(), results, new itemClickCallback() {
                    @Override
                    public void onItemClicked(Results details) {
                        details.isShow = isShow;

                        DiscoverSearchFragmentDirections.ActionDiscoverSearchFragmentToDetailsFragment action =
                                DiscoverSearchFragmentDirections.actionDiscoverSearchFragmentToDetailsFragment(SerializationUtils.convertToByteString(details));

                        navController.navigate(action);
                    }
                }, new addClickCallback() {
                    @Override
                    public void onAddButtonClicked(Results movies) {
                        viewModel.setWatchLaterShows(movies);
                        Toast.makeText(getContext(),movies.getTitle()+" added to watch Later",Toast.LENGTH_SHORT).show();

                    }
                });
                binding.searchProgress.setVisibility(View.GONE);
                binding.searchRecyclerView.setVisibility(View.VISIBLE);
                binding.searchRecyclerView.setAdapter(searchAdapter);
            }
        });

    }


}