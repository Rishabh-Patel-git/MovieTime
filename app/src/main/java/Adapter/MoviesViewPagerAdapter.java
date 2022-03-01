package Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import Views.Discover.DetailsFragment;
import Views.Discover.DiscoverFragment;
import Views.Movies.WatchLaterMoviesFragment;
import Views.Movies.WatchedMoviesFragment;

public class MoviesViewPagerAdapter extends FragmentStateAdapter {


    public MoviesViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
      if(position == 0){
          return new WatchLaterMoviesFragment();
      }
      return new WatchedMoviesFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
