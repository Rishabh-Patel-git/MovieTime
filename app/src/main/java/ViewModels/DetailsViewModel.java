package ViewModels;

import androidx.lifecycle.ViewModel;

import Models.DetailsModel;
import repository.FirebaseRepo;

public class DetailsViewModel extends ViewModel {
    private FirebaseRepo firebaseRepo;
    public DetailsViewModel(){
        firebaseRepo = new FirebaseRepo();
    }

    public void setWatchedShows(DetailsModel.Results shows){
        firebaseRepo.setWatchedShows(shows);
    }
    public void setWatchedMovies(DetailsModel.Results movies){
        firebaseRepo.setWatchedMovies(movies);
    }
}
