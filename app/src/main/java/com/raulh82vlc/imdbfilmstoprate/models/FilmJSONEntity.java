/*
 * Copyright (C) ${YEAR} Raul Hernandez Lopez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.raulh82vlc.imdbfilmstoprate.models;

/**
 * Created by Raul Hernandez Lopez on 22/02/2015.
 *
 * FilmJSONEntity
 * Film general info provided by the API
 * for each a certain rated film
 *
 */
public class FilmJSONEntity {

    /**
     * Dummy example:
     *
     * idIMDB : tt0111161
     * urlPoster : http://ia.media-imdb.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1_SX34_CR0,0,34,50_AL_.jpg
     * title : The Shawshank Redemption
     * year : 1994
     * rating : 9.2
     * ranking : 1
     */
    private String idIMDB;
    private String urlPoster;
    private String title;
    private String year;
    private double rating;
    private int ranking;

    public void setIdIMDB(String idIMDB) {
        this.idIMDB = idIMDB;
    }

    public void setUrlPoster(String urlPoster) {
        this.urlPoster = urlPoster;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getIdIMDB() {
        return idIMDB;
    }

    public String getUrlPoster() {
        return urlPoster;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public double getRating() {
        return rating;
    }

    public int getRanking() {
        return ranking;
    }
}
