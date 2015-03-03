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


import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;

import android.widget.ImageButton;

import com.raulh82vlc.imdbfilmstoprate.R;
import com.raulh82vlc.imdbfilmstoprate.activities.MainListOfFilmsActivity;

/**
 * Created by Raul on 22/02/2015.
 */

public class MainListOfFilmsActivityTest  extends ActivityUnitTestCase<MainListOfFilmsActivity> {

    private ImageButton btnFab;
    private Intent mIntent;

    public MainListOfFilmsActivityTest() {

        super(MainListOfFilmsActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mIntent = new Intent(getInstrumentation()
                .getTargetContext(), MainListOfFilmsActivity.class);
    }

    @MediumTest
    public void testNextActivityWasLaunchedWithIntent() {
        startActivity(mIntent, null, null);

        btnFab = (ImageButton) getActivity()
                .findViewById(R.id.imgBtnFAB);
        btnFab.performClick();
        final Intent launchIntent = getStartedActivityIntent();
        assertNotNull("Intent was null", launchIntent);
        assertTrue(isFinishCalled());
    }
}