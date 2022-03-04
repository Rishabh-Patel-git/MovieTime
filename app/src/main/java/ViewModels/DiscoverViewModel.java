package ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import Models.DetailsModel.Results;
import repository.DiscoverRepo;
import repository.FirebaseRepo;

public class DiscoverViewModel extends ViewModel {
    private final DiscoverRepo discoverRepo;
    private final FirebaseRepo firebaseRepo;


    public DiscoverViewModel() {

        discoverRepo = new DiscoverRepo();
        firebaseRepo = new FirebaseRepo();
    }

    public LiveData<List<Results>> getMovieUrls() {
        return discoverRepo.getMovieDetails();
    }

    public LiveData<List<Results>> getShowUrls() {
        return discoverRepo.getShowUrls();
    }


    public void setWatchedLaterMovies(Results movies){
        firebaseRepo.setWatchLaterMovies(movies);
    }

    public void setWatchLaterShows(Results shows){
        firebaseRepo.setWatchLaterShows(shows);
    }


}
