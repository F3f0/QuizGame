package Questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Category {
    String History;
    String Movies;
    String Sport;
    String Geography;
    String Music;
    String Computers;

    public Category() {
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
