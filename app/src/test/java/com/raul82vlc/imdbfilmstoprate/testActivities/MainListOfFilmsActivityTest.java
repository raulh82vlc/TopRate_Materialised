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

package com.raul82vlc.imdbfilmstoprate.testActivities;


import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;

import android.widget.ImageButton;

import com.raulh82vlc.imdbfilmstoprate.R;
import com.raulh82vlc.imdbfilmstoprate.activities.MainListOfFilmsActivity;

/**
 * Class to Test MainListOfFilmsActivity activity
 * Created by Raul Hernandez Lopez on 03/03/2015.
 */
public class MainListOfFilmsActivityTest extends ActivityInstrumentationTestCase2<MainListOfFilmsActivity> {


    // private Intent mIntent;
    private MainListOfFilmsActivity mMainActivity;
    private ImageButton btnFab;

    public MainListOfFilmsActivityTest() {
        super(MainListOfFilmsActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        setActivityInitialTouchMode(true);
        // mIntent = new Intent(getActivity(), MainListOfFilmsActivity.class);

        mMainActivity = getActivity();
    }

    @MediumTest
    public void testPreconditions()
    {
        btnFab = (ImageButton) getActivity()
                .findViewById(R.id.imgBtnFAB);
        assertNotNull("btnFab is null", btnFab);
    }

    @MediumTest
    public void testNextActivityWasLaunchedWithIntent() {
        // startActivity(mIntent, null, null);
        btnFab = (ImageButton) getActivity()
                .findViewById(R.id.imgBtnFAB);
        btnFab.performClick();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}