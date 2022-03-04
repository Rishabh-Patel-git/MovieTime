package ViewModels;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import Models.DetailsModel;
import repository.FirebaseRepo;

public class MoviesFragmentViewModel extends ViewModel {
    private FirebaseRepo firebaseRepo;
    public MoviesFragmentViewModel(){
        firebaseRepo = new FirebaseRepo();
    }

    public LiveData<List<DetailsModel.Results>> getWatchLaterMovies(){
        return firebaseRepo.getWatchLaterMoviesLiveData();
    }

    public LiveData<List<DetailsModel.Results>> getWatchedMovies(){
        return firebaseRepo.getWatchedMoviesLiveData();
    }
}
