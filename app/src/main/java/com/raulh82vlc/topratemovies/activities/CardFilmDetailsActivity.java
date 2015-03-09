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

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.transition.Slide;
import android.transition.Transition;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.raulh82vlc.topratemovies.R;
import com.raulh82vlc.topratemovies.models.Constants;
import com.raulh82vlc.topratemovies.models.FilmDetailsJSONEntity;
import com.raulh82vlc.topratemovies.models.FilmJSONEntity;
import com.raulh82vlc.topratemovies.widgets.CompatUtils;
import com.raulh82vlc.topratemovies.widgets.TransitionFlowAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Raul Hernandez Lopez on 22/02/2015.
 * CardView where the further details are filled
 */

public class CardFilmDetailsActivity extends BaseActivity {

    // constant
    private static final String TAG_DETAIL = "FilmDetails";

    @InjectView(R.id.imgExtended)
    ImageView imgExtended;
    @InjectView(R.id.txtTitle)
    TextView txtTitle;
    @InjectView(R.id.txtYear)
    TextView txtYear;
    @InjectView(R.id.txtMetaScore)
    TextView txtMetaScore;
    @InjectView(R.id.txtGenres)
    TextView txtGenres;
    @InjectView(R.id.txtPlot)
    TextView txtPlot;
    @InjectView(R.id.txtWriters)
    TextView txtWriters;
    @InjectView(R.id.txtDirectors)
    TextView txtDirectors;
    @InjectView(R.id.card_film_detail)
    CardView cardFilmDetail;
    @InjectView(R.id.toolbar_id)
    Toolbar mToolbar;
    @InjectView(R.id.scroll_id)
    ScrollView mScrollView;
    @InjectView(R.id.frame_layout)
    FrameLayout mFrameLayout;

    // variable
    private int ranking = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startAllTransitions();

        setContentView(R.layout.card_film_details_view);
        // To start the entry of the new activity
        ActivityCompat.postponeEnterTransition(this);

        ButterKnife.inject(this);
        setSupportActionBar(mToolbar);
        ViewCompat.setTransitionName(imgExtended, Constants.N_NAME_IMG);

