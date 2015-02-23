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

package com.raulh82vlc.imdbfilmstoprate.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeTransform;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import com.raulh82vlc.imdbfilmstoprate.api.WebServicesApiCalls;
import com.raulh82vlc.imdbfilmstoprate.models.Constants;
import com.raulh82vlc.imdbfilmstoprate.models.FilmJSONEntity;
import com.raulh82vlc.imdbfilmstoprate.widgets.RecyclerViewAdapter;
import com.raulh82vlc.imdbfilmstoprate.R;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Raul Hernandez Lopez on 22/02/2015.
 *
 * Main Activity where the Recycler view fits
 * by means of a Web Services provider from IMDB
 * public API
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class MainListOfFilmsActivity extends Activity
        implements RecyclerViewAdapter.OnItemClickListenerInterface,
        View.OnClickListener {

    // Initial variables
    private final static int MIN = 1;
    private ImageButton imgBtn;
    private int MAX;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private List<FilmJSONEntity> mFilms;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setAllowReturnTransitionOverlap(true);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setSharedElementExitTransition(new ChangeTransform());

        setContentView(R.layout.films_recycler_view);

        imgBtn = (ImageButton) findViewById(R.id.imgBtn);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_id);

        // Initialising Data structure
        mFilms = new ArrayList<>();
        MAX = 2;
        // Params to initialise Recycler View and Adapter
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mLayoutManager.scrollToPosition(0);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        // Adapter initialisation
        mAdapter = new RecyclerViewAdapter(this, mFilms);
        mAdapter.setOnItemClickFromList(this);
        imgBtn.setOnClickListener(this);

        // Attaching the adapter to the recyclerview
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void onResume() {
        super.onResume();
        getTopRatedFilms(MIN, MAX, true);
    }

    /**
     *
     * Method getTopRatedFilms
     * this does the getting from the network's API
     * then fills all corresponding model entities
     * and does the UI settings for providing the info corresponding
     * to each aspect of the film
     * @param iFrom From the first value of the top
     * @param iTo To the last value of the top you want to indicate
     * @param iFirstTime this flag determines if we just add a value to
     *                   the end of the mFilms collection, or we need
     *                   to fill it completely, this procedure
     *                   is more efficient
     */
    private void getTopRatedFilms(final int iFrom, final int iTo, final boolean iFirstTime) {
        new WebServicesApiCalls(this).getTopFilmsFromARange(iFrom, iTo, new Callback<List<FilmJSONEntity>>() {
            @Override
            public void success(List<FilmJSONEntity> filmJSONEntities, Response response) {
                if(filmJSONEntities != null && filmJSONEntities.size() > 0)
                {
                    int mFilmsSize = filmJSONEntities.size();
                    mFilmsSize--;
                    if(iFirstTime) {
                        mFilms.clear();
                        mFilms.addAll(filmJSONEntities);
                        mAdapter.notifyDataSetChanged();
                    }
                    else
                    {
                        mFilms.add(filmJSONEntities.get(mFilmsSize));
                        mAdapter.notifyItemInserted(mFilmsSize);
                    }
                    mRecyclerView.getLayoutManager().scrollToPosition(mFilmsSize);
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recycler_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemFromListClick(View iView, FilmJSONEntity iFilm) {
        startDetailsActivity(iView, iFilm);
    }

    /**
     * Method startDetailsActivity
     * handles and launches the parameters to the Intent
     * with its corresponding transition
     * @param iView view
     * @param iFilm data structure which corresponds to the Film Entity itself
     */
    private void startDetailsActivity(View iView, FilmJSONEntity iFilm) {
        Intent intentFilmDetail = new Intent(MainListOfFilmsActivity.this, CardFilmDetailsActivity.class);
        // Attaching extra params to the intent
        Bundle bundle = new Bundle();
        bundle.putString(Constants.N_NAME_FILM, iFilm.getTitle());
        bundle.putInt(Constants.N_RANKING, iFilm.getRanking());
        intentFilmDetail.putExtras(bundle);
        // Making the transition animation scene
        ActivityOptionsCompat optionsTransition = ActivityOptionsCompat.makeSceneTransitionAnimation(this, iView.findViewById(R.id.imgThumbnail), iFilm.getUrlPoster());
        ActivityCompat.startActivity(this, intentFilmDetail, optionsTransition.toBundle());
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.imgBtn:
                getTopRatedFilms(MIN, ++MAX, false);
                break;
        }
    }


}
