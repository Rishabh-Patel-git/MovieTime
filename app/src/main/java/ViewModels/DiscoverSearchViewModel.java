package ViewModels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import Models.DetailsModel.Results;
import repository.FirebaseRepo;
import repository.SearchRepo;

public class DiscoverSearchViewModel extends ViewModel {

    private SearchRepo searchRepo;
    private FirebaseRepo firebaseRepo;

    public DiscoverSearchViewModel(Context context) {
        searchRepo = new SearchRepo(context);
        firebaseRepo = new FirebaseRepo();
    }

    public LiveData<List<Results>> getMovieSearchDetails(String movieName) {
        return searchRepo.getMovieSearchDetails(movieName);
    }

    public LiveData<List<Results>> getShowSearchDetails(String showName) {
        return searchRepo.getShowSearchDetails(showName);
    }

    public void setWatchLaterMovies(Results movies){
        firebaseRepo.setWatchLaterMovies(movies);
    }
    public void setWatchLaterShows(Results shows){
        firebaseRepo.setWatchLaterShows(shows);
    }


}
