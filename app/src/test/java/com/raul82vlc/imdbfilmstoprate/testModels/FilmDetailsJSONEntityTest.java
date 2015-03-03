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

package com.raul82vlc.imdbfilmstoprate.testModels;


import com.raulh82vlc.imdbfilmstoprate.models.FilmDetailsJSONEntity;
import com.raulh82vlc.imdbfilmstoprate.models.SubDetailsEntity;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raul on 03/03/2015.
 */
public class FilmDetailsJSONEntityTest  extends TestCase {

    // instance of FilmDetailsJSONEntity
    private FilmDetailsJSONEntity filmDetails;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // instance of the film entity
        filmDetails = new FilmDetailsJSONEntity();

        List<SubDetailsEntity> writers1 = new ArrayList<>();
        SubDetailsEntity newSub = new SubDetailsEntity();
        newSub.setNameId("1A");
        newSub.setName("Cam1A");
        writers1.add(newSub);

        filmDetails.setWriters(writers1);
    }

    @Test
    public void testSetMetascore()
    {
        filmDetails.setMetascore("80/100");
        assertEquals(filmDetails.getMetascore(), "80/100");
    }

    @Test
    public void testWriters()
    {
        assertEquals(filmDetails.getWriters().size(), 1);
        assertEquals(filmDetails.getWriters().get(0).getName(), "Cam1A");
    }

    @Test
    public void testGetToString()
    {
        List<SubDetailsEntity> writers = filmDetails.getWriters();
        SubDetailsEntity newSub = new SubDetailsEntity();
        newSub.setNameId("2A");
        newSub.setName("Cam2A");
        writers.add(newSub);
        filmDetails.setWriters(writers);
        assertEquals(filmDetails.getToString(filmDetails.getWriters()), "Cam1A, Cam2A");
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
