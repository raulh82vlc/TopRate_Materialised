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

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by Raul Hernandez Lopez on 22/02/2015.
 *
 * Controller to call Web Services in Activity
 */
public class WebServicesController {

    private RestAdapter mRestAdapterRetrofit;
    private Context mContext;

    public WebServicesController(Context iCtx)
    {
        Context mContext = iCtx;

        Gson aGsonInstance = new GsonBuilder().create();
        mRestAdapterRetrofit = new RestAdapter.Builder()
                .setConverter(new GsonConverter(aGsonInstance))
                .setEndpoint(ApiConstans.URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
    }

    public ApiFilmInterface getFilmInterface()
    {
        return mRestAdapterRetrofit.create(ApiFilmInterface.class);
    }
}
