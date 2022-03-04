package Views.Movies;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tvtimeclone.databinding.FragmentMoviesBinding;
import com.google.android.material.tabs.TabLayoutMediator;

import Adapter.MoviesViewPagerAdapter;

public class MoviesFragment extends Fragment {
    FragmentMoviesBinding binding;
    private MoviesViewPagerAdapter viewPagerAdapter;
    private String[] titles = new String[]{"Watch Later","Watched"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentMoviesBinding.inflate(inflater,container,false );
       return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPagerAdapter = new MoviesViewPagerAdapter(getChildFragmentManager(),getLifecycle());
        binding.movieViewpager.setAdapter(viewPagerAdapter);
        new TabLayoutMediator(binding.movieTabs,binding.movieViewpager,(((tab, position) -> tab.setText(titles[position]) ))).attach();
    }
}