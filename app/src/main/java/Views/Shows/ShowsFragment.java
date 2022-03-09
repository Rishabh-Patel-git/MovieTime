package Views.Shows;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tvtimeclone.databinding.FragmentShowsBinding;
import com.google.android.material.tabs.TabLayoutMediator;

import Adapter.ShowsViewPagerAdapter;


public class ShowsFragment extends Fragment {
private FragmentShowsBinding binding;
    private String[] titles = new String[]{"Watch Later","Watched"};
    private ShowsViewPagerAdapter showsViewPagerAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      binding = FragmentShowsBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showsViewPagerAdapter = new ShowsViewPagerAdapter(getChildFragmentManager(),getLifecycle());
        binding.showsViewpager.setAdapter(showsViewPagerAdapter);
        new TabLayoutMediator(binding.showsTab,binding.showsViewpager,(((tab, position) -> tab.setText(titles[position]) ))).attach();
    }
}