package Models;



import androidx.annotation.Keep;

import java.io.Serializable;
import java.util.List;

public class DetailsModel implements Serializable {
    private Results results;

    public DetailsModel(){}


    public Results getResults() {
        return this.results;
    }


    public static class Results implements Serializable {

        @Keep
        public  Results(){}


        public   boolean isShow;

        private List<Keywords> keywords;

        private String imdb_id;

        private Integer year;

        private String image_url;

        private String release;

        private Double rating;

        private String description;

        private String created_at;

        private String banner;



        private String title;

        private String type;

        private String trailer;

        private List<Gen> gen;

        private String plot;

        private Integer popularity;

        private Integer movie_length;

        private String content_rating;


        public List<Keywords> getKeywords() {
            return this.keywords;
        }


        public String getImdb_id() {
            return this.imdb_id;
        }


        public Integer getYear() {
            return this.year;
        }


        public String getImage_url() {
            return this.image_url;
        }


        public String getRelease() {
            return this.release;
        }


        public Double getRating() {
            return this.rating;
        }


        public String getDescription() {
            return this.description;
        }


        public String getCreated_at() {
            return this.created_at;
        }


        public String getBanner() {
            return this.banner;
        }

        public String getTitle() {
            return this.title;
        }


        public String getType() {
            return this.type;
        }


        public String getTrailer() {
            return this.trailer;
        }


        public List<Gen> getGen() {
            return this.gen;
        }


        public String getPlot() {
            return this.plot;
        }


        public Integer getPopularity() {
            return this.popularity;
        }


        public Integer getMovie_length() {
            return this.movie_length;
        }


        public String getContent_rating() {
            return this.content_rating;
        }

        public void setKeywords(List<Keywords> keywords) {
            this.keywords = keywords;
        }

        public void setImdb_id(String imdb_id) {
            this.imdb_id = imdb_id;
        }

        public void setYear(Integer year) {
            this.year = year;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public void setRelease(String release) {
            this.release = release;
        }

        public void setRating(Double rating) {
            this.rating = rating;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }



        public void setTitle(String title) {
            this.title = title;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setTrailer(String trailer) {
            this.trailer = trailer;
        }

        public void setGen(List<Gen> gen) {
            this.gen = gen;
        }

        public void setPlot(String plot) {
            this.plot = plot;
        }

        public void setPopularity(Integer popularity) {
            this.popularity = popularity;
        }

        public void setMovie_length(Integer movie_length) {
            this.movie_length = movie_length;
        }

        public void setContent_rating(String content_rating) {
            this.content_rating = content_rating;
        }

        public static class Keywords implements Serializable {
            public Keywords(){}
            private Integer id;

            private String keyword;

            public Integer getId() {
                return this.id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getKeyword() {
                return this.keyword;
            }


        }



        public static class Gen  implements Serializable{
            public Gen(){}
            private String genre;

            private Integer id;

            public String getGenre() {
                return this.genre;
            }

            public Integer getId() {
                return this.id;
            }


        }
    }

}
