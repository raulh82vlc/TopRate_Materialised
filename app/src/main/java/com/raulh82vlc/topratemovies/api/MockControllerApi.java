package com.raulh82vlc.topratemovies.api;

import com.raulh82vlc.topratemovies.models.FilmJSONEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raul Hernandez Lopez on 06/03/2015.
 */
public class MockControllerApi {

    public static List<FilmJSONEntity> giveInfoOfMockFilms() {
        List<FilmJSONEntity> films = new ArrayList<>();

        String title = "";
        double mark = -1;
        String year = "";

        int j = 0;
        do {
            for(int i = 1 ; i < 6 ; i++) {
                switch (i) {
                    case 1:
                        title = "The Shawshank Redemption";
                        mark = 9.2;
                        year = "1954";
                        break;
                    case 2:
                        title = "The Godfather";
                        mark = 9.2;
                        year = "1972";
                        break;
                    case 3:
                        title = "The Godfather: Part II";
                        mark = 9.0;
                        year = "1974";
                        break;
                    case 4:
                        title = "The Dark Knight";
                        mark = 8.9;
                        year = "2008";
                        break;
                    default:
                        title = "Pulp Fiction";
                        mark = 8.7;
                        year = "1994";
                        break;
                }
                films.add(new FilmJSONEntity("http://lorempixel.com/400/200/sports/" + i, title, year, mark, i));
            }
            j++;
         } while(j != 2);
        return films;
    }

    public static int giveInfoOfMockFilm(List<FilmJSONEntity> films) {
        int count = films.size();
        films.add(new FilmJSONEntity("http://lorempixel.com/400/200/sports/ + 1", "The Dark Knight", "2101", 10, 9));
        return count;
    }
}
