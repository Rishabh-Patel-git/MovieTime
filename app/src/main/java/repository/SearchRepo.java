package repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Models.DetailsModel;
import Models.DetailsModel.Results;
import Models.ImdbIds;

import Network.ApiService;
import Network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchRepo extends DiscoverRepo {
    private MutableLiveData<List<Results>> mutableSearchDetails;
    private MutableLiveData<List<Results>> ShowDetails;
    private ApiService apiServiceA, apiServiceB, apiServiceC, apiServiceD;
    private Context context;
    public SearchRepo(Context context) {
        apiServiceA = RetrofitInstance.getInstance();
        apiServiceB = RetrofitInstance.getInstance();
        apiServiceC = RetrofitInstance.getInstance();
        apiServiceD = RetrofitInstance.getInstance();
        this.context = context;
    }

    public LiveData<List<Results>> getMovieSearchDetails(String movieName) {
        if (mutableSearchDetails == null) {
            mutableSearchDetails = new MutableLiveData<>();

        }

        loadMovieSearchDetails(movieName);
        return mutableSearchDetails;
    }

    public LiveData<List<Results>> getShowSearchDetails(String showName) {
        if (ShowDetails == null) {
            ShowDetails = new MutableLiveData<>();
        }
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                loadShowSearchDetails(showName);
            }
        });
        return ShowDetails;
    }

    private void loadShowSearchDetails(String showName) {
        if (!showName.equals("") && showName != null) {
            apiServiceC.getShowSearchResults(showName).enqueue(new Callback<ImdbIds>() {
                @Override

                public void onResponse(Call<ImdbIds> call, Response<ImdbIds> response) {
                    if (response.isSuccessful() && !response.body().getResults().isEmpty()) {
                        getDetailsForShow(response.body().getResults());
                    } else {
                        // progressBar.setVisibility(View.GONE);
                       Toast.makeText(context, "Incorrect show Name", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ImdbIds> call, Throwable t) {

                }
            });
        } else {
//            progressBar.setVisibility(View.GONE);
           Toast.makeText(context,"Plz enter a show Name",Toast.LENGTH_SHORT).show();
        }
    }

    private void getDetailsForShow(List<ImdbIds.Results> showImdbIdList) {
        List<Results> showDetailList = new ArrayList<>();
        for (int i = 0; i < showImdbIdList.size(); i++) {
            apiServiceD.getShowsDetails(showImdbIdList.get(i).getImdb_id()).enqueue(new Callback<DetailsModel>() {
                @Override
                public void onResponse(Call<DetailsModel> call, Response<DetailsModel> response) {
                    if (response.isSuccessful()) {
                        showDetailList.add(response.body().getResults());
                        ShowDetails.setValue(showDetailList);

                    }

                }
                @Override
                public void onFailure(Call<DetailsModel> call, Throwable t) {
                }
            });

        }
    }

    private void loadMovieSearchDetails(String movieName) {
        if (!movieName.equals("") && movieName != null) {
            apiServiceA.getSearchResults(movieName).enqueue(new Callback<ImdbIds>() {
                @Override
                public void onResponse(Call<ImdbIds> call, Response<ImdbIds> response) {
                    if (response.isSuccessful() && !response.body().getResults().isEmpty()) {
                        getDetailsForMovies(response.body().getResults());

                    } else {
                        // progressBar.setVisibility(View.GONE);
                        Toast.makeText(context, "Incorrect Movie Name", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ImdbIds> call, Throwable t) {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
//            progressBar.setVisibility(View.GONE);
           Toast.makeText(context,"Plz enter a movie Name",Toast.LENGTH_SHORT).show();
        }
    }

    protected void getDetailsForMovies(List<ImdbIds.Results> idList) {
        List<Results> movieDetails = new ArrayList<>();
        for (int i = 0; i < idList.size(); i++) {
            apiServiceB.getMoviesDetails(idList.get(i).getImdb_id()).enqueue(new Callback<DetailsModel>() {
                @Override
                public void onResponse(Call<DetailsModel> call, Response<DetailsModel> response) {
                    if (response.isSuccessful()) {
                        movieDetails.add(response.body().getResults());
                        mutableSearchDetails.setValue(movieDetails);
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
