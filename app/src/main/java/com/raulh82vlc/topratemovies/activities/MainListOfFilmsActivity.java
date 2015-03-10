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

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.raulh82vlc.topratemovies.R;
import com.raulh82vlc.topratemovies.fragments.WhoFragment;
import com.raulh82vlc.topratemovies.models.Constants;
import com.raulh82vlc.topratemovies.models.FilmJSONEntity;
import com.raulh82vlc.topratemovies.widgets.RecyclerViewAdapter;
import com.raulh82vlc.topratemovies.widgets.ScrollManagerHandler;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Raul Hernandez Lopez on 22/02/2015.
 * <p/>
 * Main Activity where the Recycler view fits
 * by means of a Web Services provider
 * public API
 */
public class MainListOfFilmsActivity extends BaseActivity
        implements RecyclerViewAdapter.OnItemClickListenerInterface,
        View.OnClickListener {

    // Initial variables
    @InjectView(R.id.recycler_id)
    RecyclerView mRecyclerView;
    @InjectView(R.id.toolbar_id)
    Toolbar mToolbar;
    @InjectView(R.id.imgBtnFAB)
    ImageButton imgBtnFAB;
    @InjectView(R.id.imgWho)
    ImageButton imgWho;
    @InjectView(R.id.imgRetry)
    ImageButton imgRetry;

    // up range of the films
    private int upRangeFilms;
    private RecyclerViewAdapter mAdapter;
    // Data structure
    private List<FilmJSONEntity> mFilms;
    // Gobal counter
    private int mTotalSize = 0;

    // In case the mock data was loaded first time
    // private boolean isLoadedFirstTime = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.films_recycler_view);

        ButterKnife.inject(this);

        if (mToolbar != null)
            setSupportActionBar(mToolbar);

        // Initialising Data structure
        mFilms = new ArrayList<>();
        // Initialising Variable for handling
        upRangeFilms = Constants.MAX;
        // Params to initialise Recycler View and Adapter
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Adapter initialisation
        mAdapter = new RecyclerViewAdapter(this, mFilms);
        // set Listener
        mAdapter.setOnItemClickFromList(this);
        // buttons listener
        imgBtnFAB.setOnClickListener(this);
        imgWho.setOnClickListener(this);
        imgRetry.setOnClickListener(this);

        // Attaching the adapter to the recyclerview
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // The initial offset is required, and toolbar height
        // needs to be known before the setting it
        mToolbar.post(new Runnable() {
            @Override
            public void run() {
                ScrollManagerHandler manager = new ScrollManagerHandler();
                manager.setToRecyclerView(mRecyclerView);
                manager.addViewToHide(mToolbar, Constants.TOWARDS.TOP);
                manager.addViewToHide(imgWho, Constants.TOWARDS.BOTTOM);
                manager.addViewToHide(imgBtnFAB, Constants.TOWARDS.BOTTOM);
                manager.setOffsetFactor(mToolbar.getHeight());
            }
        });
        // Toolbar settings
        mToolbar.setBackgroundColor(getResources().getColor(R.color.blue_translucent));
        mToolbar.setTitle(getString(R.string.top_rate));

        // Checking internet connection
        if (isInternetConnectionAvailable()) {
            getTopRatedFilms(Constants.MIN, upRangeFilms, true);
            imgRetry.setVisibility(View.GONE);
        } else {
            imgBtnFAB.setVisibility(View.GONE);
            imgRetry.setVisibility(View.VISIBLE);
            seeToast(getString(R.string.try_again));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        /* Mock Validation Test */
        /*
        if(!isLoadedFirstTime)
            getInRecycleView(MockControllerApi.giveInfoOfMockFilms());
        */
    }


    /**
     * Method getTopRatedFilms
     * this does the getting from the network's API
     * then fills all corresponding model entities
     * and does the UI settings for providing the info corresponding
     * to each aspect of the film
     *
     * @param iFrom      From the first value of the top
     * @param iTo        To the last value of the top you want to indicate
     * @param iFirstTime this flag determines if we just add a value to
     *                   the end of the mFilms collection, or we need
     *                   to fill it completely, this procedure
     *                   is more efficient
     */
    private void getTopRatedFilms(final int iFrom, final int iTo, final boolean iFirstTime) {
        getWebServicesApiCalls().getTopFilmsFromARange(iFrom, iTo, new Callback<List<FilmJSONEntity>>() {
            @Override
            public void success(List<FilmJSONEntity> filmJSONEntities, Response response) {
                int aFilmsSize = 0;
                if (filmJSONEntities != null && filmJSONEntities.size() > 0) {
                    aFilmsSize = filmJSONEntities.size();
                    aFilmsSize--;
                    if (iFirstTime) {
                        adapterHandlerForAllInitialData(filmJSONEntities);
                    } else {
                        adapterHandlerForOneFinalInstance(filmJSONEntities, aFilmsSize);
                    }
                    seeToast(getString(R.string.film_captured) + " " + filmJSONEntities.get(aFilmsSize).getTitle());
                    mTotalSize = mFilms.size();
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    /**
     * Method adapterHandlerForOneFinalInstance
     * this does the addition of one element to
     * the recycler view through the adapter
     *
     * @param filmJSONEntities List of movies
     * @param iFilmsSize       actual size to scroll on
     */
    private void adapterHandlerForOneFinalInstance(List<FilmJSONEntity> filmJSONEntities, int iFilmsSize) {
        mFilms.add(filmJSONEntities.get(iFilmsSize));
        mAdapter.notifyItemInserted(iFilmsSize);
        setScrollPosition(iFilmsSize);
    }

    /**
     * Method adapterHandlerForAllInitialData
     * this does the addition of ALL elements to
     * the recycler view through the adapter
     *
     * @param filmJSONEntities List of movies
     */
    private void adapterHandlerForAllInitialData(List<FilmJSONEntity> filmJSONEntities) {
        mFilms.clear();
        mFilms.addAll(filmJSONEntities);
        mAdapter.notifyDataSetChanged();
        setScrollPosition(filmJSONEntities.size());
    }

    /**
     * Method setScrollPosition
     * this does scroll in the RecyclerView
     *
     * @param iPosition position to scroll to
     */
    private void setScrollPosition(int iPosition) {
        mRecyclerView.getLayoutManager().scrollToPosition(iPosition);
    }

    @Override
    public void onItemFromListClick(View iView, FilmJSONEntity iFilm) {
        CardFilmDetailsActivity.startDetailsActivity(this, iView.findViewById(R.id.imgThumbnail), iFilm);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBtnFAB:
                if (isInternetConnectionAvailable()) {
                    getTopRatedFilms(Constants.MIN, ++upRangeFilms, false);
                    imgRetry.setVisibility(View.GONE);
                    imgBtnFAB.setVisibility(View.VISIBLE);
                }
                else
                {
                    seeToast(getString(R.string.try_again));
                    imgRetry.setVisibility(View.VISIBLE);
                    imgBtnFAB.setVisibility(View.GONE);
                }
                /* Mock Validation test */
                /*
                int count = MockControllerApi.giveInfoOfMockFilm(mFilms);
                getInRecycleViewOne(count);
                */
                break;
            case R.id.imgWho:
                // Setting the fragment with animation when has to be started
                getSupportFragmentManager().beginTransaction().setCustomAnimations(
                        R.animator.custom_slide_in_bottom,
                        R.animator.custom_slide_out_bottom,
                        R.animator.custom_slide_in_bottom,
                        R.animator.custom_slide_out_bottom)
                        .replace(R.id.container_fragment,
                                WhoFragment.newInstance(
                                        getString(R.string.author),
                                        getString(R.string.email),
                                        mTotalSize))
                        .addToBackStack(null).commit();
                break;
            case R.id.imgRetry:
                if (isInternetConnectionAvailable()) {
                        getTopRatedFilms(Constants.MIN, upRangeFilms, true);
                        imgRetry.setVisibility(View.GONE);
                        imgBtnFAB.setVisibility(View.VISIBLE);
                }
                else
                    seeToast(getString(R.string.try_again));
                    imgRetry.setVisibility(View.VISIBLE);
                    imgBtnFAB.setVisibility(View.GONE);
                break;
        }
    }

    /* Mock validation test */
    /*
    private void getInRecycleViewOne(int count) {
        mAdapter.notifyItemInserted(count);
        mRecyclerView.getLayoutManager().scrollToPosition(count);
    }
    */

    /* Mock validation test */
    /*
    private void getInRecycleView(List<FilmJSONEntity> filmJSONEntities) {

        if (filmJSONEntities != null && filmJSONEntities.size() > 0) {
            mFilms.clear();
            mFilms.addAll(filmJSONEntities);
            mAdapter.notifyDataSetChanged();
            mRecyclerView.getLayoutManager().scrollToPosition(filmJSONEntities.size());
            isLoadedFirstTime = true;
        }
    }
    */
}
