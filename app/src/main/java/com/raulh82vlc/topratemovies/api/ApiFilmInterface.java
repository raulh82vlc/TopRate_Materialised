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

package com.raulh82vlc.topratemovies.api;

import com.raulh82vlc.topratemovies.models.FilmDetailsJSONEntity;
import com.raulh82vlc.topratemovies.models.FilmJSONEntity;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.Callback;

/**
 * Created by Raul Hernandez Lopez on 22/02/2015.
 * API calls by means of Retrofit annotations
 */

public interface ApiFilmInterface {

    @GET("/imdb/top")
    public void getFilmsTop(@Query("token") String token,
                            @Query("format") String jsonFormat,
                            @Query("start") Integer fromFirst,
                            @Query("end") Integer toLast,
                            @Query("data") String typeOfData,
                            Callback<List<FilmJSONEntity>> oFilms);


    @GET("/imdb")
    public void getFilmsByName(@Query("token") String token,
                               @Query("format") String jsonFormat,
                               @Query("title") String movieTitle,
                               Callback<List<FilmDetailsJSONEntity>> oFilm);
}
