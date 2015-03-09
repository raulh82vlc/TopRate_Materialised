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
package com.raul82vlc.topratemovies.testActivities;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.TextView;

import com.raulh82vlc.topratemovies.R;
import com.raulh82vlc.topratemovies.activities.CardFilmDetailsActivity;


/**
 * Class to Test CardFilmDetailsActivity activity
 * Created by Raul Hernandez Lopez on 03/03/2015.
 */
public class CardFilmDetailsActivityTest extends ActivityInstrumentationTestCase2<CardFilmDetailsActivity> {

    private TextView txtTitle, txtSimplePlot;
    private CardFilmDetailsActivity mCardFilmDetailsActivity;

    public CardFilmDetailsActivityTest() {
        super(CardFilmDetailsActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mCardFilmDetailsActivity = getActivity();
    }

    @MediumTest
    public void testPreconditions() {
        assertNotNull("mCardFilmDetailsActivity is null", mCardFilmDetailsActivity);
    }

    @MediumTest
    public void testTxtTitleMockInitial()
    {
        txtTitle = (TextView) getActivity()
                .findViewById(R.id.txtTitle);

        final String expected = getActivity().getResources().getString(R.string.mock_info_title);
        final String actual = txtTitle.getText().toString();
        assertEquals("mCardFilmDetailsActivity contains wrong text", expected, actual);
    }

    @MediumTest
    public void testTxtContentMockInitial()
    {
        txtSimplePlot = (TextView) getActivity()
                .findViewById(R.id.txtSimplePlot);

        final String expected = getActivity().getResources().getString(R.string.mock_info_content);
        final String actual = txtSimplePlot.getText().toString();
        assertEquals("mCardFilmDetailsActivity contains wrong text", expected, actual);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
