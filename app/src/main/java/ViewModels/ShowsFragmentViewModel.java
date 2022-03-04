package ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import Models.DetailsModel;
import repository.FirebaseRepo;

public class ShowsFragmentViewModel extends ViewModel {

    FirebaseRepo firebaseRepo;

    public ShowsFragmentViewModel(){
        firebaseRepo  = new FirebaseRepo();
    }

    public LiveData<List<DetailsModel.Results>> getWatchedShows(){
        return firebaseRepo.getWatchedShowsLiveData();
    }
    public LiveData<List<DetailsModel.Results>> getWatchLaterShows(){
        return firebaseRepo.getWatchLaterShowsLiveData();
    }


}
