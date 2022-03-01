package Views.Discover;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import com.example.tvtimeclone.databinding.FragmentDetailsBinding;
import com.google.firebase.auth.FirebaseUser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.Base64;

import Models.DetailsModel;
import Models.DetailsModel.Results;
import ViewModels.DiscoverViewModel;
import repository.FirebaseAuthRepo;
import repository.FirebaseRepo;


public class DetailsFragment extends Fragment {
    private static final String TAG = "DetailsFragment";
    FragmentDetailsBinding binding;
    private Results obj;
    FirebaseRepo repo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        DiscoverViewModel viewModel = new ViewModelProvider(requireActivity()).get(DiscoverViewModel.class);

        DetailsFragmentArgs args = DetailsFragmentArgs.fromBundle(getArguments());
        String details = args.getDetails();
        FirebaseAuthRepo authRepo= new FirebaseAuthRepo(getContext());
                authRepo.getFirebaseUser().observe(getViewLifecycleOwner(), new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {

                repo = new FirebaseRepo(firebaseUser);
            }
        });


        // deserialize the object
        try {
            obj = convertFromByteString(details);


        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Glide.with(getContext()).load(obj.getImage_url()).into(binding.detailImage);
        binding.collps.setTitle(obj.getTitle());
        binding.description.append(obj.getDescription());
        binding.popularity.append(String.valueOf(obj.getPopularity()));
        binding.movieLength.append(String.valueOf(obj.getMovie_length()));
        binding.contentRating.append(obj.getContent_rating());
        binding.releaseYear.append(obj.getRelease());

        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(obj.isShow){
                    repo.setWatchedShows(obj);
                }
                else{
                repo.setWatchedMovies(obj);}
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public static Results convertFromByteString(String byteString) throws IOException, ClassNotFoundException {
        final byte[] bytes = Base64.getDecoder().decode(byteString);
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInput in = new ObjectInputStream(bis)) {
            return (Results) in.readObject();
        }
    }
}