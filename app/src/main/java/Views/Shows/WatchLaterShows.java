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

import ViewModels.WatchLaterShowsViewModel;

public class WatchLaterShows extends Fragment {

    private WatchLaterShowsViewModel mViewModel;

    public static WatchLaterShows newInstance() {
        return new WatchLaterShows();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.watch_later_shows_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WatchLaterShowsViewModel.class);
        // TODO: Use the ViewModel
    }

}