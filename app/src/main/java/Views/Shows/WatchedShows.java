package Views.Shows;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tvtimeclone.R;

import ViewModels.WatchedShowsViewModel;

public class WatchedShows extends Fragment {

    private WatchedShowsViewModel mViewModel;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.watched_shows_fragment, container, false);
    }



}