        if (getIntent().getExtras() != null) {
            Bundle extra = getIntent().getExtras();
            String nameOfFilm = extra.getString(Constants.N_NAME_FILM);
            ranking = extra.getInt(Constants.N_RANKING);
            // now start the transition
            ActivityCompat.startPostponedEnterTransition(this);
            getTopRatedFilms(nameOfFilm);
        }
    }

    /**
     * Method getTopRatedFilms
     * this does the getting from the network's API
     * then fills all corresponding model entities
     * and does the UI settings for providing the info corresponding
     * to each aspect of the film
     *
     * @param iFilmName
     */
    private void getTopRatedFilms(final String iFilmName) {
        getWebServicesApiCalls().getFilmsByName(iFilmName, new Callback<List<FilmDetailsJSONEntity>>() {
            @Override
            public void success(List<FilmDetailsJSONEntity> filmDetails, Response response) {
                if (filmDetails != null && filmDetails.size() > 0) {
                    // Getting the instance of the object from the array returned by the API

                    final FilmDetailsJSONEntity filmDetail = filmDetails.get(0);
                    if(filmDetail != null && !TextUtils.isEmpty(filmDetail.getTitle()) && !TextUtils.isEmpty(filmDetail.getUrlIMDB())) {
                        fillInfo(filmDetail);
                    }
                    else
                    {
                        seeToast(getString(R.string.error_server) + " " + iFilmName);
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    /**
     * Method fillInfo
     * all written info is filled
     * @param filmDetail
     */
    private void fillInfo(FilmDetailsJSONEntity filmDetail) {
        fillColors();
        /* Setting Film info for the card view */
        txtTitle.setText(filmDetail.getTitle());
        txtMetaScore.setText("Score: " + filmDetail.getMetascore());

        txtYear.setText("Year: " + filmDetail.getYear());
        txtPlot.setText(filmDetail.getPlot());
        /* Now the collections composed info */
        // Writers
        String aWriters = "Writers: ";
        aWriters += filmDetail.getToString(filmDetail.getWriters());
        txtWriters.setText(aWriters);
        // Genres
        txtGenres.setText(filmDetail.getToStringGenres());
        // Directors
        String aDirectors = "Directors: ";
        aDirectors += filmDetail.getToString(filmDetail.getDirectors());
        txtDirectors.setText(aDirectors);

        fillImage(filmDetail);
    }

    /**
     * Method fillColors
     * colors of each row are drawn
     * and the highlight effect of the score
     */
    private void fillColors() {
        // Setting color
        if (ranking % 2 == 1) {
            cardFilmDetail.setCardBackgroundColor(getResources().getColor(R.color.primary));
        } else {
            cardFilmDetail.setCardBackgroundColor(getResources().getColor(R.color.primary_dark));
        }
        txtMetaScore.setBackgroundColor(getResources().getColor(R.color.accent));
    }

    /**
     * Method fillInfo
     * all written info is filled
     * @param filmDetail
     */
    private void fillImage(FilmDetailsJSONEntity filmDetail) {

        // Finally the image loaded by Universal Image Loader library
        final String titleOfFilm = filmDetail.getTitle();

        // Load image, decode it to Bitmap and return Bitmap to callback
        ImageLoader.getInstance().displayImage(filmDetail.getUrlPoster(), imgExtended, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                // Do whatever you want with Bitmap

                Palette.generateAsync(loadedImage, new Palette.PaletteAsyncListener() {
                    public void onGenerated(Palette palette) {
                        applyPalette(palette, titleOfFilm);
                    }
                });
            }
        });
    }

    /**
     * Method applyPalette
     * takes the principal colors and  set the toolbar
     * muted color as well as the fade in and starts to pospone the transaction
     * @param iPalette input palette
     * @param titleOfFilm
     */
    private void applyPalette(Palette iPalette, String titleOfFilm) {
        int primaryDark = getResources().getColor(R.color.primary_dark);
        int primary = getResources().getColor(R.color.primary);
        mToolbar.setBackgroundColor(iPalette.getMutedColor(primary));
        CompatUtils.setToolBarColor(getWindow(), iPalette.getDarkMutedColor(primaryDark));
        startScrollFadeIn(titleOfFilm);
        ActivityCompat.startPostponedEnterTransition(this);
    }

    /**
     * Method startScrollFadeIn
     * starts the scroll setting the components
     * and adding the scroll listener when changing it
     * @param titleOfFilm
     */
    private void startScrollFadeIn(final String titleOfFilm) {

        setComponentFeatures(titleOfFilm);

        mScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                setComponentFeatures(titleOfFilm);
            }
        });
    }

    /**
     * Method setComponentFeatures
     * sets the dynamic translation in y-axis
     * when users moves the scroll
     * then calculates the alpha from the color
     * sets colors and the title in the tootbar
     * @param titleOfFilm
     */
    private void setComponentFeatures(String titleOfFilm) {
        int scrollY = mScrollView.getScrollY();
        imgExtended.setTranslationY(-scrollY / 2);
        ColorDrawable background = (ColorDrawable) mToolbar.getBackground();
        int padding = mScrollView.getPaddingTop();
        double alphaColor = (1 - (((double) padding - (double) scrollY) / (double) padding)) * 255.0;
        alphaColor = alphaColor < 0 ? 0 : alphaColor;
        alphaColor = alphaColor > 255 ? 255 : alphaColor;

        background.setAlpha((int) alphaColor);
        mFrameLayout.setBackgroundColor(background.getColor());
        float scrollRatio = (float) (alphaColor / 255f);
        int titleColor = getItsAlphaColor(Color.WHITE, scrollRatio);
        mToolbar.setTitleTextColor(titleColor);
        mToolbar.setTitle(titleOfFilm);
    }

    /**
     * Method getItsAlphaColor
     * if API 21 then effects are settled
     * @param iColor alpha of a color
     * @param scrollRatio float variant
     */
    private int getItsAlphaColor(int iColor, float scrollRatio) {
        return Color.argb((int) (scrollRatio * 255f), Color.red(iColor), Color.green(iColor), Color.blue(iColor));
    }

    /**
     * Method startAllTransitions
     * if API 21 then effects are settled
     */
    private void startAllTransitions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide transition = new Slide();
            transition.excludeTarget(android.R.id.statusBarBackground, true);
            getWindow().setEnterTransition(transition);
            getWindow().setReturnTransition(transition);
        }
    }

    /**
     * Method startDetailsActivity
     * handles and launches the parameters to the Intent
     * with its corresponding transition
     * @param context Activity which should be launched (own CardFilmDetailsActivity)
     * @param transitionFromPrevImage view
     * @param iFilm data structure which corresponds to the Film Entity itself
     */
    public static void startDetailsActivity(Activity context, View transitionFromPrevImage, FilmJSONEntity iFilm) {
        Intent aIntentDetails = new Intent(context, CardFilmDetailsActivity.class);
        aIntentDetails.putExtra(Constants.N_NAME_FILM, iFilm.getTitle());
        aIntentDetails.putExtra(Constants.N_NAME_IMG, iFilm.getUrlPoster());
        aIntentDetails.putExtra(Constants.N_RANKING, iFilm.getRanking());

        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(context, transitionFromPrevImage, Constants.N_NAME_IMG);
        ActivityCompat.startActivity(context, aIntentDetails, optionsCompat.toBundle());
    }

    @Override
    public void onBackPressed() {
        fallbackActionBar();
        super.onBackPressed();
    }

    /**
     * Method fallbackActionBar
     * restores the action bar when back pressed
     */
    private void fallbackActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getReturnTransition().addListener(new TransitionFlowAdapter() {
                @Override
                public void onTransitionEnd(Transition transition) {
                    mToolbar.setTitleTextColor(Color.WHITE);
                    mToolbar.getBackground().setAlpha(200);
                }
            });
        }
    }
}
