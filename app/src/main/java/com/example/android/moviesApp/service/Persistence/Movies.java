package com.example.android.moviesApp.service.Persistence;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class Movies{

    @SerializedName("page")
    @Expose
    @PrimaryKey
    private Integer page;

    @SerializedName("total_results")
    @Expose
    @Ignore
    private Integer totalResults;

    @SerializedName("total_pages")
    @Expose
    @Ignore
    private Integer totalPages;

    @SerializedName("results")
    @Expose
    @Ignore
   public List<Results> results = null;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }




@Entity(tableName = "movieResults")
    public static class Results {
    public static final String TMDB_IMAGE_PATH = "http://image.tmdb.org/t/p/w500";


        @SerializedName("vote_count")
        @Ignore
        private Integer voteCount;

        @SerializedName("id")
        @PrimaryKey
        @Expose
        public Integer id;

        @SerializedName("video")
        @Ignore
        private Boolean video;

        @SerializedName("vote_average")
        @Ignore
        private Double voteAverage;

        @SerializedName("title")
        @Expose
        public String title;

        @SerializedName("popularity")
        @Expose
        public Double popularity;

        @SerializedName("poster_path")
        @Expose
        public String posterPath;

        @SerializedName("original_language")
        @Ignore
        public String originalLanguage;

        @SerializedName("original_title")
        @Ignore
        public String originalTitle;

        @SerializedName("genre_ids")
        @Ignore
        public List<Integer> genreIds = null;

        @SerializedName("backdrop_path")
        @Ignore
        public String backdropPath;

        @SerializedName("adult")
        @Ignore
        public Boolean adult;

        @SerializedName("overview")
        @Expose
        public String overview;

        @SerializedName("release_date")
        @Expose
        public String releaseDate;

        public Integer getVoteCount() {
            return voteCount;
        }

        public void setVoteCount(Integer voteCount) {
            this.voteCount = voteCount;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Boolean getVideo() {
            return video;
        }

        public void setVideo(Boolean video) {
            this.video = video;
        }

        public Double getVoteAverage() {
            return voteAverage;
        }

        public void setVoteAverage(Double voteAverage) {
            this.voteAverage = voteAverage;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Double getPopularity() {
            return popularity;
        }

        public void setPopularity(Double popularity) {
            this.popularity = popularity;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        public String getOriginalLanguage() {
            return originalLanguage;
        }

        public void setOriginalLanguage(String originalLanguage) {
            this.originalLanguage = originalLanguage;
        }

        public String getOriginalTitle() {
            return originalTitle;
        }

        public void setOriginalTitle(String originalTitle) {
            this.originalTitle =            originalTitle;
        }

        public List<Integer> getGenreIds() {
            return genreIds;
        }

        public void setGenreIds(List<Integer> genreIds) {
            this.genreIds = genreIds;
        }

        public String getBackdropPath() {
            return backdropPath;
        }

        public void setBackdropPath(String backdropPath) {
            this.backdropPath = backdropPath;
        }

        public Boolean getAdult() {
            return adult;
        }

        public void setAdult(Boolean adult) {
            this.adult = adult;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

    }

}





