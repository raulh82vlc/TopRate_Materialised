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

import java.util.List;

/**
 * Created by Raul Hernandez Lopez on 22/02/2015.
 *
 * FilmDetailsJSONEntity
 * details provided by the API
 * for each a certain film name
 *
 */
public class FilmDetailsJSONEntity {

    /**
     *
     * Dummy example:
     *
     * urlIMDB : http://www.imdb.com/title/tt0111161
     * metascore : 80/100
     * votes : 1,397,566
     * plot : Andy Dufresne is a young and successful banker whose life changes drastically when he is convicted and sentenced to life imprisonment for the murder of his wife and her lover. Set in the 1940's, the film shows how Andy, with the help of his friend Red, the prison entrepreneur, turns out to be a most unconventional prisoner.
     * idIMDB : tt0111161
     * urlPoster : http://ia.media-imdb.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1_SX214_AL_.jpg
     * simplePlot : Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.
     * title : The Shawshank Redemption
     * releaseDate : 19941014
     * originalTitle :
     * year : 1994
     * writers : [{"name":"Stephen King","nameId":"nm0000175"},{"name":"Frank Darabont","nameId":"nm0001104"}]
     * rating : 9.3
     * directors : [{"name":"Frank Darabont","nameId":"nm0001104"}]
     * rated : R
     */
    private String urlIMDB;
    private String metascore;
    private String votes;
    private String plot;
    private String idIMDB;
    private String urlPoster;
    private String simplePlot;
    private String title;
    private String releaseDate;
    private String originalTitle;
    private String year;
    private List<SubDetailsJSONEntity> writers;
    private String rating;
    private List<SubDetailsJSONEntity> directors;
    private String rated;

    public void setUrlIMDB(String urlIMDB) {
        this.urlIMDB = urlIMDB;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public void setIdIMDB(String idIMDB) {
        this.idIMDB = idIMDB;
    }

    public void setUrlPoster(String urlPoster) {
        this.urlPoster = urlPoster;
    }

    public void setSimplePlot(String simplePlot) {
        this.simplePlot = simplePlot;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setWriters(List<SubDetailsJSONEntity> writers) {
        this.writers = writers;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setDirectors(List<SubDetailsJSONEntity> directors) {
        this.directors = directors;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getUrlIMDB() {
        return urlIMDB;
    }

    public String getMetascore() {
        return metascore;
    }

    public String getVotes() {
        return votes;
    }

    public String getPlot() {
        return plot;
    }

    public String getIdIMDB() {
        return idIMDB;
    }

    public String getUrlPoster() {
        return urlPoster;
    }

    public String getSimplePlot() {
        return simplePlot;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getYear() {
        return year;
    }

    public List<SubDetailsJSONEntity> getWriters() {
        return writers;
    }

    public String getRating() {
        return rating;
    }

    public List<SubDetailsJSONEntity> getDirectors() {
        return directors;
    }

    public String getRated() {
        return rated;
    }

    public String getToString(List<SubDetailsJSONEntity> collective) {
        int position = 0;
        String str = "";
        int sizeOfWriters = collective.size();
        for (SubDetailsJSONEntity author : collective) {
            if (position < sizeOfWriters - 1)
                str += author.getName() + ", ";
            else
                str += author.getName();
            ++position;
        }
        return str;
    }

}
