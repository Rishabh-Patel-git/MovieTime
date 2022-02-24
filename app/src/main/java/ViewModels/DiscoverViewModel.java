package ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import Models.DetailsModel.Results;
import repository.DiscoverRepo;

public class DiscoverViewModel extends ViewModel {
    private final DiscoverRepo discoverRepo;

    private MutableLiveData<Results> details = new MutableLiveData<>();

    public DiscoverViewModel() {
        discoverRepo = new DiscoverRepo();
    }

    public LiveData<List<Results>> getMovieUrls() {
        return discoverRepo.getMovieDetails();
    }

    public LiveData<List<Results>> getShowUrls() {
        return discoverRepo.getShowUrls();
    }

    public void setItemDetails(Results details1) {
        details.setValue(details1);
    }

    public LiveData<Results> getItemDetails(){
        return details;
    }
}
