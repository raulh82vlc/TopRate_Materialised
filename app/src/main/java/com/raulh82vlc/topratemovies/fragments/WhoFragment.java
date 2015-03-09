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
package com.raulh82vlc.topratemovies.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.raulh82vlc.topratemovies.R;
import com.raulh82vlc.topratemovies.models.Constants;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * WhoFragment fragment
 *
 * Created by Raul Hernandez Lopez on 06/03/2015.
 *
 */
public class WhoFragment extends Fragment {

    // Injected views
    @InjectView(R.id.txtAuthorApp)
    TextView txtAuthorApp;
    @InjectView(R.id.btnHide)
    ImageButton btnHide;
    @InjectView(R.id.txtAuthorAppIntro)
    TextView txtAuthorAppIntro;
    @InjectView(R.id.txtTotalMoviesNow)
    TextView txtTotalMoviesNow;
    @InjectView(R.id.txtAuthorAppPreMail)
    TextView txtAuthorAppPreMail;
    @InjectView(R.id.txtAuthorAppMail)
    TextView txtAuthorAppMail;
    // Variables
    private String mAuthorName, mEmail;
    private int numberOfMovies = 0;

    /**
     * Factory pattern design method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param iAuthorName input name of the author
     * @param iEmail input email
     * @param iTotalSize total number of movies in the screen at that time
     *
     * @return A new instance of fragment WhoFragment
     */
    public static WhoFragment newInstance(String iAuthorName, String iEmail, int iTotalSize) {
        WhoFragment fragment = new WhoFragment();
        Bundle args = new Bundle();
        args.putString(Constants.F_AUTHOR, iAuthorName);
        args.putString(Constants.F_EMAIL, iEmail);
        args.putInt(Constants.F_NMOVIES, iTotalSize);
        fragment.setArguments(args);
        return fragment;
    }

    public WhoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAuthorName = getArguments().getString(Constants.F_AUTHOR);
            mEmail = getArguments().getString(Constants.F_EMAIL);
            numberOfMovies = getArguments().getInt(Constants.F_NMOVIES);
        }
    }

    /**
     * onCreateView
     * here the view is inflated first, then butterknife injections
     * and finally the info is filled
     *
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_who, container, false);
        ButterKnife.inject(this, view);
        fillInfo();
        return view;
    }

    /**
     * Method fillInfo
     * fills the whole info of this fragment
     *
     */
    private void fillInfo() {
        txtAuthorAppIntro.setText(getString(R.string.intro_who));
        txtAuthorApp.setText(mAuthorName);
        txtAuthorAppPreMail.setText(getString(R.string.contact));
        txtAuthorAppMail.setText(mEmail);
        txtTotalMoviesNow.setText(numberOfMovies + " " + getString(R.string.content_number_movies));
    }

    /**
     * OnClick listener for destroying
     * the view and reset the butterknife injections
     * to save memory
     *
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    /**
     * OnClick listener for hidding ImageButton
     * this finishes the fragment
     *
     */
    @OnClick(R.id.btnHide)
    public void frameDestroyed() {
        getActivity().getSupportFragmentManager().popBackStack();
    }
}
