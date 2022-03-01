package Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import Views.Shows.WatchLaterShows;
import Views.Shows.WatchedShows;

public class ShowsViewPagerAdapter extends FragmentStateAdapter {
    public ShowsViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
       if(position == 0){
           return new WatchLaterShows();
       }
       return new WatchedShows();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
