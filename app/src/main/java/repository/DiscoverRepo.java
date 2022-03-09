package repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import Models.DetailsModel;
import Models.DetailsModel.Results;
import Models.ImdbIds;
import Network.ApiService;
import Network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscoverRepo {
    private MutableLiveData<List<Results>> mutableMovieDetails;

    private MutableLiveData<List<Results>> mutableShowDetails;
    private ApiService apiService, apiService1, apiService2, apiService3, apiService4;

    public DiscoverRepo() {
        apiService = RetrofitInstance.getInstance();
        apiService1 = RetrofitInstance.getInstance();
        apiService2 = RetrofitInstance.getInstance();
        apiService3 = RetrofitInstance.getInstance();
        apiService4 = RetrofitInstance.getInstance();

    }

    public LiveData<List<Results>> getMovieDetails() {
        if (mutableMovieDetails == null) {
            mutableMovieDetails = new MutableLiveData<>();

            loadMovieDetails();

        }
        return mutableMovieDetails;
    }

    public LiveData<List<Results>> getShowUrls() {
        if (mutableShowDetails == null) {
            mutableShowDetails = new MutableLiveData<>();
            loadShowDetails();
        }
        return mutableShowDetails;
    }


    private void loadShowDetails() {
        apiService2.getPopularShows().enqueue(new Callback<ImdbIds>() {
            @Override
            public void onResponse(Call<ImdbIds> call, Response<ImdbIds> response) {
                if (response.isSuccessful()) {
                    getDetailsForShow(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<ImdbIds> call, Throwable t) {

            }
        });
    }

    private void getDetailsForShow(List<ImdbIds.Results> showImdbIdList) {
        List<Results> showDetailList = new ArrayList<>();
        for (int i = 0; i < showImdbIdList.size(); i++) {
            apiService3.getShowsDetails(showImdbIdList.get(i).getImdb_id()).enqueue(new Callback<DetailsModel>() {
                @Override
                public void onResponse(Call<DetailsModel> call, Response<DetailsModel> response) {
                    if (response.isSuccessful()) {
                        showDetailList.add(response.body().getResults());
                        mutableShowDetails.setValue(showDetailList);

                    }
                }

                @Override
                public void onFailure(Call<DetailsModel> call, Throwable t) {
                }
            });

        }
    }

    private void loadMovieDetails() {
        apiService.getPopularMovies().enqueue(new Callback<ImdbIds>() {
            @Override
            public void onResponse(Call<ImdbIds> call, Response<ImdbIds> response) {
                if (response.isSuccessful()) {

                    getDetailsForMovies(response.body().getResults());

                }
            }

            @Override
            public void onFailure(Call<ImdbIds> call, Throwable t) {

            }
        });
    }

    protected void getDetailsForMovies(List<ImdbIds.Results> idList) {
        List<Results> movieDetails = new ArrayList<>();
        for (int i = 0; i < idList.size(); i++) {
            apiService1.getMoviesDetails(idList.get(i).getImdb_id()).enqueue(new Callback<DetailsModel>() {
                @Override
                public void onResponse(Call<DetailsModel> call, Response<DetailsModel> response) {
                    if (response.isSuccessful()) {
                        movieDetails.add(response.body().getResults());
                        mutableMovieDetails.setValue(movieDetails);
                    }
                }

                @Override
                public void onFailure(Call<DetailsModel> call, Throwable t) {
                    Log.e("TAG", "Something went wrong");
                }
            });

        }


    }


}
