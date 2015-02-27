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

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.transition.ChangeTransform;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import com.raulh82vlc.imdbfilmstoprate.models.Constants;
import com.raulh82vlc.imdbfilmstoprate.models.FilmDetailsJSONEntity;
import com.raulh82vlc.imdbfilmstoprate.R;

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
    @InjectView(R.id.txtSimplePlot)
    TextView txtSimplePlot;
    @InjectView(R.id.txtPlot)
    TextView txtPlot;
    @InjectView(R.id.txtWriters)
    TextView txtWriters;
    @InjectView(R.id.txtDirectors)
    TextView txtDirectors;
    @InjectView(R.id.card_film_detail)
    CardView cardFilmDetail;

    // variable
    private int ranking = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setAllowEnterTransitionOverlap(true);
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setSharedElementEnterTransition(new ChangeTransform());
        }
        setContentView(R.layout.card_film_details_view);
        ButterKnife.inject(this);

        if (getIntent().getExtras() != null) {
            Bundle extra = getIntent().getExtras();
            String nameOfFilm = extra.getString(Constants.N_NAME_FILM);
            ranking = extra.getInt(Constants.N_RANKING);
            getTopRatedFilms(nameOfFilm);
        }
    }

    /**
     *
     * Method getTopRatedFilms
     * this does the getting from the network's API
     * then fills all corresponding model entities
     * and does the UI settings for providing the info corresponding
     * to each aspect of the film
     * @param iFilmName
     */
    private void getTopRatedFilms(final String iFilmName) {
        getWebServicesApiCalls().getFilmsByName(iFilmName, new Callback<List<FilmDetailsJSONEntity>>() {
            @Override
            public void success(List<FilmDetailsJSONEntity> filmDetails, Response response) {
                if (filmDetails != null && filmDetails.size() > 0) {
                    // Getting the instance of the object from the array returned by IMDB API
                    FilmDetailsJSONEntity filmDetail = filmDetails.get(0);
                    // Setting color
                    if(ranking % 2 == 1) {
                        cardFilmDetail.setCardBackgroundColor(getResources().getColor(R.color.primary));
                    }
                    else
                    {
                        cardFilmDetail.setCardBackgroundColor(getResources().getColor(R.color.primary_dark));
                    }
                    /* Setting Film info for the card view */
                    txtTitle.setText(iFilmName);
                    txtMetaScore.setText("Score: " + filmDetail.getMetascore());
                    txtMetaScore.setBackgroundColor(getResources().getColor(R.color.accent));
                    txtYear.setText("Year: " + filmDetail.getYear());
                    txtSimplePlot.setText(filmDetail.getSimplePlot());
                    txtPlot.setText(filmDetail.getPlot());
                    /* Now the collections composed info */
                    String aWriters = "Writers: ";
                    aWriters += filmDetail.getToString(filmDetail.getWriters());
                    txtWriters.setText(aWriters);

                    String aDirectors = "Directors: ";
                    aDirectors += filmDetail.getToString(filmDetail.getDirectors());
                    txtDirectors.setText(aDirectors);
                    // Finally the image loaded by Universal Image Loader library
                    ImageLoader.getInstance().displayImage(filmDetail.getUrlPoster(), imgExtended);
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
