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

package com.raulh82vlc.imdbfilmstoprate.api;

import android.content.Context;

import com.raulh82vlc.imdbfilmstoprate.models.FilmDetailsJSONEntity;
import com.raulh82vlc.imdbfilmstoprate.models.FilmJSONEntity;

import java.util.List;

import retrofit.Callback;

/**
 * Created by Raul Hernandez Lopez on 22/02/2015.
 * declared calls with params
 */

public class WebServicesApiCalls {

    private Context mContext;
    private ApiFilmInterface mApiFilmInterface;

    public WebServicesApiCalls(Context ctx)
    {
        mContext = ctx;
        mApiFilmInterface = new WebServicesController(mContext).getFilmInterface();
    }

    /**
     * This was defined in the Interface
     * getFilmsTop
     *
     * @param fromFirst
     * @param toLast
     * @param oFilms
     *
     * output is Callback<List<FilmJSONEntity>> oFilms);
     *
     *  */
    public void getTopFilmsFromARange(Integer fromFirst, Integer toLast, Callback<List<FilmJSONEntity>> oFilms)
    {
        mApiFilmInterface.getFilmsTop(ApiConstans.formatToRetrofit, fromFirst, toLast, ApiConstans.formatOfInfo, oFilms);
    }

    /**
     * This was defined in the Interface
     * getFilmsByName
     *
     * @param iMovieTitle
     * output is Callback<List<FilmDetailsJSONEntity>>
     *
     */
    public void getFilmsByName(String iMovieTitle, Callback<List<FilmDetailsJSONEntity>> oFilm)
    {
        mApiFilmInterface.getFilmsByName(ApiConstans.formatToRetrofit, iMovieTitle, oFilm);
    }

}
