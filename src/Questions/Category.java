package Questions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Category implements Serializable {
    String History;
    String Movies;
    String Sport;
    String Geography;
    String Music;
    String Computers;

    public Category() {}

    public void setHistory(String history) {
        History = history;
    }

    public void setMovies(String movies) {
        Movies = movies;
    }

    public void setSport(String sport) {
        Sport = sport;
    }

    public void setGeography(String geography) {
        Geography = geography;
    }

    public void setMusic(String music) {
        Music = music;
    }

    public void setComputers(String computers) {
        Computers = computers;
    }

    public String getHistory() {
        return History;
    }

    public String getMovies() {
        return Movies;
    }

    public String getSport() {
        return Sport;
    }

    public String getGeography() {
        return Geography;
    }

    public String getMusic() {
        return Music;
    }

    public String getComputers() {
        return Computers;
    }

    public List<String> getShuffledCategories() {
        List<String> categories = new ArrayList<>();
        categories.add(this.getHistory());
        categories.add(this.getMovies());
        categories.add(this.getSport());
        categories.add(this.getGeography());
        categories.add(this.getMusic());
        categories.add(this.getComputers());
        Collections.shuffle(categories);
        return categories;
    }
}
