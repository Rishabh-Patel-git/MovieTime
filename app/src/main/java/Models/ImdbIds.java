package Models;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.util.List;

public class ImdbIds implements Serializable {


    private List<Results> results;

    public List<Results> getResults() {
        return this.results;
    }


    public static class Results implements Serializable {
        private String imdb_id;
        private Integer popularity;
        private String title;

        public String getImdb_id() {
            return this.imdb_id;
        }
        public Integer getPopularity() {
            return this.popularity;
        }
        public String getTitle() {
            return this.title;
        }

    }
}
