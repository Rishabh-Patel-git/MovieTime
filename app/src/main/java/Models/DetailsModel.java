package Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class DetailsModel implements Serializable {
    private Results results;

    protected DetailsModel(Parcel in) {
    }


    public Results getResults() {
        return this.results;
    }


    public static class Results implements Serializable {
        @Override
        public String toString() {
            return
                    imdb_id + "rishabh" +
                            year + "rishabh" +
                            image_url + "rishabh" +
                            release + "rishabh" +
                            rating + "rishabh" + title + "rishabh" +
                            popularity + "rishabh" +
                            movie_length + "rishabh" +
                            content_rating + "rishabh" +
                            description

                    ;
        }

        private List<Keywords> keywords;

        private String imdb_id;

        private Integer year;

        private String image_url;

        private String release;

        private Double rating;

        private String description;

        private String created_at;

        private String banner;

        private More_like_this more_like_this;

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


        public More_like_this getMore_like_this() {
            return this.more_like_this;
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


        public static class Keywords implements Serializable {
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

        public static class More_like_this implements Serializable {
        }

        public static class Gen implements Serializable {
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
