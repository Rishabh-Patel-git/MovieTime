package Views.Discover;


import android.os.Bundle;

import ViewModels.DetailsViewModel;
import utils.SerializationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import com.example.tvtimeclone.databinding.FragmentDetailsBinding;

import java.io.IOException;

import Models.DetailsModel.Results;


public class DetailsFragment extends Fragment {
    private static final String TAG = "DetailsFragment";
    private FragmentDetailsBinding binding;
    private Results navDetails;
    private DetailsViewModel viewModel;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        viewModel = new ViewModelProvider(requireActivity()).
                get(DetailsViewModel.class);

        DetailsFragmentArgs args = DetailsFragmentArgs.fromBundle(getArguments());
        String details = args.getDetails();

        // deserialize the object
        try {
            navDetails = SerializationUtils.convertFromByteString(details);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Glide.with(getContext()).load(navDetails.getImage_url()).into(binding.detailImage);
        binding.collps.setTitle(navDetails.getTitle());
        binding.description.append(navDetails.getDescription());
        binding.popularity.append(String.valueOf(navDetails.getPopularity()));
        binding.movieLength.append(String.valueOf(navDetails.getMovie_length()));
        binding.contentRating.append(navDetails.getContent_rating());
        binding.releaseYear.append(navDetails.getRelease());

        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (navDetails.isShow) {
                    viewModel.setWatchedShows(navDetails);
                } else {
                    viewModel.setWatchedMovies(navDetails);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


}