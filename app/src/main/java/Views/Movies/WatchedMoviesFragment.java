package Views.Movies;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.tvtimeclone.R;
import com.example.tvtimeclone.databinding.WatchedMoviesFragmentBinding;

import java.util.List;

import Adapter.MoviesAdapter;
import Adapter.itemClickCallback;
import Models.DetailsModel;
import ViewModels.WatchedMoviesViewModel;
import repository.FirebaseRepo;

public class WatchedMoviesFragment extends Fragment {

    private WatchedMoviesViewModel mViewModel;
    private WatchedMoviesFragmentBinding binding;
    MoviesAdapter moviesAdapter;

    private static final String TAG = "yup ";
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding  = WatchedMoviesFragmentBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WatchedMoviesViewModel.class);
        FirebaseRepo repo = new FirebaseRepo();

       repo.getWatchedMoviesLiveData().observe(getViewLifecycleOwner(), new Observer<List<DetailsModel.Results>>() {
           @Override
           public void onChanged(List<DetailsModel.Results> results) {
               moviesAdapter = new MoviesAdapter(getContext(), results, new itemClickCallback() {
                   @Override
                   public void onItemClicked(DetailsModel.Results details) {
                       Log.i(TAG, "onItemClicked: uo");
                   }
               });
               binding.watchedRecycler.setAdapter(moviesAdapter);

           }
       });



    }
}