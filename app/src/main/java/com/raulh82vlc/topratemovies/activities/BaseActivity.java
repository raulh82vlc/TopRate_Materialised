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
package com.raulh82vlc.topratemovies.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.raulh82vlc.topratemovies.api.WebServicesApiCalls;

/**
 * Created by Raul Hernandez Lopez on 24/02/2015.
 */
public class BaseActivity extends ActionBarActivity
{
    private WebServicesApiCalls webServicesApiCalls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webServicesApiCalls = new WebServicesApiCalls(this);
    }

    protected WebServicesApiCalls getWebServicesApiCalls() {
        return webServicesApiCalls;
    }

    /**
    * Method seeToast
    * showing the a toast with a concrete message
    * @param msg string of the message to show
    */
    public void seeToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    protected boolean isInternetConnectionAvailable() {
        ConnectivityManager connectManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectManager.getActiveNetworkInfo();
        if (netInfo == null)
            return false;
        return true;
    }
}
