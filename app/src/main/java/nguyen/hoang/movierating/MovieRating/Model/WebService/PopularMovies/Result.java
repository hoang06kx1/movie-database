package nguyen.hoang.movierating.MovieRating.Model.WebService.PopularMovies;

public class Result {
    public final boolean adult;
    public final String backdrop_path;
    public final int[] genre_ids;
    public final long id;
    public final String original_language;
    public final String original_title;
    public final String overview;
    public final String release_date;
    public final String poster_path;
    public final double popularity;
    public final String title;
    public final boolean video;
    public final double vote_average;
    public final long vote_count;

    public Result(boolean adult, String backdrop_path, int[] genre_ids, long id, String original_language, String original_title, String overview, String release_date, String poster_path, double popularity, String title, boolean video, double vote_average, long vote_count) {
        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.genre_ids = genre_ids;
        this.id = id;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.release_date = release_date;
        this.poster_path = poster_path;
        this.popularity = popularity;
        this.title = title;
        this.video = video;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }


    public boolean isAdult() {
        return adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public int[] getGenre_ids() {
        return genre_ids;
    }

    public long getId() {
        return id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getTitle() {
        return title;
    }

    public boolean isVideo() {
        return video;
    }

    public double getVote_average() {
        return vote_average;
    }

    public long getVote_count() {
        return vote_count;
    }
}
