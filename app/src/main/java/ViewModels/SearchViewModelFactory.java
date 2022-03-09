package ViewModels;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class SearchViewModelFactory implements ViewModelProvider.Factory {
    Context context;
    public SearchViewModelFactory(Context context){
        this.context = context;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new DiscoverSearchViewModel(context);
    }


}
