public class Movie{

    private int movie_id;
    private String title;
    
    public Movie(int movie_id, String title){
        this.movie_id = movie_id;
        this.title = title;
    }
    
    public String getTitle(){
        return title;
    }
    
    public int getMovieId(){
        return movie_id;
    }
}