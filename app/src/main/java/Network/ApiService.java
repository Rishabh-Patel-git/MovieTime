package Network;

import Models.ImdbIds;
import Models.DetailsModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ApiService {
    @Headers({"x-rapidapi-host: data-imdb1.p.rapidapi.com","x-rapidapi-key: f10afb2c73msh013a79a65afe8c4p1ed5eajsn85cf1e10c2cb"})
    @GET("movie/order/byPopularity/?page_size=20")
    Call<ImdbIds> getPopularMovies();

    @Headers({"x-rapidapi-host: data-imdb1.p.rapidapi.com","x-rapidapi-key: f10afb2c73msh013a79a65afe8c4p1ed5eajsn85cf1e10c2cb"})
    @GET("/movie/id/{imdbId}/")
    Call<DetailsModel> getMoviesDetails(@Path("imdbId")String imdbId);

    @Headers({"x-rapidapi-host: data-imdb1.p.rapidapi.com","x-rapidapi-key: f10afb2c73msh013a79a65afe8c4p1ed5eajsn85cf1e10c2cb"})
    @GET("series/order/byRating/?page_size=20")
    Call<ImdbIds> getPopularShows();

    @Headers({"x-rapidapi-host: data-imdb1.p.rapidapi.com","x-rapidapi-key: f10afb2c73msh013a79a65afe8c4p1ed5eajsn85cf1e10c2cb"})
    @GET("series/id/{imdbId}/")
    Call<DetailsModel> getShowsDetails(@Path("imdbId") String imdbId);

    @Headers({"x-rapidapi-host: data-imdb1.p.rapidapi.com","x-rapidapi-key: f10afb2c73msh013a79a65afe8c4p1ed5eajsn85cf1e10c2cb"})
    @GET("movie/imdb_id/byTitle/{movieName}/")
    Call<ImdbIds> getSearchResults(@Path("movieName")String movieName);

    @Headers({"x-rapidapi-host: data-imdb1.p.rapidapi.com","x-rapidapi-key: f10afb2c73msh013a79a65afe8c4p1ed5eajsn85cf1e10c2cb"})
        @GET("series/idbyTitle/{showName}/")
    Call<ImdbIds> getShowSearchResults(@Path("showName")String showName);





}
