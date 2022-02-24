package ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import Models.DetailsModel.Results;
import repository.DiscoverRepo;
import repository.SearchRepo;

public class DiscoverSearchViewModel extends ViewModel  {

    private final SearchRepo searchRepo;


    public DiscoverSearchViewModel() {
        searchRepo = new SearchRepo();
    }

    public LiveData<List<Results>> getMovieSearchDetails(String movieName) {
        return searchRepo.getMovieSearchDetails(movieName);
    }

    public LiveData<List<Results>> getShowSearchDetails(String showName){
        return searchRepo.getShowSearchDetails(showName);
    }




}
