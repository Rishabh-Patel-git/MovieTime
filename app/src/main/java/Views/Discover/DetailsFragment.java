package Views.Discover;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.tvtimeclone.R;
import com.example.tvtimeclone.databinding.FragmentDetailsBinding;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import Models.DetailsModel;
import ViewModels.DiscoverViewModel;


public class DetailsFragment extends Fragment {
    private static final String TAG = "DetailsFragment";
    FragmentDetailsBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        DiscoverViewModel viewModel = new ViewModelProvider(requireActivity()).get(DiscoverViewModel.class);
            DetailsFragmentArgs args = DetailsFragmentArgs.fromBundle(getArguments());
           String details = args.getDetails();
       String[] everything = details.split("rishabh");
//        viewModel.getItemDetails().observe(getViewLifecycleOwner(), new Observer<DetailsModel.Results>() {
//            @Override
//            public void onChanged(DetailsModel.Results results) {
////                Glide.with(getContext()).load(results.getBanner()).into(binding.detailImage);
////                binding.collps.setTitle(results.getTitle());
////                binding.description.append(results.getDescription());
////                binding.popularity.append(String.valueOf(results.getPopularity()));
////                binding.movieLength.append(String.valueOf(results.getMovie_length()));
////                binding.contentRating.append(results.getContent_rating());
////                binding.releaseYear.append(results.getRelease());
//
//
//
//
//            }
//        });
        Glide.with(getContext()).load(everything[2]).into(binding.detailImage);
        binding.collps.setTitle(everything[5]);
        binding.description.append(everything[9]);
        binding.popularity.append(everything[6]);
        binding.movieLength.append(everything[7]);
        binding.contentRating.append(everything[8]);
        binding.releaseYear.append(everything[3]);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}